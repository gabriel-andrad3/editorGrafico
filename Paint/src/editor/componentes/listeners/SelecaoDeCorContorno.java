package editor.componentes.listeners;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.JColorChooser;

import editor.Janela;

public class SelecaoDeCorContorno extends EditorActionListener {
    public SelecaoDeCorContorno(Janela janela) {
        super(janela);
    }

    public void actionPerformed(ActionEvent e) {
        janela.setMensagem("selecione a cor do contorno");

        Color ret = JColorChooser.showDialog(this.janela, "Cor Contorno",
                this.janela.getPainelDesenho().getCorContorno());

        if (ret == null) {
            return;
        }

        if (ret.getAlpha() < 255) {
            ret = new Color(ret.getRed(), ret.getGreen(), ret.getBlue(), 255);
            janela.setMensagem("transparência não é suportada no contorno");
        } else {
            janela.setMensagem("");
        }

        this.janela.getPainelDesenho().setCorContorno(ret);
    }
}