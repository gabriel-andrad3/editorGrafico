package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DesenhoDAO implements IDesenhoDAO {

    private List<Desenho> desenhos;

    public DesenhoDAO() {
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

        Connection con = ConexaoBD.getConexaoBD();
        PreparedStatement stmt = null;

        for (Desenho desenhoSalvo : desenhos) {
            if (desenhoSalvo.getIpCriador().equals(desenho.getIpCriador()) && desenhoSalvo.getNome().equals(desenho.getNome()))
                    desenhoExistente = desenhoSalvo;
        }

        if (desenhoExistente == null)
            try{
                stmt = con.prepareStatement("INSERT INTO Editor.Desenhos (nome,ip_criador,data_criacao,data_ult_atualizacao,figuras VALUES (?,?,?,?,?,)");
                stmt.setString(1, desenho.getNome());
                stmt.setString(2, desenho.getIpCriador());
                stmt.setDate(3, (java.sql.Date) desenho.getDataCriacao());
                stmt.setDate(4, (java.sql.Date) desenho.getDataUltimaAtualizacao());
                stmt.setString(5, desenho.getFiguras().toString());

                stmt.executeUpdate();
            }
            catch(SQLException e) {
                throw e;
            }
            finally {
                stmt.close();
                ConexaoBD.FecharConexao();
            }
        else
            desenhoExistente = desenho;
    }
    
}