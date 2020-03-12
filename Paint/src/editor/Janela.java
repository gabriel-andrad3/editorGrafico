package editor;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import figuras.*;

public class Janela extends JFrame 
{
    protected static final long serialVersionUID = 1L;

    protected JButton btnPonto     = new JButton("Ponto"),
                      btnLinha     = new JButton("Linha"),
                      btnCirculo   = new JButton("Círculo"),
                      btnElipse    = new JButton("Elipse"),
                      btnQuadrado  = new JButton("Quadrado"),
                      btnRetangulo = new JButton("Retângulo"),
                      btnCores     = new JButton("Cores"),
                      btnAbrir     = new JButton("Abrir"),
                      btnSalvar    = new JButton("Salvar"),
                      btnApagar    = new JButton("Apagar"),
                      btnSair      = new JButton("Sair");

    protected MeuJPanel pnlDesenho = new MeuJPanel ();
    
    protected JLabel stsMensagem = new JLabel ("Mensagem:"),
                     stsCoordenada = new JLabel ("Coordenada:");
    
    protected JPopupMenu mnuCores = new JPopupMenu();
    
    protected JMenuItem itmCorContorno = new JMenuItem("Contorno");
    protected JMenuItem itmCorInterior = new JMenuItem("Interior");
    
    protected Acao acao = Acao.Nenhuma;

    protected Color corContorno = Color.BLACK;
    protected Color corInterior = Color.LIGHT_GRAY;
    
    protected Ponto p1;
    
    protected Vector<Figura> figuras = new Vector<Figura>();

