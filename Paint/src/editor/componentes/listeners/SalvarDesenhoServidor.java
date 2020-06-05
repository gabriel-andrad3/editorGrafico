package editor.componentes.listeners;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import comunicacao.PedidoSalvamento;
import editor.Janela;
import figuras.Figura;

public class SalvarDesenhoServidor extends EditorActionListener {
    public SalvarDesenhoServidor(Janela janela) {
        super(janela);
    }

    public void actionPerformed(ActionEvent e) {
        String nome = JOptionPane.showInputDialog("Digite o nome do desenho a ser salvo");

        comunicacao.Desenho desenho = new comunicacao.Desenho(nome);

        for (Figura figura : this.janela.getPainelDesenho().getFiguras()) {
            desenho.addFigura(figura.toString());
        }

        PedidoSalvamento pedidoSalvamento = new PedidoSalvamento(desenho);
        
        try {
            this.janela.getServidor().receba(pedidoSalvamento);
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao enviar desenho para o servidor", "",
                    JOptionPane.WARNING_MESSAGE);
        }

    }

}