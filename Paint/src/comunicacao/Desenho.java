package comunicacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Desenho implements Serializable {

    private static final long serialVersionUID = 934581887387077915L;

    private String nome;
	private ArrayList<String> figuras;
	
    public Desenho(String nome)
    {
        this.nome = nome;
		this.figuras = new ArrayList<String>();
    }
	
    // fig vai ter como forma o retorno do metodo toString de alguma das classes
    // herdadas da classe Figura por exemplo, r:11:22:33:44:55:66:77, para uma
    // linha que vai do ponto com coordenada 11,22 ao ponto com coordenada 33,44
    // e com cor 55:66:77 (55 de red, 66 de green e 77 de blue).
    public void addFigura(String figura)
	{
		figuras.add(figura);
	}
  
    public int getQtd ()
    {
        return figuras.size();
    }
    
    public String getFigura (int i)
    {
        return figuras.get(i);
    }

    public List<String> getFiguras()
    {
        return figuras;
    }
    
    public String getNome() {
        return this.nome;
    }
}