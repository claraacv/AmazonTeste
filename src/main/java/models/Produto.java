package models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Produto {
    @Id
    private long id;
    private String nome;
    private double valor;
    private int quantidadeEstoque;
    private String categoria;

    public Produto(String nome, double valor, int quantidadeEstoque, String categoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
    }

    public Produto() {

    }

    /** RN06 – Não permitir preço zerado ou negativo */
    public boolean validarPreco() {
        return valor > 0;
    }

    /** RN06 – Não permitir nome vazio ou nulo */
    public boolean validarNome() {
        return nome != null && !nome.trim().isEmpty();
    }

    public String getNome() { return nome; }
    public String getCategoria() { return categoria; }
    public double getValor() { return valor; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }

}
