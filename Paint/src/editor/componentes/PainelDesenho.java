package editor.componentes;

import java.awt.image.BufferedImage;
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

    private Figura figuraDesenhada;

    private Color corInterior = Color.GRAY;
    private Color corContorno = Color.BLACK;

    private Font fonte = new Font("Arial", Font.PLAIN, 24);

    private BufferedImage bufferDeImagem;

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

    public Vector<Figura> getFiguras() {
        return this.figuras;
    }

    public void setFiguras(Vector<Figura> fig) {
        this.figuras = fig;
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
        if (figuras.size() > 0) {
            for (Figura figura : figuras)
            figura.torneSeVisivel(g);
        }
    }

    public void mousePressed(MouseEvent e) {
        criarBufferDeFiguras();

        switch (acao) {
            case Ponto:
                figuras.add(new Ponto(e.getX(), e.getY(), corContorno));
                figuras.get(figuras.size() - 1).torneSeVisivel(this.getGraphics());
                acao = Acao.Ponto;
                this.janela.setMensagem("clique no local do ponto desejado");
                break;
            
            case InicioReta:
                ponto = new Ponto(e.getX(), e.getY(), corContorno);
                acao = Acao.FimReta;
                this.janela.setMensagem("solte para finalizar a reta");
                break;

            case InicioCirculo:
                ponto = new Ponto(e.getX(), e.getY());
                acao = Acao.FimCirculo;
                this.janela.setMensagem("solte para finalizar o círculo");
                break;

            case InicioElipse:
                ponto = new Ponto(e.getX(), e.getY());
                acao = Acao.FimElipse;
                this.janela.setMensagem("solte para finalizar a elipse");
                break;

            case InicioQuadrado:
                ponto = new Ponto(e.getX(), e.getY());
                acao = Acao.FimQuadrado;
                this.janela.setMensagem("solte para finalizar o quadrado");
                break;

            case InicioRetangulo:
                ponto = new Ponto(e.getX(), e.getY());
                acao = Acao.FimRetangulo;
                this.janela.setMensagem("solte para finalizar o retângulo");
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
                    acao = Acao.InicioPoligono;
                    this.janela.setMensagem("clique no ponto inicial do polígono");
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

                this.janela.setMensagem("pressione [ENTER] para finalizar o texto");
                this.grabFocus();
                break;

            default:
                break;
        }
    }

    public void mouseReleased(MouseEvent e) {
        switch (acao) {
            case FimReta:
                acao = Acao.InicioReta;

                this.janela.setMensagem("clique e arraste para desenhar a reta");
                break;

            case FimCirculo:
                acao = Acao.InicioCirculo;

                this.janela.setMensagem("clique e arraste para desenhar o círculo");
                break;

            case FimElipse:
                acao = Acao.InicioElipse;

                this.janela.setMensagem("clique e arraste para desenhar a elipse");
                break;

            case FimQuadrado:
                acao = Acao.InicioQuadrado;

                this.janela.setMensagem("clique e arraste para desenhar o quadrado");
                break;

            case FimRetangulo:
                acao = Acao.InicioRetangulo;

                this.janela.setMensagem("clique e arraste para desenhar o retângulo");
                break;

            default:
                break;
        }

        if (figuraDesenhada != null) {
            figuras.add(figuraDesenhada);
        }
        
        figuraDesenhada = null;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        switch (acao) {
            case FimReta:
                figuraDesenhada = new Linha(ponto.getX(), ponto.getY(), e.getX(), e.getY(), corContorno);
                break;

            case FimCirculo:
                figuraDesenhada = new Circulo(ponto.getX(), ponto.getY(), e.getX(), e.getY(), corContorno, corInterior);
                break;

            case FimElipse:
                figuraDesenhada = new Elipse(ponto.getX(), ponto.getY(), e.getX(), e.getY(), corContorno, corInterior);
                break;

            case FimQuadrado:
                figuraDesenhada = new Quadrado(ponto.getX(), ponto.getY(), e.getX(), e.getY(), corContorno,
                        corInterior);
                break;

            case FimRetangulo:
                figuraDesenhada = new Retangulo(ponto.getX(), ponto.getY(), e.getX(), e.getY(), corContorno, corInterior);
                break;

            default:
                break;
        }
        
        if (figuraDesenhada != null) {
            desenharBufferDeFiguras();
            figuraDesenhada.torneSeVisivel(this.getGraphics());
        }
    }

    public void mouseMoved(MouseEvent e) {
        this.janela.setCoordenada(e.getX(), e.getY());
    }

    public void keyTyped(KeyEvent keyEvent) {
        if (acao == Acao.MeioTexto) {
            switch (keyEvent.getKeyChar()) {
                case '\n':  // Enter
                    limparAcao();
                    break;

                case '\b':  // Apagar
                    if (texto.getTexto().length() > 0) {
                        texto.setTexto(texto.getTexto().substring(0, texto.getTexto().length() - 1));

                        desenharBufferDeFiguras();
                        texto.torneSeVisivel(this.getGraphics());
                    }
                    break;

                default:
                    if (!keyEvent.isActionKey() && keyEvent.getModifiersEx() != InputEvent.CTRL_DOWN_MASK) {
                        texto.setTexto(texto.getTexto() + keyEvent.getKeyChar());
                        texto.torneSeVisivel(this.getGraphics());
                    }
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

    private void criarBufferDeFiguras() {
        BufferedImage imagem = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graficos = imagem.createGraphics();

        new Retangulo(0, 0, 1920, 1080, this.getBackground(), this.getBackground()).torneSeVisivel(graficos);
        
        if (figuras.size() > 0) {
            for (Figura figura : figuras)
                figura.torneSeVisivel(graficos);
        }

        graficos.dispose();

        this.bufferDeImagem = imagem;
    }

    private void desenharBufferDeFiguras() {
        this.getGraphics().drawImage(bufferDeImagem, 0, 0, this);
    }
}