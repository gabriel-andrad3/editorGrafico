package comunicacao;

public class EncerrarConexao extends Comunicado {
    private static final long serialVersionUID = 1L;

    private String ipCliente;
    
    public EncerrarConexao(String ipCliente) {
        this.ipCliente = ipCliente;
    }

    public String getIpCliente() {
        return this.ipCliente;
    }
}