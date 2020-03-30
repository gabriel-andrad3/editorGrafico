package editor.componentes.listeners;

import java.awt.event.*;

import editor.Acao;
import editor.Janela;

public class DesenhoDeTexto extends EditorActionListener {
    public DesenhoDeTexto(Janela janela) {
        super(janela);
    }

    public void actionPerformed(ActionEvent e) {
        this.janela.getPainelDesenho().setAcao(Acao.InicioTexto);
        this.janela.setMensagem("clique no ponto inicial do texto");
    }
}
