package editor.componentes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import editor.Acao;
import editor.Janela;

import java.util.*;

import figuras.*;

public class PainelDesenho extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    private static final long serialVersionUID = 1L;

    private Janela janela;

    private Acao acao = Acao.Nenhuma;

    private Vector<Figura> figuras = new Vector<Figura>();

    private Ponto ponto;
    private Poligono polig;
    private Linha ladoPolig;
    private Texto texto;

    private Color corInterior = Color.GRAY;
    private Color corContorno = Color.BLACK;

    private Font fonte;

    public PainelDesenho(Janela janela) {
        super();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);

        this.janela = janela;
    }

    public Color getCorContorno() {
        return this.corContorno;
    }

    public Color getCorInterior() {
        return this.corInterior;
    }

    public Font getFonte() {
        return this.fonte;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public void setCorContorno(Color cor) {
        this.corContorno = cor;
    }

    public void setCorInterior(Color cor) {
        this.corInterior = cor;
    }

    public void setFonte(Font fonte) {
        this.fonte = fonte;
    }

    public void paint(Graphics g) {
        for (Figura figura : figuras)
            figura.torneSeVisivel(g);
    }

    public void mousePressed(MouseEvent e) {
        switch (acao) {
            case Ponto:
                figuras.add(new Ponto(e.getX(), e.getY(), corContorno));
                figuras.get(figuras.size() - 1).torneSeVisivel(this.getGraphics());
                limparAcao();
                break;

            case InicioReta:
                ponto = new Ponto(e.getX(), e.getY(), corContorno);
                acao = Acao.FimReta;
                this.janela.setMensagem("clique o ponto final da reta");
                break;

            case FimReta:
                figuras.add(new Linha(ponto.getX(), ponto.getY(), e.getX(), e.getY(), corContorno));
                figuras.get(figuras.size() - 1).torneSeVisivel(this.getGraphics());
                limparAcao();
                break;

            case InicioCirculo:
                ponto = new Ponto(e.getX(), e.getY());
                acao = Acao.FimCirculo;
                this.janela.setMensagem("clique o ponto final do circulo");
                break;

            case FimCirculo:
                figuras.add(new Circulo(ponto.getX(), ponto.getY(), e.getX(), e.getY(), corContorno, corInterior));
                figuras.get(figuras.size() - 1).torneSeVisivel(this.getGraphics());
                limparAcao();
                break;

            case InicioElipse:
                ponto = new Ponto(e.getX(), e.getY());
                acao = Acao.FimElipse;
                this.janela.setMensagem("clique o ponto final da elipse");
                break;

            case FimElipse:
                figuras.add(new Elipse(ponto.getX(), ponto.getY(), e.getX(), e.getY(), corContorno, corInterior));
                figuras.get(figuras.size() - 1).torneSeVisivel(this.getGraphics());
                limparAcao();
                break;

            case InicioQuadrado:
                ponto = new Ponto(e.getX(), e.getY());
                acao = Acao.FimQuadrado;
                this.janela.setMensagem("clique o ponto final do quadrado");
                break;

            case FimQuadrado:
                figuras.add(new Quadrado(ponto.getX(), ponto.getY(), e.getX(), e.getY(), corContorno, corInterior));
                figuras.get(figuras.size() - 1).torneSeVisivel(this.getGraphics());
                limparAcao();
                break;

            case InicioRetangulo:
                ponto = new Ponto(e.getX(), e.getY());
                acao = Acao.FimRetangulo;
                this.janela.setMensagem("clique o ponto final do retângulo");
                break;

            case FimRetangulo:
                figuras.add(new Retangulo(ponto.getX(), ponto.getY(), e.getX(), e.getY(), corContorno, corInterior));
                figuras.get(figuras.size() - 1).torneSeVisivel(this.getGraphics());
                limparAcao();
                break;

            case InicioPoligono:
                ponto = new Ponto(e.getX(), e.getY());
                polig = new Poligono();
                acao = Acao.DurantePoligono;
                this.janela.setMensagem("clique o ponto final desse lado do polígono");
                break;

            case DurantePoligono:
                ladoPolig = new Linha(ponto.getX(), ponto.getY(), e.getX(), e.getY(), corContorno);
                polig.adicionaLados(ladoPolig);

                // FimPoligono
                if (polig.getQtdLados() >= 3 && e.getX() == polig.getPrimeiroPonto().getX()
                        && e.getY() == polig.getPrimeiroPonto().getY()) { // FIXME: mudar para pegar uma área proxima
                    polig.setCorInterior(corInterior); // FIXME
                    figuras.add(polig);
                    polig.torneSeVisivel(this.getGraphics());
                    limparAcao();
                } else {
                    polig.torneSeVisivel(this.getGraphics());
                    ponto.setX(e.getX());
                    ponto.setY(e.getY());
                    this.janela.setMensagem("clique o ponto final desse lado do polígono");
                }
                break;

            case InicioTexto:
                texto = new Texto(e.getX(), e.getY(), "", corContorno, fonte);
                figuras.add(texto);

                acao = Acao.MeioTexto;

                this.janela.setMensagem("pressione [ENTER] ou [TAB] ou clique para finalizar o texto");
                this.grabFocus();
                break;

            default:
                limparAcao();
                break;
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        this.janela.setCoordenada(e.getX(), e.getY());
    }

    public void keyTyped(KeyEvent keyEvent) {
        if (acao == Acao.MeioTexto) {
            switch (keyEvent.getKeyChar()) {
                case '\b':
                    if (texto.getTexto().length() > 0) {
                        texto.setTexto(texto.getTexto().substring(0, texto.getTexto().length() - 1));
                        new Retangulo(0, 0, 1920, 1080, this.getBackground(), this.getBackground())
                                .torneSeVisivel(this.getGraphics());
                        for (Figura figura : figuras)
                            figura.torneSeVisivel(this.getGraphics());
                    }
                    break;

                case '\n':
                    limparAcao();
                    break;

                default:
                    texto.setTexto(texto.getTexto() + keyEvent.getKeyChar());
                    texto.torneSeVisivel(this.getGraphics());
            }
        }
    }

    public void keyPressed(KeyEvent keyEvent) {
    }

    public void keyReleased(KeyEvent keyEvent) {
    }

    private void limparAcao() {
        acao = Acao.Nenhuma;
        this.janela.setMensagem("");
    }
}