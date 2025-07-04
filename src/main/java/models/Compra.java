package models;

import daos.mockClienteDao;
import mocks.CupomDaoMock;

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
        if(cliente.getAmazonPrime() != null){
            if(cliente.getDataInscricaoPrime().isBefore(data)){
                if(cliente.getDataCancelamentoPrime()== null || cliente.getDataCancelamentoPrime().isAfter(data)|| cliente.getDataCancelamentoPrime().isEqual(data)){
                    return true;
                }
            }
        }
        return false;
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

    public boolean validarCupomAmazon(String codigo){
        if(cliente.getAmazonPrime() != null){
            if(cliente.getDataInscricaoPrime().isBefore(data)){
                if(cliente.getDataCancelamentoPrime()== null || cliente.getDataCancelamentoPrime().isAfter(data)|| cliente.getDataCancelamentoPrime().isEqual(data)){
                    CupomDaoMock dao = new CupomDaoMock();
                    List<Cupom> cupons = dao.getCupons();

                    for(Cupom c: cupons){
                        if(c.getCodigo().equals(codigo)){
                            if(data.isBefore(c.getDataVencimento()) || data.isEqual(c.getDataVencimento())){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
