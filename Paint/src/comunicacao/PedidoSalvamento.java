package comunicacao;

public class PedidoSalvamento extends Comunicado{
    private static final long serialVersionUID = 1L;
    
    private String ipCliente; 
    private String nome;
    private Desenho desenho;
    
    public PedidoSalvamento (String idCliente, String nome, Desenho des)
    {
        this.ipCliente = idCliente;
        this.nome = nome;
        this.desenho = des;
    }
    
    public String getIpCliente() {
        return this.ipCliente;
    }
    
    public String getNome() {
        return this.nome;
    }

    public Desenho getDesenho ()
    {
        return this.desenho;
    }
}