package editor.componentes.listeners;

import java.awt.event.*;

import editor.Acao;
import editor.Janela;

public class DesenhoDePonto extends EditorActionListener {
    public DesenhoDePonto(Janela janela) {
        super(janela);
    }

    public void actionPerformed(ActionEvent e) {
        this.janela.getPainelDesenho().setAcao(Acao.Ponto);
        this.janela.setMensagem("clique no local do ponto desejado");
    }
}
