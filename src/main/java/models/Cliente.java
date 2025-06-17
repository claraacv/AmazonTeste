package models;

import jakarta.persistence.*;

@Entity
@Table(name="clientes_amazon")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emailTelefone;
    private String nome;
    private String senha;
    private String segundaSenha;

    public Cliente(String emailTelefone) {
        this.emailTelefone = emailTelefone;
    }

    public Cliente() {

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
}
