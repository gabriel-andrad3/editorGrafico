package editor.componentes.listeners;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import comunicacao.PedidoDesenhos;
import comunicacao.PedidoSalvamento;
import editor.Janela;
import figuras.Figura;

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
        
        String nomeDesenhos = null;
        for (int i=0; i < comunicadoDesenhos.getQtd(); i++) {
            nomeDesenhos += comunicadoDesenhos.getDesenho(i).getNome() + " ";
        }
        this.janela.setMensagem(nomeDesenhos);

        String nome = JOptionPane.showInputDialog("Digite o nome do desenho a ser carregado do servidor");
        
        // printar desenho no painel
    }

}