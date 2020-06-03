package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Cliente;

public class ClienteDAO {

    public boolean create(Cliente p) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO cliente (cpf,nome,telefone,email,cidade,bairro,rua,numero,cep) VALUES (?,?,?,?,?,?,?,?,?)");

            stmt.setString(1, p.getCpf());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getTel());
            stmt.setString(4, p.getEmail());
            stmt.setString(5, p.getCidade());
            stmt.setString(6, p.getBairro());
            stmt.setString(7, p.getRua());
            stmt.setInt(8, p.getNumero());
            stmt.setString(9, p.getCep());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            return true;

        } catch (SQLException ex) {

            dadosDuplicados(ex);
            return false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Cliente> read() {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList();

        try {
            
            stmt = con.prepareStatement("SELECT * FROM cliente");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTel(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setRua(rs.getString("rua"));
                cliente.setNumero(rs.getInt("numero"));
                cliente.setCep(rs.getString("cep"));

                clientes.add(cliente);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO LER OS DADOS: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return clientes;
    }

    public boolean update(Cliente p) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE cliente SET nome = ?, cpf = ?, email = ?, telefone = ?,"
                    + " cidade = ?, bairro = ?, rua = ?, numero = ?, cep = ? WHERE id_cliente = ?");

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCpf());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, p.getTel());
            stmt.setString(5, p.getCidade());
            stmt.setString(6, p.getBairro());
            stmt.setString(7, p.getRua());
            stmt.setInt(8, p.getNumero());
            stmt.setString(9, p.getCep());
            stmt.setInt(10, p.getId());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
            return true;

        } catch (SQLException ex) {

            dadosDuplicados(ex);
            return false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean delete(Cliente p) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM cliente WHERE id_cliente = ?");

            stmt.setInt(1, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR: " + ex);
            return false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Cliente> readForName(String nome) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE id_cliente LIKE ? OR nome LIKE ? "
                    + "OR cpf LIKE ? OR telefone LIKE ? OR email LIKE ? OR cidade LIKE ?"
                    + "OR bairro LIKE ? OR rua LIKE ? OR numero LIKE ? OR cep LIKE ?");

            stmt.setString(1, "%" + nome + "%");
            stmt.setString(2, "%" + nome + "%");
            stmt.setString(3, "%" + nome + "%");
            stmt.setString(4, "%" + nome + "%");
            stmt.setString(5, "%" + nome + "%");
            stmt.setString(6, "%" + nome + "%");
            stmt.setString(7, "%" + nome + "%");
            stmt.setString(8, "%" + nome + "%");
            stmt.setString(9, "%" + nome + "%");
            stmt.setString(10, "%" + nome + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTel(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setRua(rs.getString("rua"));
                cliente.setNumero(rs.getInt("numero"));
                cliente.setCep(rs.getString("cep"));

                clientes.add(cliente);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO LER OS DADOS: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return clientes;
    }

    private void dadosDuplicados(SQLException ex) {
        
        if (ex.getErrorCode() == 1062) {
            
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR: Dados já existem!"+ex);
            
        } else {
            
            JOptionPane.showMessageDialog(null, ex);
            
        }
        
    }

}
