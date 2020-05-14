package comunicacao;

public class PedidoDesenhos extends Comunicado {
    private static final long serialVersionUID = 1L;

    private String ipCliente; 
    
    public PedidoDesenhos (String ipCliente)
    {
        this.ipCliente = ipCliente;
    }
    
    public String getIpCliente ()
    {
        return this.ipCliente;
    }
}