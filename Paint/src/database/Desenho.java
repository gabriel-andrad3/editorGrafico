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
    private String conteudo;
    
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

    public String getConteudo() {
        return this.conteudo;
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

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}