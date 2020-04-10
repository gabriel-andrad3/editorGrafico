package figuras;
import java.awt.Color;
import java.awt.Graphics;
import java.util.StringTokenizer;

public class Retangulo extends Figura
{
	protected Ponto p1, p2;
	protected Color corInterior;
	
    public Retangulo (int x1, int y1, int x2, int y2)
    {
    	this(x1, y1, x2, y2, Color.BLACK);
    }
    
    public Retangulo (int x1, int y1, int x2, int y2, Color corContorno)
    {
	    this(x1, y1, x2, y2, corContorno, new Color(255, 255, 255, 0));
    }
    
    public Retangulo (int x1, int y1, int x2, int y2, Color corContorno, Color corInterior)
    {
	    super(corContorno);
	
	    this.p1 = new Ponto(x1, y1);
	    this.p2 = new Ponto(x2, y2);
	    
	    this.corInterior = corInterior;
    }
    
    // TODO: Revisar quando implementar carregamento de arquivo
    public Retangulo (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int x1 = Integer.parseInt(quebrador.nextToken());
        int y1 = Integer.parseInt(quebrador.nextToken());

        int x2 = Integer.parseInt(quebrador.nextToken());
        int y2 = Integer.parseInt(quebrador.nextToken());

        Color corContorno = new Color(
        		Integer.parseInt(quebrador.nextToken()),
        		Integer.parseInt(quebrador.nextToken()),
        		Integer.parseInt(quebrador.nextToken()));
        
        Color corInterior = new Color(
        		Integer.parseInt(quebrador.nextToken()),
                Integer.parseInt(quebrador.nextToken()),
                Integer.parseInt(quebrador.nextToken()));
        
        this.p1 = new Ponto(x1, y1);
        this.p2 = new Ponto(x2, y2);
        this.corContorno = corContorno;
        this.corInterior = corInterior;
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
		int altura = Math.max(this.p1.getY(), this.p2.getY()) - y;
    	
		if (this.corInterior.getAlpha() == 255) {
			g.setColor(this.corInterior);
		    g.fillRect(x, y, largura + 1, altura + 1);
		}
		
        g.setColor(this.corContorno);
        g.drawRect(x, y, largura, altura);
    }

    public String toString()
    {
        return "r:" +
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