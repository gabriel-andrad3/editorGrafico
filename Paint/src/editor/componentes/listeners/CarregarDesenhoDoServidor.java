package editor.componentes.listeners;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import comunicacao.PedidoDesenhos;
import comunicacao.PedidoSalvamento;
import editor.Janela;
import figuras.*;
import editor.componentes.TabelaDesenhos;

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

        TabelaDesenhos tabelaDesenhos = new TabelaDesenhos(janela, comunicadoDesenhos.getDesenhos());
    }

}