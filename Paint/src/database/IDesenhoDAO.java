package database;

import java.util.List;

public interface IDesenhoDAO {

    public void salvarDesenho(Desenho desenho) throws Exception;

    public Desenho buscarDesenho(String ipCriador, String nome) throws Exception;

    public List<Desenho> buscarDesenhos(String ipCriador) throws Exception;
    
}
