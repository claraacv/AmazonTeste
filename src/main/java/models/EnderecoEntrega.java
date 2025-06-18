package models;

public class EnderecoEntrega {

    private String pais;
    private String nomeCompleto;
    private long telefone;
    private int cep;
    private String endereco;
    private int numeroResidencia;
    private String complemento;
    private String bairro;

    private String cidade;
    private String estado;

    public EnderecoEntrega(String pais, String nomeCompleto, long telefone, int cep, String endereco, int numeroResidencia, String complemento, String bairro) {
        this.pais = pais;
        this.nomeCompleto = nomeCompleto;
        this.telefone = telefone;
        this.cep = cep;
        this.endereco = endereco;
        this.numeroResidencia = numeroResidencia;
        this.complemento = complemento;
        this.bairro = bairro;
    }

    public boolean validarEnderecoEntrega() {
        try {
            String url = "https://viacep.com.br/ws/" + this.cep + "/json/";
            java.net.URL conexao = new java.net.URL(url);
            java.net.HttpURLConnection request = (java.net.HttpURLConnection) conexao.openConnection();
            request.setRequestMethod("GET");

            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(request.getInputStream(), "UTF-8"));

            StringBuilder resposta = new StringBuilder();
            String linha;
            while ((linha = in.readLine()) != null){
                resposta.append(linha);
            }
            in.close();

            String json = resposta.toString();

            if (json.contains("\"erro\": true")) {
                return false;
            }

            this.cidade = extrairCampo(json, "localidade");
            this.estado = extrairCampo(json, "uf");

            return true;

        }catch (Exception e){
            return false;
        }
    }

    private String extrairCampo(String json, String campo){
        String regex = "\"" + campo + "\"\\s*:\\s*\"([^\"]+)\"";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

}
