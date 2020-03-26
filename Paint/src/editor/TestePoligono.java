package editor;

import java.util.Iterator;

import figuras.Linha;
import figuras.Poligono;

public class TestePoligono {
    public static void main (String[] args) {
        Poligono poli = new Poligono();

        poli.adicionaLados(new Linha(100, 100, 100, 100));
        poli.adicionaLados(new Linha(200, 200, 200, 200));

        Iterator value = poli.getLados().iterator();
        while (value.hasNext()) { 
            System.out.println(value.next()); 
        }
        
        System.out.println(poli.getQtdLados());
        //System.out.println(poli.getPrimeiroPonto());
    }
}