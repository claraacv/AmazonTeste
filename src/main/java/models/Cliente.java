package models;

import daos.ClienteDao;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import models.Missao;
import models.AmazonPrime;

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
    @Column(name = "status_uso")
    private boolean statusUso = false;
    @ManyToOne
    @JoinColumn(name = "prime_id")
    private AmazonPrime amazonPrime;
    private LocalDate dataInscricaoPrime;
    private LocalDate dataCancelamentoPrime;

    public void setAmazonPrime(AmazonPrime amazonPrime){
        this.amazonPrime = amazonPrime;
    }

    public AmazonPrime getAmazonPrime(){
        return amazonPrime;
    }

    public void inscreverPrime(LocalDate dataInscricaoPrime){
        this.dataInscricaoPrime = dataInscricaoPrime;
    }

    public void cancelarPrime(LocalDate dataCancelamentoPrime){
        this.dataCancelamentoPrime = dataCancelamentoPrime;
    }

    public LocalDate getDataInscricaoPrime(){
        return dataInscricaoPrime;
    }

    public LocalDate getDataCancelamentoPrime(){
        return dataCancelamentoPrime;
    }

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

//    public void setMissao(Missao missao) {
//        this.missao = missao;
//    }

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
