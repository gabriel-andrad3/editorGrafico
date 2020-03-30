package editor.componentes.listeners;

import java.awt.event.*;

import editor.Acao;
import editor.Janela;

public class DesenhoDeCirculo extends EditorActionListener {
    public DesenhoDeCirculo(Janela janela) {
        super(janela);
    }

    public void actionPerformed(ActionEvent e) {
        this.janela.getPainelDesenho().setAcao(Acao.InicioCirculo);
        this.janela.setMensagem("clique no ponto inicial do círculo");
    }
}
