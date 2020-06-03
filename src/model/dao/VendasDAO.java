package model.dao;

import model.bean.Vendas;
import connection.ConexaoMySql;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class VendasDAO extends ConexaoMySql {

    public int salvarVendasDAO(Vendas pModelVendas) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO vendas"
                    + "(fk_cliente,"
                    + "ven_data_venda,"
                    + "ven_valor_liquido,"
                    + "ven_valor_bruto,"
                    + "ven_desconto)"
                    + "VALUES (?,?,?,?,?)");
            
            stmt.setInt(1, pModelVendas.getCliente());
            stmt.setDate(2, pModelVendas.getVenDataVenda());
            stmt.setDouble(3, pModelVendas.getVenValorLiquido());
            stmt.setDouble(4, pModelVendas.getVenValorBruto());
            stmt.setDouble(5, pModelVendas.getVenDesconto());
            
            //JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            
            return stmt.executeUpdate();
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            return 0;
            
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public Vendas getVendasDAO(int pIdVenda) {
        
        Vendas modelVendas = new Vendas();
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("SELECT * WHERE pk_id_venda = ?"); 
            stmt.setInt(1, pIdVenda);

            while (this.getResultSet().next()) {
                modelVendas.setIdVenda(this.getResultSet().getInt(1));
                modelVendas.setCliente(this.getResultSet().getInt(2));
                modelVendas.setVenDataVenda(this.getResultSet().getDate(3));
                modelVendas.setVenValorLiquido(this.getResultSet().getDouble(4));
                modelVendas.setVenValorBruto(this.getResultSet().getDouble(5));
                modelVendas.setVenDesconto(this.getResultSet().getDouble(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return modelVendas;
    }

    public ArrayList<Vendas> getListaVendasDAO() {
        
        ArrayList<Vendas> listamodelVendas = new ArrayList();
        Vendas modelVendas = new Vendas();
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM vendas");

            while (this.getResultSet().next()) {
                modelVendas = new Vendas();
                modelVendas.setIdVenda(this.getResultSet().getInt(1));
                modelVendas.setCliente(this.getResultSet().getInt(2));
                modelVendas.setVenDataVenda(this.getResultSet().getDate(3));
                modelVendas.setVenValorLiquido(this.getResultSet().getDouble(4));
                modelVendas.setVenValorBruto(this.getResultSet().getDouble(5));
                modelVendas.setVenDesconto(this.getResultSet().getDouble(6));
                listamodelVendas.add(modelVendas);
            }
        } catch (SQLException ex) {
            
            ex.printStackTrace();
            
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return listamodelVendas;
    }

    public boolean atualizarVendasDAO(Vendas pModelVendas) {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE vendas SET "
                    + "pk_id_venda = ?,"
                    + "fk_cliente = ?,"
                    + "ven_data_venda = ?,"
                    + "ven_valor_liquido = ?,"
                    + "ven_valor_bruto = ?,"
                    + "ven_desconto = ?"
                    + " WHERE pk_id_venda = ?");
                    
            stmt.setInt(1,pModelVendas.getIdVenda());
            stmt.setInt(2,pModelVendas.getCliente());
            stmt.setDate(3,pModelVendas.getVenDataVenda());
            stmt.setDouble(4,pModelVendas.getVenValorLiquido());
            stmt.setDouble(5,pModelVendas.getVenValorBruto());
            stmt.setDouble(6,pModelVendas.getVenDesconto());
            stmt.setDouble(7,pModelVendas.getIdVenda());
            
            return true;
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
            return false;
            
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean excluirVendasDAO(int pIdVenda) {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM vendas WHERE pk_id_venda = ?");
            stmt.setInt(1, pIdVenda);
            
            stmt.executeUpdate();
            
            return true;
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
            return false;
            
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
