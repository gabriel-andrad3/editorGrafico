package editor.componentes.listeners;

import java.awt.event.*;

import editor.Acao;
import editor.Janela;

public class DesenhoDeReta extends EditorActionListener {
    public DesenhoDeReta(Janela janela) {
        super(janela);
    }

    public void actionPerformed(ActionEvent e) {
        this.janela.getPainelDesenho().setAcao(Acao.InicioReta);
        this.janela.setMensagem("clique no ponto inicial da reta");
    }
}