    public Janela ()
    {        super("Editor Gráfico");

        try
        {
            Image btnPontoImg = ImageIO.read(getClass().getResource("/resources/ponto.jpg"));
            btnPonto.setIcon(new ImageIcon(btnPontoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo ponto.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnLinhaImg = ImageIO.read(getClass().getResource("/resources/linha.jpg"));
            btnLinha.setIcon(new ImageIcon(btnLinhaImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo linha.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnCirculoImg = ImageIO.read(getClass().getResource("/resources/circulo.jpg"));
            btnCirculo.setIcon(new ImageIcon(btnCirculoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo circulo.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnElipseImg = ImageIO.read(getClass().getResource("/resources/elipse.jpg"));
            btnElipse.setIcon(new ImageIcon(btnElipseImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo elipse.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            Image btnQuadradoImg = ImageIO.read(getClass().getResource("/resources/quadrado.jpg"));
            btnQuadrado.setIcon(new ImageIcon(btnQuadradoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo elipse.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            Image btnRetanguloImg = ImageIO.read(getClass().getResource("/resources/retangulo.jpg"));
            btnRetangulo.setIcon(new ImageIcon(btnRetanguloImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo elipse.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnCoresImg = ImageIO.read(getClass().getResource("/resources/cores.jpg"));
            btnCores.setIcon(new ImageIcon(btnCoresImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo cores.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnAbrirImg = ImageIO.read(getClass().getResource("/resources/abrir.jpg"));
            btnAbrir.setIcon(new ImageIcon(btnAbrirImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo abrir.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnSalvarImg = ImageIO.read(getClass().getResource("/resources/salvar.jpg"));
            btnSalvar.setIcon(new ImageIcon(btnSalvarImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo salvar.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnApagarImg = ImageIO.read(getClass().getResource("/resources/apagar.jpg"));
            btnApagar.setIcon(new ImageIcon(btnApagarImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo apagar.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnSairImg = ImageIO.read(getClass().getResource("/resources/sair.jpg"));
            btnSair.setIcon(new ImageIcon(btnSairImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo sair.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        btnPonto.addActionListener(new DesenhoDePonto());
        btnLinha.addActionListener(new DesenhoDeReta ());
        btnCirculo.addActionListener(new DesenhoDeCirculo());
        btnElipse.addActionListener(new DesenhoDeElipse());
        btnQuadrado.addActionListener(new DesenhoDeQuadrado());
        btnRetangulo.addActionListener(new DesenhoDeRetangulo());
        
        btnCores.addMouseListener(new AbreMenuCores());
        
        itmCorContorno.addActionListener(new SelecionaCorContorno(this));
        itmCorInterior.addActionListener(new SelecionaCorInterior(this));

        JPanel     pnlBotoes = new JPanel();
        FlowLayout flwBotoes = new FlowLayout(); 
        pnlBotoes.setLayout (flwBotoes);

        pnlBotoes.add (btnAbrir);
        pnlBotoes.add (btnSalvar);
        pnlBotoes.add (btnPonto);
        pnlBotoes.add (btnLinha);
        pnlBotoes.add (btnCirculo);
        pnlBotoes.add (btnElipse);
        pnlBotoes.add (btnQuadrado);
        pnlBotoes.add (btnRetangulo);
        pnlBotoes.add (btnCores);
        pnlBotoes.add (btnApagar);
        pnlBotoes.add (btnSair);
        
        mnuCores.add(itmCorContorno);
        mnuCores.add(itmCorInterior);

        JPanel     pnlStatus = new JPanel();
        GridLayout grdStatus = new GridLayout(1,2);
        pnlStatus.setLayout(grdStatus);

        pnlStatus.add(stsMensagem);
        pnlStatus.add(stsCoordenada);

        Container cntForm = this.getContentPane();
        cntForm.setLayout (new BorderLayout());
        cntForm.add (pnlBotoes,  BorderLayout.NORTH);
        cntForm.add (pnlDesenho, BorderLayout.CENTER);
        cntForm.add (pnlStatus,  BorderLayout.SOUTH);
        
        this.addWindowListener (new FechamentoDeJanela());

        this.setSize (1200,500);
        this.setVisible (true);
    }

    protected class MeuJPanel extends    JPanel 
                              implements MouseListener,
                                         MouseMotionListener
    {

	private static final long serialVersionUID = 1L;

	public MeuJPanel()
        {
            super();

            this.addMouseListener       (this);
            this.addMouseMotionListener (this);
        }

        public void paint (Graphics g)
        {
            for (int i=0 ; i<figuras.size(); i++)
                figuras.get(i).torneSeVisivel(g);
        }
        
        public void mousePressed (MouseEvent e)
        {
        	switch (acao) {
        	case Ponto:
                figuras.add(new Ponto (e.getX(), e.getY(), corContorno));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                limparAcao();
                break;
                
        	case InicioReta:
                p1 = new Ponto (e.getX(), e.getY(), corContorno);
                acao = Acao.FimReta;
                stsMensagem.setText("Mensagem: clique o ponto final da reta"); 
                break;
                
        	case FimReta:
                figuras.add (new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corContorno));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                limparAcao();
                break;
                
        	case InicioCirculo:
        		p1 = new Ponto(e.getX(), e.getY());
        		acao = Acao.FimCirculo;
        		stsMensagem.setText("Mensagem: clique o ponto final do circulo");
        		break;
                
        	case FimCirculo:
        		figuras.add(new Circulo(p1.getX(), p1.getY(), e.getX(), e.getY(), corContorno, corInterior));
        		figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
        		limparAcao();
        		break;
        		
        	case InicioElipse:
        		p1 = new Ponto(e.getX(), e.getY());
        		acao = Acao.FimElipse;
        		stsMensagem.setText("Mensagem: clique o ponto final da elipse");
        		break;
        		
        	case FimElipse:
        		figuras.add(new Elipse(p1.getX(), p1.getY(), e.getX(), e.getY(), corContorno, corInterior));
        		figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
        		limparAcao();
        		break;
        	
        	case InicioQuadrado:
        		p1 = new Ponto(e.getX(), e.getY());
        		acao = Acao.FimQuadrado;
        		stsMensagem.setText("Mensagem: clique o ponto final do quadrado");
        		break;
        		
        	case FimQuadrado:
        		figuras.add(new Quadrado(p1.getX(), p1.getY(), e.getX(), e.getY(), corContorno, corInterior));
        		figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
        		limparAcao();
        		break;
        		
        	case InicioRetangulo:
        		p1 = new Ponto(e.getX(), e.getY());
        		acao = Acao.FimRetangulo;
        		stsMensagem.setText("Mensagem: clique o ponto final do retângulo");
        		break;
        		
        	case FimRetangulo:
        		figuras.add(new Retangulo(p1.getX(), p1.getY(), e.getX(), e.getY(), corContorno, corInterior));
        		figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
        		limparAcao();
        		break;
        		
        	default:
        		limparAcao();
        		break;
        	}
        }
        
        public void mouseReleased (MouseEvent e)
        {}
        
        public void mouseClicked (MouseEvent e)
        {}
        
        public void mouseEntered (MouseEvent e)
        {}

        public void mouseExited (MouseEvent e)
        {}
        
        public void mouseDragged(MouseEvent e)
        {}

        public void mouseMoved(MouseEvent e)
        {
            stsCoordenada.setText("Coordenada: "+e.getX()+","+e.getY());
        }
        
        private void limparAcao() 
        {
        	acao = Acao.Nenhuma;
            stsMensagem.setText("Mensagem: ");
        }
    }

    protected class DesenhoDePonto implements ActionListener
    {
    	public void actionPerformed (ActionEvent e)    
    	{
	  		acao = Acao.Ponto;	
    		stsMensagem.setText("Mensagem: clique no local do ponto desejado");
    	}
    }

    protected class DesenhoDeReta implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
    		acao = Acao.InicioReta;
            stsMensagem.setText("Mensagem: clique no ponto inicial da reta");
        }
    }
    
    protected class DesenhoDeCirculo implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		acao = Acao.InicioCirculo;
    		stsMensagem.setText("Mensagem: clique no ponto inicial do cÃ­rculo");
    	}
    }
    
    protected class DesenhoDeElipse implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		acao = Acao.InicioElipse;
    		stsMensagem.setText("Mensagem: clique no ponto inicial da elipse");
    	}
    }
    
    protected class DesenhoDeQuadrado implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		acao = Acao.InicioQuadrado;
    		stsMensagem.setText("Mensagem: clique no ponto inicial do quadrado");
    	}
    }
    
    protected class DesenhoDeRetangulo implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		acao = Acao.InicioRetangulo;
    		stsMensagem.setText("Mensagem: clique no ponto inicial do retangulo");
    	}
    }
    
    protected class AbreMenuCores extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
        	mnuCores.show(e.getComponent(), e.getX(), e.getY());
        }
    }
    
    protected class SelecionaCorContorno implements ActionListener
    {   	
    	private Component component;
    	
    	public SelecionaCorContorno(Component component) {
    		this.component = component;
    	}
    	
    	public void actionPerformed(ActionEvent e)
    	{    		
    		stsMensagem.setText("Mensagem: selecione a cor do contorno");
    		Color ret = JColorChooser.showDialog(this.component, "Cor Contorno", corContorno);
    		
    		if (ret != null) {
    			corContorno = ret;
    		}
    		
    		if (corContorno.getAlpha() < 255) {
    			corContorno = new Color(corContorno.getRed(), corContorno.getGreen(), corContorno.getBlue(), 255);
        		stsMensagem.setText("Mensagem: transparência não é suportada no contorno");
    		}
    		else {
    			stsMensagem.setText("Mensagem: ");
    		}    		
    	}
    }
    
    protected class SelecionaCorInterior implements ActionListener
    {
    	private Component component;
    	
    	public SelecionaCorInterior(Component component) {
    		this.component = component;
    	}
    	
    	public void actionPerformed(ActionEvent e)
    	{
    		stsMensagem.setText("Mensagem: selecione a cor do interior");
    		Color ret = JColorChooser.showDialog(this.component, "Cor Interior", corInterior);
    		
    		if (ret != null) {
    			corInterior = ret;
    		}
    		
    		if (corInterior.getAlpha() > 0 && corInterior.getAlpha() < 255) {
    			corInterior = new Color(corInterior.getRed(), corInterior.getGreen(), corInterior.getBlue(), 0);
        		stsMensagem.setText("Mensagem: somente a transparência total é suportada");
    		}
    		else {
    			stsMensagem.setText("Mensagem: ");
    		}
    	}
    }

    protected class FechamentoDeJanela extends WindowAdapter
    {
        public void windowClosing (WindowEvent e)
        {
            System.exit(0);
        }
    }
}
