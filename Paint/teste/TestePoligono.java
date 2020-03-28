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

        for (int i=0; i<poli.getQtdLados(); i++) {
            //System.out.println(poli.getLado(i));
        }
        
        System.out.println(poli.getQtdLados());
        //System.out.println(poli.getPrimeiroPonto());
    }
}