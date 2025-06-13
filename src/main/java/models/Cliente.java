package models;

public class Cliente {
    private Long id;
    private String emailTelefone;
    private String nome;
    private String senha;
    private String segundaSenha;

    public Cliente(String emailTelefone){
        this.emailTelefone = emailTelefone;
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
}
