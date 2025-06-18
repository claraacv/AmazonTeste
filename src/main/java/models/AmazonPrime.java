package models;

import java.time.LocalDate;

public class AmazonPrime {
    public AmazonPrime(String s, Cliente cliente) {
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
}
