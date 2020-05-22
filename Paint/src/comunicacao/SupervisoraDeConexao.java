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
                    PedidoSalvamento pedidoSalvamento = (PedidoSalvamento)comunicado;
                    
                    database.Desenho desenho = null;

                    try {
                        desenho = desenhoDAO.buscarDesenho(pedidoSalvamento.getIpCliente(), pedidoSalvamento.getNome());
                    }
                    catch (Exception erro) {
                        throw erro;
                    }

                    if (desenho != null) {
                        desenho.setConteudo(pedidoSalvamento.getDesenho().toString());
                        desenho.setDataUltimaAtualizacao(new Date());
                    }
                    else {
                        desenho = new database.Desenho();

                        desenho.setIpCriador(pedidoSalvamento.getIpCliente());
                        desenho.setNome(pedidoSalvamento.getNome());
                        desenho.setConteudo(pedidoSalvamento.toString());
                        desenho.setDataCriacao(new Date());
                        desenho.setDataUltimaAtualizacao(new Date());
                    }

                    try {
                        desenhoDAO.salvarDesenho(desenho);
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
