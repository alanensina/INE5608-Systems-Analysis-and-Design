package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.Endereco;
import model.Locatario;

public class LocatarioDAO {

	private EnderecoDAO edao = new EnderecoDAO();
	private Connection con = null;

	public LocatarioDAO() {}
	
	public boolean salvar(Locatario loc, Endereco end) {
		
		edao.salvar(end);
		int idEndereco = edao.retornaIdEndereco(end);
		loc.setDataCadastro(LocalDate.now());
		
		con = ConnectionFactory.getConnection();
		String sql = "insert into locatario (nome, cpf, idEndereco, celular, login, senha, dataCadastro) values (?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, loc.getNome()); 
			stmt.setString(2, loc.getCpf());
			stmt.setInt(3, idEndereco); 
			stmt.setString(4, loc.getCelular());
			stmt.setString(5, loc.getLogin());
			stmt.setString(6, loc.getSenha());
			stmt.setDate(7, Date.valueOf(loc.getDataCadastro()));
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Locatário registrado com sucesso!");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao salvar o locatário no banco de dados (LocatarioDAO.salvar)" + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return true;
	}
	
	public boolean editarLocatario(Locatario loc, Endereco end) {

		Connection con = ConnectionFactory.getConnection();
		String sql = "update locatario set nome = ?, cpf = ?, celular = ?, " + "senha = ? where id = ?";

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, loc.getNome());
			stmt.setString(2, loc.getCpf());
			stmt.setString(3, loc.getCelular());
			stmt.setString(4, loc.getSenha());
			stmt.setInt(5, loc.getId());
			stmt.executeUpdate();
			

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					"Houve um erro ao atualizar o locatário no banco de dados. (LocatarioDAO.editarLocatario)" + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
		edao.atualizar(end);
		JOptionPane.showMessageDialog(null,"Locatário atualizado com sucesso!");
		
		return true;

	}

	public boolean deletarLocatario(Locatario loc, Endereco end) {
		
		Connection con = ConnectionFactory.getConnection();
		String sql = "delete from locatario where id = ? ";

		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, loc.getId());
			stmt.executeUpdate();
			
			edao.deletar(end);
			JOptionPane.showMessageDialog(null,"Locatário deletado com sucesso!");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao deletar o locatário no banco de dados. (LocatarioDAO.deletarLocatario)" + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
		return true;	
	}

	public List<Locatario> listarLocatarios() {
		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from locatario";

		List<Locatario> locatarios = new LinkedList<>();

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {

				Locatario loc = new Locatario();
				loc.setId(rs.getInt(1));
				loc.setNome(rs.getString("nome"));
				loc.setCpf(rs.getString("cpf"));
				loc.setEndereco(edao.buscarPorId(rs.getInt("idEndereco")));
				loc.setCelular(rs.getString("celular"));
				loc.setLogin(rs.getString("login"));
				loc.setSenha(rs.getString("senha"));
				loc.setDataCadastro(rs.getDate("dataCadastro").toLocalDate());				
				
				locatarios.add(loc);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao listar os locatarios do banco de dados. (LocatarioDAO.listarLocatarios)" + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return locatarios;
	}

	public int retornaIdLocatario(Locatario loc) {
		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select id from locatario where cpf = ?";

		Locatario locatario = new Locatario();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, loc.getCpf());
			rs = stmt.executeQuery();

			while (rs.next()) {
				locatario.setId(rs.getInt(1));
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao recuperar o id do locatário. (LocatarioDAO.retornaIdLocatario)" + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return locatario.getId();
	}
	
	public Locatario buscarPorId(int id) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from locatario where id = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Locatario loc = new Locatario();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while (rs.next()) {

				loc.setId(rs.getInt(1));
				loc.setNome(rs.getString("nome"));
				loc.setCpf(rs.getString("cpf"));
				loc.setEndereco(edao.buscarPorId(rs.getInt("idEndereco")));
				loc.setCelular(rs.getString("celular"));
				loc.setLogin(rs.getString("login"));
				loc.setSenha(rs.getString("senha"));
				loc.setDataCadastro(rs.getDate("dataCadastro").toLocalDate());

			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao buscar o locatário pelo ID no banco de dados. (LocatarioDAO.buscarPorId)" + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return loc;
	}
	
	public Locatario buscarPorLogin(String login) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from locatario where login = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Locatario loc = new Locatario();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, login);
			rs = stmt.executeQuery();

			while (rs.next()) {

				loc.setId(rs.getInt(1));
				loc.setNome(rs.getString("nome"));
				loc.setCpf(rs.getString("cpf"));
				loc.setEndereco(edao.buscarPorId(rs.getInt("idEndereco")));
				loc.setCelular(rs.getString("celular"));
				loc.setLogin(rs.getString("login"));
				loc.setSenha(rs.getString("senha"));
				loc.setDataCadastro(rs.getDate("dataCadastro").toLocalDate());	

			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao buscar o locatário pelo login no banco de dados. (LocatarioDAO.buscarPorLogin)" + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return loc;
	}
}
