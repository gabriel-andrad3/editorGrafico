package editor.componentes.listeners;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import comunicacao.PedidoDesenhos;
import comunicacao.PedidoSalvamento;
import editor.Janela;
import figuras.*;

public class CarregarDesenhoDoServidor extends EditorActionListener {
    public CarregarDesenhoDoServidor(Janela janela) {
        super(janela);
    }

    public void actionPerformed(ActionEvent e) {

        PedidoDesenhos pedidoDesenhos = new PedidoDesenhos("/127.0.0.1"); // FIXME usar getIp
        try {
            this.janela.getServidor().receba(pedidoDesenhos);
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar desenhos do servidor", "",
                    JOptionPane.WARNING_MESSAGE);
        }

        comunicacao.Desenhos comunicadoDesenhos = null;
        try {
            comunicadoDesenhos = (comunicacao.Desenhos)this.janela.getServidor().envie();
            
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar desenhos do servidor", "",
                    JOptionPane.WARNING_MESSAGE);
        }
        
        String nomeDesenhos = "";
        for (int i=0; i < comunicadoDesenhos.getQtd(); i++) {
            nomeDesenhos += comunicadoDesenhos.getDesenho(i).getNome() + " ";
        }
        this.janela.setMensagem(nomeDesenhos);

        String nome = JOptionPane.showInputDialog("Digite o nome do desenho a ser carregado do servidor");
        
        // printar desenho no painel
        comunicacao.Desenho desenhoSelecionado = null;
        for (comunicacao.Desenho desenho : comunicadoDesenhos.getDesenhos()) {
            if (nome.equals(desenho.getNome()))
                desenhoSelecionado = desenho;
        }


        //limpa a tela antes de abrir
        janela.getPainelDesenho().getGraphics().clearRect(0, 0, (int)janela.getSize().getWidth(), (int)janela.getSize().getHeight());
        Vector<Figura> figuras = new Vector<>();

        for (String linha : desenhoSelecionado.getFiguras()) {
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
    }

}