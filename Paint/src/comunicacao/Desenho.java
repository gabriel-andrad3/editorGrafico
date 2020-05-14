package comunicacao;

import java.util.ArrayList;

public class Desenho {
    private String nome;
	private String dataCriacao;
	private String dataUltimaAtualizacao;
	private ArrayList<String> conteudo;
	
    
    public Desenho(String nom, String dtCri, String dtUltAtu)
    {
        nome = nom;
        dataCriacao = dtCri;
        dataUltimaAtualizacao = dtUltAtu;
		conteudo = new ArrayList<String>();
     }
	
  // fig vai ter como forma o retorno do metodo toString de alguma das classes
  // herdadas da classe Figura por exemplo, r:11:22:33:44:55:66:77, para uma
  // linha que vai do ponto com coordenada 11,22 ao ponto com coordenada 33,44
  // e com cor 55:66:77 (55 de red, 66 de green e 77 de blue).
    public void addFigura(String fig)
	{
		conteudo.add(fig);
	}
  
    public int getQtd ()
    {
        return conteudo.size();
    }
    
    public String getFigura (int i)
    {
        return conteudo.get(i);
    }
    // faça os getters para nome, dataCriacao e dataUltimaAtualizacao
}