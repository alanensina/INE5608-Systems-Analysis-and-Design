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
import model.Locador;

public class LocadorDAO {

	private EnderecoDAO edao = new EnderecoDAO();
	private Connection con = null;

	public LocadorDAO() {
	}

	public boolean salvar(Locador loc, Endereco end) {

		edao.salvar(end);
		int idEndereco = edao.retornaIdEndereco(end);
		loc.setDataCadastro(LocalDate.now());

		con = ConnectionFactory.getConnection();
		String sql = "insert into locador (nome, cpf, idEndereco, celular, login, senha, dataCadastro) values (?, ?, ?, ?, ?, ?, ?)";

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
			JOptionPane.showMessageDialog(null, "Locador registrado com sucesso!");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					"Houve um erro ao salvar o locador no banco de dados (LocadorDAO.salvar)" + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return true;
	}

	public boolean editarLocador(Locador loc, Endereco end) {

		Connection con = ConnectionFactory.getConnection();
		String sql = "update locador set nome = ?, cpf = ?, celular = ?, " + "senha = ? where id = ?";

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
					"Houve um erro ao atualizar o locador no banco de dados. (LocadorDAO.editarLocador)" + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
		edao.atualizar(end);
		JOptionPane.showMessageDialog(null,"Locador atualizado com sucesso!");
		return true;

	}

	public boolean deletarLocador(Locador loc, Endereco end) {
		
		Connection con = ConnectionFactory.getConnection();
		String sql = "delete from locador where id = ? ";

		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, loc.getId());
			stmt.executeUpdate();
			
			edao.deletar(end);
			JOptionPane.showMessageDialog(null,"Locador deletado com sucesso!");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao deletar o locador no banco de dados. (LocadorDAO.deletarLocador)" + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
		
		return true;	
	}

	public List<Locador> listarLocadores() {
		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from locador";

		List<Locador> locadores = new LinkedList<>();

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {

				Locador loc = new Locador();
				loc.setId(rs.getInt(1));
				loc.setNome(rs.getString("nome"));
				loc.setCpf(rs.getString("cpf"));
				loc.setEndereco(edao.buscarPorId(rs.getInt("idEndereco")));
				loc.setCelular(rs.getString("celular"));
				loc.setLogin(rs.getString("login"));
				loc.setSenha(rs.getString("senha"));
				loc.setDataCadastro(rs.getDate("dataCadastro").toLocalDate());				
				
				locadores.add(loc);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao listar os locadores do banco de dados. (LocadorDAO.listarLocadores)" + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return locadores;
	}

	public int retornaIdLocador(Locador loc) {
		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select id from locador where cpf = ?";

		Locador locador = new Locador();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, loc.getCpf());
			rs = stmt.executeQuery();

			while (rs.next()) {
				locador.setId(rs.getInt(1));
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao recuperar o id do locador. (LocadorDAO.retornaIdLocador)" + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return locador.getId();
	}
	
	public Locador buscarPorId(int id) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from locador where id = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Locador loc = new Locador();
		
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
			JOptionPane.showMessageDialog(null, "Houve um erro ao buscar o locador pelo ID no banco de dados. (LocadorDAO.buscarPorId)" + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return loc;
	}
	
	public Locador buscarPorLogin(String login) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from locador where login = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Locador loc = new Locador();
		
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
			JOptionPane.showMessageDialog(null, "Houve um erro ao buscar o locador pelo ID no banco de dados. (LocadorDAO.buscarPorId)" + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return loc;
	}
}
