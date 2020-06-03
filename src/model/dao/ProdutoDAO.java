package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Produto;

public class ProdutoDAO {

    public boolean create(Produto p) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO produto (id_produto,descricao,quant,preco,unidade) VALUES (?,?,?,?,?)");

            stmt.setInt(1, p.getId());
            stmt.setString(2, p.getDescricao());
            stmt.setInt(3, p.getQtd());
            stmt.setString(4, p.getPreco());
            stmt.setString(5, p.getUnidade());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            return true;

        } catch (SQLException ex) {

            dadosDuplicados(ex);
            return false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Produto> read() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();

                produto.setId(rs.getInt("id_produto"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setQtd(rs.getInt("quant"));
                produto.setPreco(rs.getString("preco"));
                produto.setUnidade(rs.getString("unidade"));

                produtos.add(produto);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO LER OS DADOS: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return produtos;
    }

    public boolean update(Produto p) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE produto SET id_produto = ?, descricao = ?, quant = ?, preco = ?, unidade = ? WHERE id_produto = ?");

            stmt.setInt(1, p.getIdproduto());
            stmt.setString(2, p.getDescricao());
            stmt.setInt(3, p.getQtd());
            stmt.setString(4, p.getPreco());
            stmt.setString(5, p.getUnidade());
            stmt.setInt(6, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
            return true;

        } catch (SQLException ex) {
            
            dadosDuplicados(ex);
            return false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean delete(Produto p) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM produto WHERE id_produto = ?");

            stmt.setInt(1, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR: " + ex);
            return false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Produto> readForDesc(String desc) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto WHERE descricao LIKE ?");
            stmt.setString(1, "%" + desc + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();

                produto.setId(rs.getInt("id_produto"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setQtd(rs.getInt("quant"));
                produto.setPreco(rs.getString("preco"));
                produto.setUnidade(rs.getString("unidade"));

                produtos.add(produto);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO LER OS DADOS: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return produtos;
    }

    private void dadosDuplicados(SQLException ex) {

        if (ex.getErrorCode() == 1062) {

            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR: Dados já existem!");

        } else {

            JOptionPane.showMessageDialog(null, ex);

        }

    }

}
