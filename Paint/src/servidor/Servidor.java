package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static int porta = 12345;

    private static ServerSocket serverSocket;

    private void criarServidor(int porta) throws IOException {
        serverSocket = new ServerSocket(porta);
    }

    private static Socket esperaConexao() throws IOException {
        Socket socket = serverSocket.accept();
        return socket;
    }

    public static void main(String[] args) {
        try {
                Servidor servidor = new Servidor();
                servidor.criarServidor(porta);
        
                while (true) {
                Socket cliente = servidor.esperaConexao();
                System.out.println("Cliente conectado");
            }
        }
        catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
