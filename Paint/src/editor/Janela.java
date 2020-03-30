package editor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import editor.componentes.PainelDesenho;
import editor.componentes.listeners.*;

import javax.imageio.*;
import java.io.*;
import java.util.*;

import figuras.*;

public class Janela extends JFrame {
    protected static final long serialVersionUID = 1L;

    protected JButton btnPonto = new JButton("Ponto");
    protected JButton btnLinha = new JButton("Linha");
    protected JButton btnCirculo = new JButton("Círculo");
    protected JButton btnElipse = new JButton("Elipse");
    protected JButton btnQuadrado = new JButton("Quadrado");
    protected JButton btnRetangulo = new JButton("Retângulo");
    protected JButton btnPoligono = new JButton("Polígono");
    protected JButton btnTexto = new JButton("Texto");
    protected JButton btnCores = new JButton("Cores");
    protected JButton btnFonte = new JButton("Fonte");
    protected JButton btnAbrir = new JButton("Abrir");
    protected JButton btnSalvar = new JButton("Salvar");
    protected JButton btnApagar = new JButton("Apagar");
    protected JButton btnSair = new JButton("Sair");

    protected PainelDesenho pnlDesenho = new PainelDesenho(this);

    protected JLabel stsMensagem = new JLabel("Mensagem:"), stsCoordenada = new JLabel("Coordenada:");

    protected JPopupMenu mnuCores = new JPopupMenu();

    protected JMenuItem itmCorContorno = new JMenuItem("Contorno");
    protected JMenuItem itmCorInterior = new JMenuItem("Interior");

    protected Acao acao = Acao.Nenhuma;

    protected Color corContorno = Color.BLACK;
    protected Color corInterior = Color.LIGHT_GRAY;

    protected Font fonte = new Font("Arial", Font.PLAIN, 12);

    protected Ponto p1;

    protected Poligono polig;
    protected Linha ladoPolig;

    protected Texto texto;

    protected Vector<Figura> figuras = new Vector<Figura>();

