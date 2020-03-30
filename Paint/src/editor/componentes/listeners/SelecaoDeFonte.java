package editor.componentes.listeners;

import java.awt.event.*;

import say.swing.JFontChooser;

import editor.Janela;

public class SelecaoDeFonte extends EditorActionListener {
    public SelecaoDeFonte(Janela janela) {
        super(janela);
    }

    public void actionPerformed(ActionEvent e) {
        janela.setMensagem("selecione a fonte");

        JFontChooser fontChooser = new JFontChooser();

        fontChooser.setSelectedFont(janela.getPainelDesenho().getFonte());

        if (fontChooser.showDialog(this.janela) == 0)
            janela.getPainelDesenho().setFonte(fontChooser.getSelectedFont());

        janela.setMensagem("");
    }
}