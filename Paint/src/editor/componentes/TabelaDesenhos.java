package editor.componentes;

import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import comunicacao.Desenho;
import editor.Janela;

import figuras.*;

public class TabelaDesenhos extends JFrame {

	private static final long serialVersionUID = 6204763967805869023L;
    
    private Janela janela;
    private List<Desenho> desenhos;

    JFrame frame = new JFrame("Selecionar Desenhos");
    JList<Desenho> list = new JList<Desenho>();
    DefaultListModel<Desenho> model = new DefaultListModel<>();

    private JLabel lblNome = new JLabel();
    private JLabel lblDataCriacao = new JLabel();
    private JLabel lblDataAtualizacao = new JLabel();
    private JLabel lblIpAtualizacao = new JLabel();

    private JPanel panel = new JPanel();
    private JSplitPane splitPane = new JSplitPane();

    public TabelaDesenhos(Janela janela, List<Desenho> desenhos) {
        this.janela = janela;
        this.desenhos = desenhos;

        list.setModel(model);

        for (Desenho desenho : desenhos)
            model.addElement(desenho);

        list.getSelectionModel().addListSelectionListener(x -> {
            Desenho desenho = list.getSelectedValue();

            lblNome.setText("Nome: " + desenho.getNome());
            lblDataCriacao.setText("Data Criação: " + desenho.getDataAtualizacao());
            lblDataAtualizacao.setText("Data Última Atualização: " + desenho.getDataAtualizacao().toString());
            lblIpAtualizacao.setText("Ip Última Atualização: " + desenho.getIpAtualizacao());   

            janela.getPainelDesenho().getGraphics().clearRect(0, 0, (int)janela.getSize().getWidth(), (int)janela.getSize().getHeight());
            Vector<Figura> figuras = new Vector<>();

            for (String linha : desenho.getFiguras()) {
                char qualFigura = linha.charAt(0);

                switch(qualFigura) {
                    case 'p':
                        figuras.add(new Ponto(linha));
                        break;
                    case 'l':
                        figuras.add(new Linha(linha));
                        break;  
                    case 'c':
                        figuras.add(new Circulo(linha));
                        break;
                    case 'e':
                        figuras.add(new Elipse(linha));
                        break;
                    case 'q':
                        figuras.add(new Quadrado(linha));
                        break;
                    case 'r':
                        figuras.add(new Retangulo(linha));
                        break;
                    case 't':
                        figuras.add(new Texto(linha));
                        break;
                    default:
                }
            }

            for (int index = 0; index < figuras.size(); index++) {            
                figuras.get(index).torneSeVisivel(janela.getGraphics());   
            } 
            janela.getPainelDesenho().setFiguras(figuras);
        });

        splitPane.setLeftComponent(new JScrollPane(list));

        panel.add(lblNome);
        panel.add(lblDataCriacao);
        panel.add(lblDataAtualizacao);
        panel.add(lblIpAtualizacao);

        splitPane.setRightComponent(panel);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(splitPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
