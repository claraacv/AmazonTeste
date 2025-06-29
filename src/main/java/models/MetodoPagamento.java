package models;

import jakarta.persistence.*;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Entity
public class MetodoPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // Ex: Cartão de Crédito
    private String numeroCartao;
    private String nomeTitular;
    private String validade; // Ex: "12/2027"
    private String cvv;

    public MetodoPagamento() {
    }

    public MetodoPagamento(String tipo, String numeroCartao, String nomeTitular, String validade, String cvv) {
        this.tipo = tipo;
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.validade = validade;
        this.cvv = cvv;
    }

    public boolean validarValidade() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
            YearMonth validadeYM = YearMonth.parse(this.validade, formatter);
            return validadeYM.isAfter(YearMonth.now());
        } catch (Exception e) {
            return false;
        }
    }

    // Getters e setters omitidos para brevidade
    public Long getId() { return id; }
    public String getTipo() { return tipo; }
    public String getNumeroCartao() { return numeroCartao; }
    public String getNomeTitular() { return nomeTitular; }
    public String getValidade() { return validade; }
    public String getCvv() { return cvv; }

    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setNumeroCartao(String numeroCartao) { this.numeroCartao = numeroCartao; }
    public void setNomeTitular(String nomeTitular) { this.nomeTitular = nomeTitular; }
    public void setValidade(String validade) { this.validade = validade; }
    public void setCvv(String cvv) { this.cvv = cvv; }
}
