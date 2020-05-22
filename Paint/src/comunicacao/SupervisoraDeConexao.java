package comunicacao;

import java.io.*;
import java.net.*;
import java.util.*;

import database.DesenhoDAOMemoria;
import database.IDesenhoDAO;

public class SupervisoraDeConexao extends Thread {
    private double valor = 0;

    private Parceiro cliente;
    private Socket conexao;

    private ArrayList<Parceiro> clientes;

    private IDesenhoDAO desenhoDAO;

    public SupervisoraDeConexao (Socket conexao, ArrayList<Parceiro> clientes) throws Exception {
        if (conexao == null)
            throw new Exception ("Conexão ausente");

        if (clientes == null)
            throw new Exception ("Clientes ausentes");

        this.conexao  = conexao;
        this.clientes = clientes;

        this.desenhoDAO = new DesenhoDAOMemoria();
    }

    public void run() {
        ObjectOutputStream transmissor;

        try {
            transmissor = new ObjectOutputStream(this.conexao.getOutputStream());
        }
        catch (Exception erro) {
            return;
        }
        
        ObjectInputStream receptor = null;

        try {
            receptor = new ObjectInputStream(this.conexao.getInputStream());
        }
        catch (Exception err0) {
            try {
                transmissor.close();
            }
            catch (Exception falha) {} // so tentando fechar antes de acabar a thread
            
            return;
        }

        try {
            this.cliente = new Parceiro(this.conexao, receptor, transmissor);
        }
        catch (Exception erro) {} // sei que passei os parametros corretos

        try {
            synchronized(this.clientes) {
                this.clientes.add(this.cliente);
                System.out.println("Novo cliente conectado!");
            }

            for(;;) {
                Comunicado comunicado = this.cliente.envie();

                if (comunicado == null)
                    return;

                else if (comunicado instanceof PedidoDesenhos) {
					
                }
                else if (comunicado instanceof PedidoSalvamento) {
                    System.out.println("Pedido de salvamento recebido do cliente " + cliente.getIp());

                    PedidoSalvamento pedidoSalvamento = (PedidoSalvamento)comunicado;
                    
                    database.Desenho desenho = null;

                    try {
                        desenho = desenhoDAO.buscarDesenho(cliente.getIp(), pedidoSalvamento.getDesenho().getNome());
                    }
                    catch (Exception erro) {
                        throw erro;
                    }

                    if (desenho != null) {
                        System.out.println("O desenho '" + pedidoSalvamento.getDesenho().getNome() + "' já existe para o cliente " + cliente.getIp() + " e será atualizado");

                        desenho.setFiguras(pedidoSalvamento.getDesenho().getFiguras());
                        desenho.setDataUltimaAtualizacao(new Date());
                    }
                    else {
                        System.out.println("O desenho '" + pedidoSalvamento.getDesenho().getNome() + "' não existe para o cliente " + cliente.getIp() + " e será criado");

                        desenho = new database.Desenho();

                        desenho.setIpCriador(cliente.getIp());
                        desenho.setNome(pedidoSalvamento.getDesenho().getNome());
                        desenho.setFiguras(pedidoSalvamento.getDesenho().getFiguras());
                        desenho.setDataCriacao(new Date());
                        desenho.setDataUltimaAtualizacao(new Date());
                    }

                    System.out.println("Conteúdo do desenho '" + pedidoSalvamento.getDesenho().getNome() + "' do cliente " + cliente.getIp());
                        
                    for (String figura : desenho.getFiguras()) {
                        System.out.println(figura);
                    }

                    try {
                        desenhoDAO.salvarDesenho(desenho);

                        System.out.println("O desenho '" + pedidoSalvamento.getDesenho().getNome() + "' do cliente " + cliente.getIp() + " foi salvo com sucesso");
                    }
                    catch (Exception erro) {
                        throw erro;
                    }
                }
                else if (comunicado instanceof EncerrarConexao) {
                    synchronized (this.clientes) {
                        this.clientes.remove(this.cliente);
                    }

                    this.cliente.adeus();
                    System.out.println("Cliente desconectado!");
                }
            }
        }
        catch (Exception erro) {
            try {
                transmissor.close();
                receptor.close();
            }
            catch (Exception falha) {} // so tentando fechar antes de acabar a thread

            return;
        }
    }
}
