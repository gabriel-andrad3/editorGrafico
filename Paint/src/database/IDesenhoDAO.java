package database;

import java.util.List;

public interface IDesenhoDAO {

    public void salvarDesenho(Desenho desenho) throws Exception;

    public Desenho buscarDesenho(String nome) throws Exception;

    public List<Desenho> buscarDesenhos() throws Exception;
    
}
