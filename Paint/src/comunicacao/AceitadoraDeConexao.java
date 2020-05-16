package comunicacao;

import java.net.*;
import java.util.*;

public class AceitadoraDeConexao extends Thread
{
    private static final int PORTA_PADRAO = 12345;
    private int porta;

    private ServerSocket pedido;
    private ArrayList<Parceiro> clientes;

    public AceitadoraDeConexao (String escolha, ArrayList<Parceiro> clientes) throws Exception {
        porta = AceitadoraDeConexao.PORTA_PADRAO;

        if (escolha != null) {
            try {
                porta = Integer.parseInt(escolha);
                this.pedido = new ServerSocket(porta);
            }
            catch (Exception  erro) {
                throw new Exception("Porta invalida");
            }
        }

        if (clientes == null)
            throw new Exception("Clientes ausentes");

        this.clientes = clientes;
    }

    public void run () {
        System.out.println("Servidor iniciado na porta: " + porta);

        for(;;) {
            Socket conexao = null;

            try {
                conexao = this.pedido.accept();
            }
            catch (Exception erro) {
                continue;
            }

            SupervisoraDeConexao supervisoraDeConexao = null;

            try {
                supervisoraDeConexao = new SupervisoraDeConexao(conexao, clientes);
            }
            catch (Exception erro) {} // sei que passei parametros corretos para o construtor

            supervisoraDeConexao.start();
        }
    }
}
