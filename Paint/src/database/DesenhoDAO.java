package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DesenhoDAO implements IDesenhoDAO {
    public DesenhoDAO() { }

    @Override
    public Desenho buscarDesenho(String nome) throws Exception {       
        if (nome == null || nome == "")
            throw new Exception("O nome não pode ser vazio ou nulo");

        Connection con = ConexaoBD.getConexaoBD();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        stmt = con.prepareStatement("SELECT nome, ip_criador, data_criacao, data_ult_atualizacao, figuras FROM Editor.Desenhos WHERE nome = ?");
        
        stmt.setString(1, nome);

        Desenho desenho = null;

        try {
            rs = stmt.executeQuery();

            while (rs.next()) {
                desenho = new Desenho();

                desenho.setNome(rs.getString("nome"));
                desenho.setIpAtualizacao(rs.getString("ip_criador"));
                desenho.setDataCriacao(rs.getObject("data_criacao", LocalDateTime.class));
                desenho.setDataUltimaAtualizacao(rs.getObject("data_ult_atualizacao", LocalDateTime.class));

                desenho.setFiguras(Arrays.asList(rs.getString("figuras").split("\n")));
            }
        }
        catch (SQLException e) {
            throw e;
        }
        finally {
            stmt.close();
            ConexaoBD.FecharConexao();
        }

        return desenho;
    }

    @Override
    public void salvarDesenho(Desenho desenho) throws Exception {
        if (desenho == null)
            throw new Exception("O desenho não pode ser nulo");

        Connection con = ConexaoBD.getConexaoBD();
        PreparedStatement stmt = null;

        Desenho desenhoExistente = buscarDesenho(desenho.getNome());

        try {
            if (desenhoExistente != null) {
                stmt = con.prepareStatement("UPDATE Editor.Desenhos SET data_ult_atualizacao = ?, figuras = ?, ip_criador = ? WHERE nome = ?");

                stmt.setObject(1, desenho.getDataUltimaAtualizacao());
                stmt.setString(2, desenho.getFigurasString());
                stmt.setString(3, desenho.getIpAtualizacao());
                stmt.setString(4, desenho.getNome());

                stmt.executeUpdate();
            } else {
                stmt = con.prepareStatement("INSERT INTO Editor.Desenhos (nome, ip_criador, data_criacao, data_ult_atualizacao,figuras) VALUES (?,?,?,?,?)");

                stmt.setString(1, desenho.getNome());
                stmt.setString(2, desenho.getIpAtualizacao());
                stmt.setObject(3, desenho.getDataCriacao());
                stmt.setObject(4, desenho.getDataUltimaAtualizacao());
                stmt.setString(5, desenho.getFigurasString());
    
                stmt.executeUpdate();
            }
        }
        catch (SQLException e) {
            throw e;
        }
        finally {
            stmt.close();
            ConexaoBD.FecharConexao();
        }
    }

	@Override
	public List<Desenho> buscarDesenhos() throws Exception {
        Connection con = ConexaoBD.getConexaoBD();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        stmt = con.prepareStatement("SELECT nome, ip_criador, data_criacao, data_ult_atualizacao, figuras FROM Editor.Desenhos");
    
        List<Desenho> desenhos = new ArrayList<Desenho>();

        try {
            rs = stmt.executeQuery();

            while (rs.next()) {
                Desenho desenho = new Desenho();

                desenho.setNome(rs.getString("nome"));
                desenho.setIpAtualizacao(rs.getString("ip_criador"));
                desenho.setDataCriacao(rs.getObject("data_criacao", LocalDateTime.class));
                desenho.setDataUltimaAtualizacao(rs.getObject("data_ult_atualizacao", LocalDateTime.class));

                desenho.setFiguras(Arrays.asList(rs.getString("figuras").split("\n")));

                desenhos.add(desenho);
            }
        }
        catch (SQLException e) {
            throw e;
        }
        finally {
            stmt.close();
            ConexaoBD.FecharConexao();
        }

        return desenhos;
	}
}
