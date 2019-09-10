package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.Endereco;

public class EnderecoDAO {
	
	public EnderecoDAO() {}
	
	public boolean salvar(Endereco end) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "insert into endereco "
				+ "(logradouro, numero, complemento, bairro, cidade, estado) "
				+ "values (?,?,?,?,?,?)";

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, end.getLogradouro());
			stmt.setString(2, end.getNumero());
			stmt.setString(3, end.getComplemento());
			stmt.setString(4, end.getBairro());
			stmt.setString(5, end.getCidade());
			stmt.setString(6, end.getEstado());
		
			stmt.executeUpdate();
			
			System.out.println("Endereço salvo com sucesso!");
			
		} catch (SQLException ex) {
			return false;
		} catch (Throwable e) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao salvar endereço no banco de dados. (EnderecoDAO.salvar)" + e);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return true;
	}	
	
	public boolean deletar(Endereco end) {
		
		Connection con = ConnectionFactory.getConnection();
		String sql = "delete from endereco where id = ? ";

		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, end.getId());
			stmt.executeUpdate();
			System.out.println("Endereço deletado com sucesso!");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao deletar o endereço no banco de dados. (EnderecoDAO.atualizar)" + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return true;		
	}
	
	public boolean atualizar(Endereco end) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "update endereco set logradouro = ?," + " numero = ?, complemento = ?, bairro = ?, "
				+ "cidade = ?, estado = ? where id = ?";

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, end.getLogradouro());
			stmt.setString(2, end.getNumero());
			stmt.setString(3, end.getComplemento());
			stmt.setString(4, end.getBairro());
			stmt.setString(5, end.getCidade());
			stmt.setString(6, end.getEstado());
			stmt.setInt(7, end.getId());
			stmt.executeUpdate();
			System.out.println("Endereço atualizado com sucesso!");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao atualizar o endereço no banco de dados. (EnderecoDAO.atualizar)" + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return true;
	}
	
	public List<Endereco> listar(){
		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from endereco";

		List<Endereco> enderecos = new LinkedList<>();

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {

				Endereco end = new Endereco();
				end.setId(rs.getInt(1));
				end.setLogradouro(rs.getString("logradouro"));
				end.setNumero(rs.getString("numero"));
				end.setComplemento(rs.getString("complemento"));
				end.setBairro(rs.getString("bairro"));
				end.setCidade(rs.getString("cidade"));
				end.setEstado(rs.getString("estado"));
				enderecos.add(end);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao listar os endereços do banco de dados. (EnderecoDAO.listar)" + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return enderecos;
	}
	
	public int retornaIdEndereco(Endereco end) {
		
		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select id from endereco where logradouro = ? and numero = ?";

		Endereco endereco = new Endereco();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, end.getLogradouro());
			stmt.setString(2, end.getNumero());
			rs = stmt.executeQuery();

			while (rs.next()) {
				endereco.setId(rs.getInt(1));
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao recuperar o id do endere�o. (EnderecoDAO.retornaIdEndereco)" + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return endereco.getId();
		
	}	
}