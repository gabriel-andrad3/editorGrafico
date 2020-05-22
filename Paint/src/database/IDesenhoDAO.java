package database;

public interface IDesenhoDAO {

    public void salvarDesenho(Desenho desenho) throws Exception;

    public Desenho buscarDesenho(String ipCriador, String nome) throws Exception;

}