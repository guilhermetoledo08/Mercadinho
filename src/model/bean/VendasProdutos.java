package model.bean;

public class VendasProdutos {

    private int idVendaProduto;
    private int produto;
    private int vendas;
    private double venProValor;
    private double venProQuant;

    public VendasProdutos(){}

    public void setIdVendaProduto(int pIdVendaProduto){
        this.idVendaProduto = pIdVendaProduto;
    }
    
    public int getIdVendaProduto(){
        return this.idVendaProduto;
    }

    public void setProduto(int pProduto){
        this.produto = pProduto;
    }

    public int getProduto(){
        return this.produto;
    }

    public void setVendas(int pVendas){
        this.vendas = pVendas;
    }
    
    public int getVendas(){
        return this.vendas;
    }

    public void setVenProValor(double pVenProValor){
        this.venProValor = pVenProValor;
    }
    
    public double getVenProValor(){
        return this.venProValor;
    }

    public void setVenProQuant(double pVenProQuant){
        this.venProQuant = pVenProQuant;
    }
    
    public double getVenProQuant(){
        return this.venProQuant;
    }

    @Override
    public String toString(){
        return "ModelVendasProdutos {" + "::idVendaProduto = " + this.idVendaProduto + "::produto = " + this.produto + "::vendas = " + this.vendas + "::venProValor = " + this.venProValor + "::venProQuant = " + this.venProQuant +  "}";
    }
}