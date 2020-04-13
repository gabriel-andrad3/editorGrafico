package editor.componentes.listeners;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import editor.Janela;
import figuras.*;

public class AbrirDesenho extends EditorActionListener {
    public AbrirDesenho(Janela janela) {
        super(janela);
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "text");
        fc.setFileFilter(filter);

        try {
            int returnVal = fc.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                BufferedReader leitor = new BufferedReader(new FileReader(file));
                Vector<Figura> figuras = new Vector<Figura>();

                //limpa a tela antes de abrir
                janela.getPainelDesenho().getGraphics().clearRect(0, 0, (int)janela.getSize().getWidth(), (int)janela.getSize().getHeight());

                String linha = leitor.readLine();

                while (linha != null) {
                    char qualFigura = linha.charAt(0);

                    switch(qualFigura) {
                        case 'p':
                            figuras.add(new Ponto(linha));
                            break;
                        case 'l':
                            figuras.add(new Linha(linha));
                            break;  
                        case 'c':
                            figuras.add(new Circulo(linha));
                            break;
                        case 'e':
                            figuras.add(new Elipse(linha));
                            break;
                        case 'q':
                            figuras.add(new Quadrado(linha));
                            break;
                        case 'r':
                            figuras.add(new Retangulo(linha));
                            break;
                        case 't':
                            figuras.add(new Texto(linha));
                            break;
                        default:
                    }
                    linha = leitor.readLine();
                }
                leitor.close();

                for (int index = 0; index < figuras.size(); index++) {            
                    figuras.get(index).torneSeVisivel(janela.getGraphics());   
                } 
                janela.getPainelDesenho().setFiguras(figuras);
            }
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro na abertura do arquivo:", "",
                    JOptionPane.WARNING_MESSAGE);
        }

    }

}