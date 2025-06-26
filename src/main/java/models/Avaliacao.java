package models;

public class Avaliacao {
    private int estrelas;
    private String comentario;
    private String titulo;
    private Cliente cliente;
    private Produto produto;

    public Avaliacao(Cliente cliente, Produto produto, String titulo){
        this.cliente = cliente;
        this.produto = produto;
        this.titulo = titulo;
    }
    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getComentario(){
        return comentario;
    }

    public int getEstrelas(){
        return estrelas;
    }
}
