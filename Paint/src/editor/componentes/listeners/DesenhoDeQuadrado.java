package editor.componentes.listeners;

import java.awt.event.*;

import editor.Acao;
import editor.Janela;

public class DesenhoDeQuadrado extends EditorActionListener {
    public DesenhoDeQuadrado(Janela janela) {
        super(janela);
    }

    public void actionPerformed(ActionEvent e) {
        this.janela.getPainelDesenho().setAcao(Acao.InicioQuadrado);
        this.janela.setMensagem("clique e arraste para desenhar o quadrado");
    }
}
