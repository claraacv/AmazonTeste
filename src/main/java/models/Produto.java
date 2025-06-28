package models;

import jakarta.persistence.*;

@Entity
@Table(name = "produto")
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public long getId() {
        return id;
    }
}
