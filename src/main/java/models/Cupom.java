package models;

import java.time.LocalDate;

public class Cupom{
    private int idCupom;
    private String codigo;
    private LocalDate dataVencimento;
    private double porcentagemDesconto;

    public LocalDate getDataVencimento(){
        return dataVencimento;
    }

    public Cupom(int idCupom, String codigo, LocalDate dataVencimento, double porcentagemDesconto){
        this.idCupom = idCupom;
        this.codigo = codigo;
        this.dataVencimento = dataVencimento;
        this.porcentagemDesconto = porcentagemDesconto;
    }

    public boolean validarCupom(){
        if (dataVencimento != null && !dataVencimento.isBefore(LocalDate.now())){
            return true;
        }
        else{
            return false;
        }
    }

    public double getPorcentagemDesconto() {
        return porcentagemDesconto;
    }

    public String getCodigo() {
        return codigo;
    }
}