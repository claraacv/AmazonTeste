package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PedidoDevolucao {

    private int idPedido;
    private LocalDate dataPedido;
    private LocalDate dataEntrega;
    private String item;
    private String motivo;
    private String metodo;
    private LocalDate dataPedidoDevolucao;
    private String status;

    public PedidoDevolucao(int idPedido, LocalDate dataPedido, LocalDate dataEntrega, String item, String motivo, String metodo, LocalDate dataPedidoDevolucao, String status){
        this.idPedido = idPedido;
        this.dataPedido = dataPedido;
        this.dataEntrega = dataEntrega;
        this.item = item;
        this.motivo = motivo;
        this.metodo = metodo;
        this.dataPedidoDevolucao = dataPedidoDevolucao;
        this.status = status;
    }

    public boolean devolverPedido(){
        if (!status.equals("Entregue")){
            return false;
        }

        long diasEntreEntregaEDevolucao = ChronoUnit.DAYS.between(dataEntrega, dataPedidoDevolucao);

        if (diasEntreEntregaEDevolucao <= 7){
            return true;
        }
        else{
            return false;
        }
    }

    public String getStatus(){
        return status;
    }
}
