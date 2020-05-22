package comunicacao;

import java.util.ArrayList;

public class Desenhos {
   
    private ArrayList<Desenho> desenhos;
    
    public Desenhos()
    {
		desenhos = new ArrayList<Desenho>();
    }
	
	public void addDesenho(Desenho novo)
	{
		desenhos.add(novo);
	}
    
    public double getQtd ()
    {
        return desenhos.size();
    }
	
	public Desenho getDesenho(int i)
	{
		return desenhos.get(i);
	}
}