    public Janela() {
        super("Editor Gráfico");

        try {
            Image btnPontoImg = ImageIO.read(getClass().getResource("/resources/ponto.jpg"));
            btnPonto.setIcon(new ImageIcon(btnPontoImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo ponto.jpg não foi encontrado", "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnLinhaImg = ImageIO.read(getClass().getResource("/resources/linha.jpg"));
            btnLinha.setIcon(new ImageIcon(btnLinhaImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo linha.jpg não foi encontrado", "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnCirculoImg = ImageIO.read(getClass().getResource("/resources/circulo.jpg"));
            btnCirculo.setIcon(new ImageIcon(btnCirculoImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo circulo.jpg não foi encontrado", "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnElipseImg = ImageIO.read(getClass().getResource("/resources/elipse.jpg"));
            btnElipse.setIcon(new ImageIcon(btnElipseImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo elipse.jpg não foi encontrado", "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnQuadradoImg = ImageIO.read(getClass().getResource("/resources/quadrado.jpg"));
            btnQuadrado.setIcon(new ImageIcon(btnQuadradoImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo elipse.jpg não foi encontrado", "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnRetanguloImg = ImageIO.read(getClass().getResource("/resources/retangulo.jpg"));
            btnRetangulo.setIcon(new ImageIcon(btnRetanguloImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo retangulo.jpg não foi encontrado", "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnPoligonoImg = ImageIO.read(getClass().getResource("/resources/poligono.jpg"));
            btnPoligono.setIcon(new ImageIcon(btnPoligonoImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo poligono.jpg não foi encontrado", "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnTextoImg = ImageIO.read(getClass().getResource("/resources/texto.jpg"));
            btnTexto.setIcon(new ImageIcon(btnTextoImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo cores.jpg não foi encontrado", "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnCoresImg = ImageIO.read(getClass().getResource("/resources/cores.jpg"));
            btnCores.setIcon(new ImageIcon(btnCoresImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo cores.jpg não foi encontrado", "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnFonteImg = ImageIO.read(getClass().getResource("/resources/fonte.jpg"));
            btnFonte.setIcon(new ImageIcon(btnFonteImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo cores.jpg não foi encontrado", "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnAbrirImg = ImageIO.read(getClass().getResource("/resources/abrir.jpg"));
            btnAbrir.setIcon(new ImageIcon(btnAbrirImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo abrir.jpg não foi encontrado", "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnSalvarImg = ImageIO.read(getClass().getResource("/resources/salvar.jpg"));
            btnSalvar.setIcon(new ImageIcon(btnSalvarImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo salvar.jpg não foi encontrado", "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnApagarImg = ImageIO.read(getClass().getResource("/resources/apagar.jpg"));
            btnApagar.setIcon(new ImageIcon(btnApagarImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo apagar.jpg não foi encontrado", "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try {
            Image btnSairImg = ImageIO.read(getClass().getResource("/resources/sair.jpg"));
            btnSair.setIcon(new ImageIcon(btnSairImg));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Arquivo sair.jpg não foi encontrado", "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        btnPonto.addActionListener(new DesenhoDePonto(this));
        btnLinha.addActionListener(new DesenhoDeReta(this));
        btnCirculo.addActionListener(new DesenhoDeCirculo(this));
        btnElipse.addActionListener(new DesenhoDeElipse(this));
        btnQuadrado.addActionListener(new DesenhoDeQuadrado(this));
        btnRetangulo.addActionListener(new DesenhoDeRetangulo(this));
        btnPoligono.addActionListener(new DesenhoDePoligono(this));
        btnTexto.addActionListener(new DesenhoDeTexto(this));
        btnCores.addMouseListener(new AbreMenuCores(this));
        btnFonte.addActionListener(new SelecaoDeFonte(this));

        itmCorContorno.addActionListener(new SelecaoDeCorContorno(this));
        itmCorInterior.addActionListener(new SelecaoDeCorInterior(this));

        JPanel pnlBotoes = new JPanel();
        FlowLayout flwBotoes = new FlowLayout();
        pnlBotoes.setLayout(flwBotoes);

        pnlBotoes.add(btnAbrir);
        pnlBotoes.add(btnSalvar);
        pnlBotoes.add(btnPonto);
        pnlBotoes.add(btnLinha);
        pnlBotoes.add(btnCirculo);
        pnlBotoes.add(btnElipse);
        pnlBotoes.add(btnQuadrado);
        pnlBotoes.add(btnRetangulo);
        pnlBotoes.add(btnPoligono);
        pnlBotoes.add(btnTexto);
        pnlBotoes.add(btnCores);
        pnlBotoes.add(btnFonte);
        pnlBotoes.add(btnApagar);
        pnlBotoes.add(btnSair);

        mnuCores.add(itmCorContorno);
        mnuCores.add(itmCorInterior);

        JPanel pnlStatus = new JPanel();
        GridLayout grdStatus = new GridLayout(1, 2);
        pnlStatus.setLayout(grdStatus);

        pnlStatus.add(stsMensagem);
        pnlStatus.add(stsCoordenada);

        Container cntForm = this.getContentPane();
        cntForm.setLayout(new BorderLayout());
        cntForm.add(pnlBotoes, BorderLayout.NORTH);
        cntForm.add(pnlDesenho, BorderLayout.CENTER);
        cntForm.add(pnlStatus, BorderLayout.SOUTH);

        this.addWindowListener(new FechamentoDeJanela());

        this.setSize(1400, 500);
        this.setVisible(true);
    }

    public PainelDesenho getPainelDesenho() {
        return this.pnlDesenho;
    }

    public JPopupMenu getMenuCores() {
        return this.mnuCores;
    }

    public void setMensagem(String mensagem) {
        this.stsMensagem.setText("Mensagem: " + mensagem);
    }

    public void setCoordenada(int x, int y) {
        this.stsCoordenada.setText("Coordenada: " + x + "," + y);
    }

    protected static class FechamentoDeJanela extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }
}
