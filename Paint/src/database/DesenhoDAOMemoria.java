package database;

import java.util.ArrayList;
import java.util.List;

public class DesenhoDAOMemoria implements IDesenhoDAO {

    private List<Desenho> desenhos;

    public DesenhoDAOMemoria() {
        desenhos = new ArrayList<Desenho>();
    }

    @Override
    public Desenho buscarDesenho(String ipCriador, String nome) throws Exception {
        if (ipCriador == null || ipCriador == "")
            throw new Exception("O IP do criador não pode ser vazio ou nulo");
        
        if (nome == null || nome == "")
            throw new Exception("O nome não pode ser vazio ou nulo");

        for (Desenho desenhoSalvo : desenhos) {
            if (desenhoSalvo.getIpCriador().equals(ipCriador) && desenhoSalvo.getNome().equals(nome))
                    return desenhoSalvo;
        }

        return null;
    }

    @Override
    public void salvarDesenho(Desenho desenho) throws Exception {
        if (desenho == null)
        throw new Exception("O desenho não pode ser nulo");
    
        Desenho desenhoExistente = null;

        for (Desenho desenhoSalvo : desenhos) {
            if (desenhoSalvo.getIpCriador().equals(desenho.getIpCriador()) && desenhoSalvo.getNome().equals(desenho.getNome()))
                    desenhoExistente = desenhoSalvo;
        }

        if (desenhoExistente == null)
            desenhos.add(desenho);
        else
            desenhoExistente = desenho;
    }

	@Override
	public List<Desenho> buscarDesenhos(String ipCriador) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}