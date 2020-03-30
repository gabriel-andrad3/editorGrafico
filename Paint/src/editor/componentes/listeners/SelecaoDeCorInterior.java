package editor.componentes.listeners;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.JColorChooser;

import editor.Janela;

public class SelecaoDeCorInterior extends EditorActionListener {
    public SelecaoDeCorInterior(Janela janela) {
        super(janela);
    }

    public void actionPerformed(ActionEvent e) {
        janela.setMensagem("selecione a cor do interior");

        Color ret = JColorChooser.showDialog(this.janela, "Cor Interior",
                this.janela.getPainelDesenho().getCorInterior());

        if (ret == null) {
            return;
        }

        if (ret.getAlpha() > 0 && ret.getAlpha() < 255) {
            ret = new Color(ret.getRed(), ret.getGreen(), ret.getBlue(), 0);
            janela.setMensagem("somente a transparância total é suportada");
        } else {
            janela.setMensagem("");
        }

        this.janela.getPainelDesenho().setCorInterior(ret);
    }
}