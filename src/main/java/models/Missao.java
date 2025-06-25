package models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table
public class Missao implements Serializable {

    @Id
    private Long id;
    private double valor;
    private String nome;

    public Missao(double valor, String nome){
        this.valor = valor;
        this.nome = nome;
    }

    public Missao() {

    }

    public double getValor(){
        return valor;
    }
    public void setId(Long id){
        this.id=id;
    }

    public Long getId(){
        return id;
    }

    public void setCredito(double valor) {
        this.valor = valor;
    }

    public void setNome(String s) {
    }

    public void setData(LocalDate dataUsada) {
    }

}
