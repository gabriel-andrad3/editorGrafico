package database;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Desenho implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private String nome;
    private String ipAtualizacao;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataUltimaAtualizacao;
    private List<String> figuras;
    
    public String getNome() {
        return this.nome;
    }

    public String getIpAtualizacao() {
        return this.ipAtualizacao;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public LocalDateTime getDataUltimaAtualizacao() {
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

    public void setIpAtualizacao(String ipAtualizacao) {
        this.ipAtualizacao = ipAtualizacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataUltimaAtualizacao(LocalDateTime dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    public void setFiguras(List<String> figuras) {
        this.figuras = figuras;
    }
}