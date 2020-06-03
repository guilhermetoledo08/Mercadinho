package model.dao;

import model.bean.VendasProdutos;
import connection.ConnectionFactory;
import connection.ConexaoMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class VendasProdutosDAO extends ConexaoMySql {

    public int salvarVendasProdutosDAO(VendasProdutos pModelVendasProdutos){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO vendas_produtos"
                    + "(fk_produto,"
                    + "fk_vendas,"
                    + "ven_pro_valor,"
                    + "ven_pro_quant)"
                    + "VALUES (?,?,?,?)");
            
            stmt.setInt(1, pModelVendasProdutos.getProduto());
            stmt.setInt(2, pModelVendasProdutos.getVendas());
            stmt.setDouble(3, pModelVendasProdutos.getVenProValor());
            stmt.setDouble(4, pModelVendasProdutos.getVenProQuant());
           
            //JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            
            return stmt.executeUpdate();
            
        }catch(Exception e){
            
            e.printStackTrace();
            return 0;
            
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public VendasProdutos getVendasProdutosDAO(int pIdVendaProduto){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        VendasProdutos VenProd = new VendasProdutos();
        
        try {
            
            stmt = con.prepareStatement("SELECT * FROM vendas_produtos WHERE pk_id_venda_produto = ?");
            stmt.setInt(1,pIdVendaProduto);

            while(this.getResultSet().next()){
                VenProd.setIdVendaProduto(this.getResultSet().getInt(1));
                VenProd.setProduto(this.getResultSet().getInt(2));
                VenProd.setVendas(this.getResultSet().getInt(3));
                VenProd.setVenProValor(this.getResultSet().getDouble(4));
                VenProd.setVenProQuant(this.getResultSet().getDouble(5));
            }
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, ex);
            
        }finally{
            
            ConnectionFactory.closeConnection(con, stmt);
            
        }
        
        return VenProd;
    }

    public ArrayList<VendasProdutos> getListaVendasProdutosDAO(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        ArrayList<VendasProdutos> listaVenProd = new ArrayList();
        //VendasProdutos vendasProd = new VendasProdutos();
        
        try {
            
            stmt = con.prepareStatement("SELECT * FROM vendas_produtos");
            this.setResultSet(stmt.executeQuery());

            while(this.getResultSet().next()){
                
                VendasProdutos vendasProd = new VendasProdutos();
                
                vendasProd.setIdVendaProduto(this.getResultSet().getInt(1));
                vendasProd.setProduto(this.getResultSet().getInt(2));
                vendasProd.setVendas(this.getResultSet().getInt(3));
                vendasProd.setVenProValor(this.getResultSet().getDouble(4));
                vendasProd.setVenProQuant(this.getResultSet().getDouble(5));
                
                listaVenProd.add(vendasProd);
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }finally{
            this.fecharConexao();
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        return listaVenProd;
        
    }

    public boolean atualizarVendasProdutosDAO(VendasProdutos pModelVendasProdutos){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("UPDATE vendas_produtos SET "
                    + "pk_id_venda_produto = ?, "
                    + "fk_produto = ?, "
                    + "fk_vendas = ?, "
                    + "ven_pro_valor = ?, "
                    + "ven_pro_quant = ? "
                    + "WHERE pk_id_venda_produto = ?");
            
            stmt.setInt(1, pModelVendasProdutos.getIdVendaProduto());
            stmt.setInt(2, pModelVendasProdutos.getProduto());
            stmt.setInt(3, pModelVendasProdutos.getVendas());
            stmt.setDouble(4, pModelVendasProdutos.getVenProValor());
            stmt.setDouble(5, pModelVendasProdutos.getVenProQuant());
            stmt.setInt(6, pModelVendasProdutos.getIdVendaProduto());
            
            return true;
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, ex);
            return false;
            
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean excluirVendasProdutosDAO(int pIdVendaProduto){
  
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("DELETE FROM vendas_produtos WHERE pk_id_venda_produto = ?");
            stmt.setInt(1, pIdVendaProduto);
            stmt.executeUpdate();
            return true;
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, ex);
            return false;
            
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}