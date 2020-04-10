package editor.componentes.listeners;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import editor.Janela;
import figuras.Figura;

public class SalvarDesenho extends EditorActionListener {
    public SalvarDesenho(Janela janela) {
        super(janela);
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "text");
        fc.setFileFilter(filter);

        try {
            int returnVal = fc.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                BufferedWriter escritor = new BufferedWriter(new FileWriter(file));
                Vector<Figura> figuras = this.janela.getPainelDesenho().getFiguras();

                Iterator figura = figuras.iterator();
                while (figura.hasNext()) { 
                    escritor.write(figura.next().toString());
                    escritor.newLine();
                }

                escritor.close();
            }
        }
        catch(Exception ex) {

        }

    }

}