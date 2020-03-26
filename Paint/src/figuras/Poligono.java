package figuras;
import java.awt.*;
import java.util.*;
 
public class Poligono extends Figura
{
    protected Vector<Linha> lados;
    protected Color corInterior;
    
    public Poligono ()
    {
        this.lados = new Vector<>();
    }

    public void adicionaLados(Linha lado){
        lados.addElement(lado);
    }

    public int getQtdLados() {
        return lados.size();
    }
    
    public Ponto getPrimeiroPonto() {
        return lados.firstElement().p1;
    }

    // public boolean estaPertoPontoInicial(int outroX, int outroY) {
    //     int xInicial  = this.getPrimeiroPonto().getX();
    //     int yInicial  = this.getPrimeiroPonto().getY();

    //     if (outroX - 10 >= xInicial || outroX + 10 <= xInicial)
    //         if (outroY - 10 >= yInicial || outroY + 10 <= yInicial)
    //             return true;

    //     return false;
    // }

    public Vector<Linha> getLados()
    {
        return this.lados;
    }

    public void setCorInterior(Color corInterior)
    {
    	this.corInterior = corInterior;
    }

    public Color getCorInterior()
    {
    	return this.corInterior;
    }

    // public Poligono (Color corContorno)
    // {
    //     super(corContorno);

    //     this.p1 = new Ponto (x1, y1, corContorno);
    //     this.p2 = new Ponto (x2, y2, corContorno);
    // }

    // TODO implement
    public Poligono (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x1  = Integer.parseInt(quebrador.nextToken());
        int   y1  = Integer.parseInt(quebrador.nextToken());

        int   x2  = Integer.parseInt(quebrador.nextToken());
        int   y2  = Integer.parseInt(quebrador.nextToken());

        Color corContorno = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B

        //this.p1  = new Ponto (x1,y1,corContorno);
        //this.p2  = new Ponto (x2,y2,corContorno);
        this.corContorno = corContorno;
    }

    // TODO implement
    public void torneSeVisivel (Graphics g)
    {
        g.setColor(this.corContorno);
        g.drawLine(this.lados.lastElement().p1.x,   //FIXME: percorrer lados e desenhar todas linhas
                    this.lados.lastElement().p1.y,
                    this.lados.lastElement().p2.x,
                    this.lados.lastElement().p2.y);  
    }
    

    // TODO implement
    public String toString()
    {
        return "r:" +
            //    this.p1.getX() +
            //    ":" +
            //    this.p1.getY() +
            //    ":" +
            //    this.p2.getX() +
            //    ":" +
            //    this.p2.getY() +
               ":" +
               this.getCorContorno().getRed() +
               ":" +
               this.getCorContorno().getGreen() +
               ":" +
               this.getCorContorno().getBlue();
    }
}