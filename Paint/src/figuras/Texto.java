package figuras;

import java.awt.*;
import java.util.StringTokenizer;

public class Texto extends Figura {
    private Ponto p1;
    private String texto;
    private Font fonte;

    public Texto(int x1, int y1) { this(x1, y1, ""); }

    public Texto(int x1, int y1, String texto) { this(x1, y1, texto, Color.BLACK); }

    public Texto(int x1, int y1, String texto, Color corContorno) { this(x1, y1, texto, corContorno, Font.getFont("arial")); }

    public Texto(int x1, int y1, String texto, Color corContorno, Font fonte)
    {
        super(corContorno);

        this.p1 = new Ponto(x1, y1);
        this.texto = texto;
        this.fonte = fonte;
    }

    public Texto (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int x1 = Integer.parseInt(quebrador.nextToken());
        int y1 = Integer.parseInt(quebrador.nextToken());

        Color corContorno = new Color(
                Integer.parseInt(quebrador.nextToken()),
                Integer.parseInt(quebrador.nextToken()),
                Integer.parseInt(quebrador.nextToken()));

        String texto = quebrador.nextToken();

        Font fonte = Font.getFont(quebrador.nextToken());

        this.p1 = new Ponto(x1, y1);
        this.texto = texto;
        this.corContorno = corContorno;
        this.fonte = fonte;
    }

    public void setP1 (int x, int y)
    {
        this.p1 = new Ponto(x, y);
    }

    public void setTexto(String texto) { this.texto = texto; }

    public void setFonte(Font fonte) { this.fonte = fonte; }

    public Ponto getP1()
    {
        return this.p1;
    }

    public String getTexto() { return this.texto; }

    public Font getFonte() { return this.fonte; }

    public void torneSeVisivel(Graphics g)
    {
        g.setColor(this.corContorno);
        g.setFont(this.fonte);

        g.drawString(this.texto, this.p1.getX(), this.p1.getY());
    }

    public String toString()
    {
        return "t:" +
                this.p1.getX() +
                ":" +
                this.p1.getY() +
                ":" +
                this.getCorContorno().getRed() +
                ":" +
                this.getCorContorno().getGreen() +
                ":" +
                this.getCorContorno().getBlue() +
                ":" +
                this.getTexto() +
                ":" +
                this.getFonte().getName();
    }
}
