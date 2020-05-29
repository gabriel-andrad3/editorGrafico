package database;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Desenho implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private String nome;
    private String ipCriador;
	private Date dataCriacao;
	private Date dataUltimaAtualizacao;
    private List<String> figuras;
    
    public String getNome() {
        return this.nome;
    }

    public String getIpCriador() {
        return this.ipCriador;
    }

    public Date getDataCriacao() {
        return this.dataCriacao;
    }

    public Date getDataUltimaAtualizacao() {
        return this.dataUltimaAtualizacao;
    }

    public List<String> getFiguras() {
        return this.figuras;
    }

    public String getFigurasString() {
        String figurasString = "";

        for (String figura : this.getFiguras()) {
            figurasString += figura;
            figurasString += "\n"; // para buscar espaço em branco na leitura
        }
        
        return figurasString;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIpCriador(String ipCriador) {
        this.ipCriador = ipCriador;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    public void setFiguras(List<String> figuras) {
        this.figuras = figuras;
    }
}