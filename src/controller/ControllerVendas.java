package controller;

import model.bean.Vendas;
import model.dao.VendasDAO;
import java.util.ArrayList;

public class ControllerVendas {

    private VendasDAO daoVendas = new VendasDAO();

    public int salvarVendasController(Vendas pModelVendas){
        return this.daoVendas.salvarVendasDAO(pModelVendas);
    }

    public Vendas getVendasController(int  pIdVenda){
        return this.daoVendas.getVendasDAO(pIdVenda);
    }

    public ArrayList<Vendas> getListaVendasController(){
        return this.daoVendas.getListaVendasDAO();
    }

    public boolean atualizarVendasController(Vendas pModelVendas){
        return this.daoVendas.atualizarVendasDAO(pModelVendas);
    }

    public boolean excluirVendasController(int  pIdVenda){
        return this.daoVendas.excluirVendasDAO(pIdVenda);
    }
    
}