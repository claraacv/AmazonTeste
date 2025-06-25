package models;

import java.io.Serializable;
import java.time.LocalDate;
import enums.Planos;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class AmazonPrime implements Serializable {
    private Planos plano;
    private double valor;
    @Id
    private Long id;

    public AmazonPrime(String s, Cliente cliente) {
    }

    public AmazonPrime() {

    }

    public AmazonPrime(Planos plano, double valor){
        this.plano = plano;
        this.valor = valor;
    }

    public void cancelar(String s) {
    }

    public String validarCartao(String tipoPagamento, String numeroCartao, String nomeCartao, String dataExpiracao, int codigoSeguranca){
        boolean cartaoValido = true;

        //Verifica se o Cartão de Crédito tem a quantidade correta de dígitos
        if (tipoPagamento.equals("Cartão de Crédito") && numeroCartao.length() != 16){
            cartaoValido = false;
        }

        //Verifica a data de expiração do Cartão de Crédito
        String[] partes = dataExpiracao.split("/");
        int mesExp = Integer.parseInt(partes[0]);
        int anoExp = Integer.parseInt(partes[1]);

        LocalDate hoje = LocalDate.now();
        int mesAtual = hoje.getMonthValue();
        int anoAtual = hoje.getYear();

        if (anoExp < anoAtual || (anoExp == anoAtual && mesExp < mesAtual)){
            cartaoValido = false;
        }

        //Verifica se o código de segurança está entre 100 e 999
        if (codigoSeguranca < 100 || codigoSeguranca > 999){
            cartaoValido = false;
        }

        if(cartaoValido){
            return "Assinatura Realizada com Sucesso!";
        }
        else{
            return "Pagamento não Autorizado! Verifique os dados de seu cartão.";
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
