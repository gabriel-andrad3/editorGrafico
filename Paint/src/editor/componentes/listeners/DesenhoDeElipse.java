package editor.componentes.listeners;

import java.awt.event.*;

import editor.Acao;
import editor.Janela;

public class DesenhoDeElipse extends EditorActionListener {
    public DesenhoDeElipse(Janela janela) {
        super(janela);
    }

    public void actionPerformed(ActionEvent e) {
        this.janela.getPainelDesenho().setAcao(Acao.InicioElipse);
        this.janela.setMensagem("clique no ponto inicial da elipse");
    }
}
