package controller;

import model.bean.VendasProdutos;
import model.dao.VendasProdutosDAO;
import java.util.ArrayList;

public class ControllerVendasProdutos {

    private VendasProdutosDAO daoVendasProdutos = new VendasProdutosDAO();

    public int salvarVendasProdutosController(VendasProdutos pModelVendasProdutos){
        return this.daoVendasProdutos.salvarVendasProdutosDAO(pModelVendasProdutos);
    }

    public VendasProdutos getVendasProdutosController(int pIdVendaProduto){
        return this.daoVendasProdutos.getVendasProdutosDAO(pIdVendaProduto);
    }

    public ArrayList<VendasProdutos> getListaVendasProdutosController(){
        return this.daoVendasProdutos.getListaVendasProdutosDAO();
    }

    public boolean atualizarVendasProdutosController(VendasProdutos pModelVendasProdutos){
        return this.daoVendasProdutos.atualizarVendasProdutosDAO(pModelVendasProdutos);
    }

    public boolean excluirVendasProdutosController(int pIdVendaProduto){
        return this.daoVendasProdutos.excluirVendasProdutosDAO(pIdVendaProduto);
    }
    
}