package servidor;

import java.util.ArrayList;

import comunicacao.AceitadoraDeConexao;
import comunicacao.Parceiro;

public class Servidor  {
    private static String PORTA_PADRAO = "12345";

    public static void main(String[] args) {
        ArrayList<Parceiro> clientes = new ArrayList<Parceiro>();

        AceitadoraDeConexao aceitadoraDeConexao = null;

        try {
            aceitadoraDeConexao = new AceitadoraDeConexao(PORTA_PADRAO, clientes);
            aceitadoraDeConexao.start();
        }
        catch (Exception erro) {
            System.err.println ("Escolha uma porta apropriada e liberada para uso!\n");
            return;
        }
    }
}
