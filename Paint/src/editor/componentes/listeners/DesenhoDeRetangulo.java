package editor.componentes.listeners;

import java.awt.event.*;

import editor.Acao;
import editor.Janela;

public class DesenhoDeRetangulo extends EditorActionListener {
    public DesenhoDeRetangulo(Janela janela) {
        super(janela);
    }

    public void actionPerformed(ActionEvent e) {
        this.janela.getPainelDesenho().setAcao(Acao.InicioRetangulo);
        this.janela.setMensagem("clique no ponto inicial do retângulo");
    }
}
