package comunicacao;

public class PedidoSalvamento extends Comunicado{
    private static final long serialVersionUID = 1L;
    
    private String ipCliente; 
    private Desenho desenho;
    
    public PedidoSalvamento (String idCliente, Desenho des)
    {
        this.ipCliente = idCliente;
        this.desenho = des;
    }
    
    public String getIpCliente() {
        return this.ipCliente;
    }
    
    public Desenho getDesenho ()
    {
        return this.desenho;
    }
}