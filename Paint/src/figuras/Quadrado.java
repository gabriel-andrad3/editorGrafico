package figuras;
import java.awt.Color;
import java.awt.Graphics;

public class Quadrado extends Retangulo
{
    public Quadrado (int x1, int y1, int x2, int y2)
    {
    	super(x1, y1, x2, y2, Color.BLACK);
    }
    
    public Quadrado (int x1, int y1, int x2, int y2, Color corContorno)
    {
    	super(x1, y1, x2, y2, corContorno, new Color(255, 255, 255, 0));
    }
    
    public Quadrado (int x1, int y1, int x2, int y2, Color corContorno, Color corInterior)
    {
	    super(x1, y1, x2, y2, corContorno, corInterior);		   
    }
    
    // TODO: Revisar quando implementar carregamento de arquivo
    public Quadrado (String s)
    {
    	super(s);
    }

    public void setP1 (int x, int y)
    {
        this.p1 = new Ponto(x, y);
    }

    public void setP2 (int x, int y)
    {
        this.p2 = new Ponto(x, y);
    }
    
    public void setCorInterior(Color corInterior)
    {
    	this.corInterior = corInterior;
    }

    public Ponto getP1()
    {
        return this.p1;
    }

    public Ponto getP2()
    {
        return this.p2;
    }
    
    public Color getCorInterior()
    {
    	return this.corInterior;
    }

    public void torneSeVisivel(Graphics g)
    {
    	int x = Math.min(this.p1.getX(), this.p2.getX());
    	int y = Math.min(this.p1.getY(), this.p2.getY());
    	
    	int largura = Math.max(this.p1.getX(), this.p2.getX()) - x;
		int altura = largura;
    	
		if (this.corInterior.getAlpha() == 255) {
			g.setColor(this.corInterior);
		    g.fillRect(x, y, largura + 1, altura + 1);
		}
		
        g.setColor(this.corContorno);
        g.drawRect(x, y, largura, altura);
    }

    // TODO: Revisar quando implementar carregamento de arquivo
    public String toString()
    {
        return "e:" +
               this.p1.getX() +
               ":" +
               this.p1.getY() +
               ":" +
               this.p2.getX() +
               ":" +
               this.p2.getY() +
               ":" +
               this.getCorContorno().getRed() +
               ":" +
               this.getCorContorno().getGreen() +
               ":" +
               this.getCorContorno().getBlue() +
               ":" +
               this.getCorInterior().getRed() +
               ":" +
               this.getCorInterior().getGreen() +
               ":" +
               this.getCorInterior().getBlue();
    }
}
