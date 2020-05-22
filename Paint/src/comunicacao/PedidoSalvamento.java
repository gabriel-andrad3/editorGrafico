package comunicacao;

public class PedidoSalvamento implements Comunicado{

    private static final long serialVersionUID = 242220071685655080L;
    
    private Desenho desenho;
    
    public PedidoSalvamento (Desenho des)
    {
        this.desenho = des;
    }

    public Desenho getDesenho ()
    {
        return this.desenho;
    }
}