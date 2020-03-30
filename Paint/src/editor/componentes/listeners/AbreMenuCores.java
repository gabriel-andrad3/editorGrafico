package editor.componentes.listeners;

import java.awt.event.*;

import editor.Janela;

public class AbreMenuCores implements MouseListener {
    private Janela janela;

    public AbreMenuCores(Janela janela) {
        this.janela = janela;
    }

    public void mouseClicked(MouseEvent e) {
        this.janela.getMenuCores().show(e.getComponent(), e.getX(), e.getY());
    }

    public void mousePressed(MouseEvent e) {
    };

    public void mouseReleased(MouseEvent e) {
    };

    public void mouseEntered(MouseEvent e) {
    };

    public void mouseExited(MouseEvent e) {
    };
}
