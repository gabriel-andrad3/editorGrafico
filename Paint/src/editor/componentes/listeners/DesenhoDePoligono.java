package editor.componentes.listeners;

import java.awt.event.*;

import editor.Acao;
import editor.Janela;

public class DesenhoDePoligono extends EditorActionListener {
    public DesenhoDePoligono(Janela janela) {
        super(janela);
    }

    public void actionPerformed(ActionEvent e) {
        this.janela.getPainelDesenho().setAcao(Acao.InicioPoligono);
        this.janela.setMensagem("clique no ponto inicial do polígono");
    }
}
