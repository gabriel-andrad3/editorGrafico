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

    public boolean estaPertoPontoInicial(int outroX, int outroY) {  // FIXME
        int xInicial  = this.getPrimeiroPonto().getX();
        int yInicial  = this.getPrimeiroPonto().getY();

        if (outroX - 10 >= xInicial || outroX + 10 <= xInicial)
            if (outroY - 10 >= yInicial || outroY + 10 <= yInicial)
                return true;

        return false;
    }

    public Vector<Linha> getLados()
    {
        return this.lados;
    }

    private Linha getLado(int pos) {
        return this.lados.elementAt(pos);
    }

    public void setCorInterior(Color corInterior)
    {
    	this.corInterior = corInterior;
    }

    public Color getCorInterior()
    {
    	return this.corInterior;
    }

     public void torneSeVisivel (Graphics g)
     {
         g.setColor(this.corContorno);

         for (int i=0; i<this.lados.size(); i++) {
            g.drawLine(getLado(i).p1.x,   
                        getLado(i).p1.y,
                        getLado(i).p2.x,
                        getLado(i).p2.y);   
        }
     }

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

        this.corContorno = corContorno;
    }
    
    // printando um conjunto de linhas
    public String toString()
    {
        String ret = "";
        
        for (int i=0; i<this.lados.size(); i++) {
            ret +=  getLado(i).toString();
            ret += "\n";
        }

        return ret;
    }
}