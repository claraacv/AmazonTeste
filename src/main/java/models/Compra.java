package models;

import daos.mockClienteDao;

import java.time.LocalDate;
import java.util.List;

public class Compra {

    private Long id;
    private LocalDate data;
    private Cliente cliente;

    public Compra() {

    }

    public LocalDate getData(){
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Compra(Cliente cliente, LocalDate data){
        this.cliente = cliente;
        this.data = data;
    }

    public boolean validarFreteGratis() {
        return true;
    }


    public double finalizarVenda() {
        mockClienteDao dao = new mockClienteDao();
        List<Cliente> clientes = dao.getClientes();
        for(Cliente c: clientes){
            if(c.getId() == cliente.getId()){
                if(cliente.getMissao() != null){
                    if (!data.isBefore(cliente.getDataMissao()) && data.isBefore(cliente.getDataMissao().plusDays(8))) {
                        if(cliente.getStatusUso()){
                            cliente.realizarMissao();
                            return cliente.getMissao().getValor();
                        }
                    }
                }
            }
        }
        return 0;
    }
}
