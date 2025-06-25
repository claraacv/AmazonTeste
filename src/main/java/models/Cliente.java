package models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import models.Missao;

@Entity
@Table(name="clientes_amazon")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emailTelefone;
    private String nome;
    private String senha;
    private String segundaSenha;
    @ManyToOne
    @JoinColumn(name = "missao_id")
    private Missao missao;
    private LocalDate dataMissao;
    private boolean statusUso = false;

    public LocalDate getDataMissao(){
        return dataMissao;
    }

    public void setDataMissao(LocalDate dataMissao){
        this.dataMissao = dataMissao;
    }

    public boolean getStatusUso(){
        return statusUso;
    }
    public Missao getMissao() {
        return missao;
    }

    public void setMissao(Missao missao) {
        this.missao = missao;
    }

    public Cliente(String emailTelefone) {
        this.emailTelefone = emailTelefone;
    }

    public Cliente() {

    }

    public void cadastrarMissao(Missao missao){
        this.missao = missao;
        dataMissao = LocalDate.now();
        statusUso = true;
    }

    public void realizarMissao(){
        statusUso = false;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public void setSegundaSenha(String segundaSenha){
        this.segundaSenha = segundaSenha;
    }

    public String getEmailTelefone(){
        return emailTelefone;
    }

    public String getNome(){
        return nome;
    }

    public String getSenha(){
        return senha;
    }

    public String getSegundaSenha(){
        return segundaSenha;
    }

    public Long getId() {
        return id;
    }

    public boolean validarLogin(String email, String senha){
        if (email == null || !email.contains("@")){
            return false;
        }

        if (senha == null || senha.isEmpty()){
            return false;
        }
        return true;
    }

    public void setId(int i) {
        this.id = id;
    }
}
