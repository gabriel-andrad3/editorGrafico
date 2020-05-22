package comunicacao;

import java.io.*;
import java.net.*;
import java.util.concurrent.Semaphore;

public class Parceiro
{
    private final Socket conexao;
    private final ObjectInputStream receptor;
    private final ObjectOutputStream transmissor;

    private Comunicado proximoComunicado = null;

    private final Semaphore mutEx = new Semaphore(1, true);

    public Parceiro(final Socket conexao, final ObjectInputStream receptor, final ObjectOutputStream transmissor) throws Exception {
        if (conexao == null)
            throw new Exception("Conexao ausente");

        if (receptor == null)
            throw new Exception("Receptor ausente");

        if (transmissor == null)
            throw new Exception("Transmissor ausente");

        this.conexao = conexao;
        this.receptor = receptor;
        this.transmissor = transmissor;
    }

    public void receba(final Comunicado x) throws Exception {
        try {
            this.transmissor.writeObject(x);
            this.transmissor.flush();
        } catch (final IOException erro) {
            throw new Exception("Erro de transmissao");
        }
    }

    public Comunicado espie() throws Exception {
        try {
            this.mutEx.acquireUninterruptibly();

            if (this.proximoComunicado == null)
                this.proximoComunicado = (Comunicado) this.receptor.readObject();

            this.mutEx.release();

            return this.proximoComunicado;
        } catch (final Exception erro) {
            throw new Exception("Erro de recepcao");
        }
    }

    public Comunicado envie() throws Exception {
        try {
            if (this.proximoComunicado == null)
                this.proximoComunicado = (Comunicado) this.receptor.readObject();

            final Comunicado ret = this.proximoComunicado;

            this.proximoComunicado = null;
            
            return ret;
        } catch (final Exception erro) {
            throw new Exception("Erro de recepcao");
        }
    }

    public void adeus() throws Exception {
        try {
            this.transmissor.close();
            this.receptor.close();
            this.conexao.close();
        } catch (final Exception erro) {
            throw new Exception ("Erro de desconexao");
        }
    }

    public String getIp() {
        return (((InetSocketAddress) this.conexao.getRemoteSocketAddress()).getAddress()).toString();
    }
}
