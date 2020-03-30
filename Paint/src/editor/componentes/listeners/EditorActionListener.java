package editor.componentes.listeners;

import java.awt.event.*;

import editor.Janela;

public abstract class EditorActionListener implements ActionListener {
    protected Janela janela;

    public EditorActionListener(Janela janela) {
        this.janela = janela;
    }
}
