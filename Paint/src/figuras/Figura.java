package figuras;
import java.awt.*;

public abstract class Figura
{
	protected Color corContorno;
    
    protected Figura ()
    {
        this (Color.BLACK);
    }
	
    protected Figura (Color corContorno)
    {
        this.corContorno = corContorno;
    }
	  
    public void setCorContorno (Color corContorno)
    {
        this.corContorno = corContorno;
    }
	  
    public Color getCorContorno()
    {
    	return this.corContorno;
    }

  //public abstract boolean equals         (Object obj);
  //public abstract int     hashCode       ();
  //public abstract Object  clone          ();
    public abstract String  toString       ();
    public abstract void    torneSeVisivel (Graphics g);
}
