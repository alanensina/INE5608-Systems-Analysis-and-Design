package dao;

import static service.UtilsService.getProp;
import static service.UtilsService.getSqls;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.Endereco;
import model.Locatario;

public class LocatarioDAO {

	private static final String ID = "id";
	private static final String NOME = "nome";
	private static final String CPF = "cpf";
	private static final String ID_ENDERECO = "idEndereco";
	private static final String CELULAR = "celular";
	private static final String LOGIN = "login";
	private static final String SENHA = "senha";
	private static final String DATA_CADASTRO = "dataCadastro";

	private Properties prop = getProp();
	private Properties sqls = getSqls();

	private EnderecoDAO edao = new EnderecoDAO();
	private Connection con = null;

	public LocatarioDAO() {
	}

	public boolean salvar(Locatario loc, Endereco end) {

		edao.salvar(end);
		int idEndereco = edao.retornaIdEndereco(end);
		loc.setDataCadastro(LocalDate.now());

		con = ConnectionFactory.getConnection();
		String sql = sqls.getProperty("LocatarioDAO.salvar");

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
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("LocatarioDAO.salvar.Fail") + " " + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		JOptionPane.showMessageDialog(null, prop.getProperty("LocatarioDAO.salvar.Sucesso"));
		return true;
	}

	public boolean editarLocatario(Locatario loc, Endereco end) {

		Connection con = ConnectionFactory.getConnection();
		String sql = sqls.getProperty("LocatarioDAO.update");

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
			JOptionPane.showMessageDialog(null, prop.getProperty("LocatarioDAO.update.Fail") + " " + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}

		edao.atualizar(end);
		JOptionPane.showMessageDialog(null, prop.getProperty("LocatarioDAO.update.Sucesso"));

		return true;

	}

	public boolean deletarLocatario(Locatario loc, Endereco end) {

		Connection con = ConnectionFactory.getConnection();
		String sql = sqls.getProperty("LocatarioDAO.delete");

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, loc.getId());
			stmt.executeUpdate();

			edao.deletar(end);

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("LocatarioDAO.delete.Fail") + " " + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}

		JOptionPane.showMessageDialog(null, prop.getProperty("LocatarioDAO.delete.Sucesso"));
		return true;
	}

	public List<Locatario> listarLocatarios() {
		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = sqls.getProperty("LocatarioDAO.list");

		List<Locatario> locatarios = new LinkedList<>();

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {

				Locatario loc = new Locatario();
				loc.setId(rs.getInt(1));
				loc.setNome(rs.getString(NOME));
				loc.setCpf(rs.getString(CPF));
				loc.setEndereco(edao.buscarPorId(rs.getInt(ID_ENDERECO)));
				loc.setCelular(rs.getString(CELULAR));
				loc.setLogin(rs.getString(LOGIN));
				loc.setSenha(rs.getString(SENHA));
				loc.setDataCadastro(rs.getDate(DATA_CADASTRO).toLocalDate());

				locatarios.add(loc);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("LocatarioDAO.list.Fail") + " " + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		System.out.println(prop.getProperty("LocatarioDAO.list.Sucesso"));
		return locatarios;
	}

	public int retornaIdLocatario(Locatario loc) {
		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = sqls.getProperty("LocatarioDAO.retornarId");

		Locatario locatario = new Locatario();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, loc.getCpf());
			rs = stmt.executeQuery();

			while (rs.next()) {
				locatario.setId(rs.getInt(1));
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("LocatarioDAO.retornarId.Fail") + " " + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		System.out.println(prop.getProperty("LocatarioDAO.retornarId.Sucesso"));
		return locatario.getId();
	}

	public Locatario buscarPorId(int id) {
		Connection con = ConnectionFactory.getConnection();
		String sql = sqls.getProperty("LocatarioDAO.retornarPorId");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Locatario loc = new Locatario();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while (rs.next()) {

				loc.setId(rs.getInt(ID));
				loc.setNome(rs.getString(NOME));
				loc.setCpf(rs.getString(CPF));
				loc.setEndereco(edao.buscarPorId(rs.getInt(ID_ENDERECO)));
				loc.setCelular(rs.getString(CELULAR));
				loc.setLogin(rs.getString(LOGIN));
				loc.setSenha(rs.getString(SENHA));
				loc.setDataCadastro(rs.getDate(DATA_CADASTRO).toLocalDate());

			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("LocatarioDAO.retornarPorId.Fail") + " " + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		System.out.println(prop.getProperty("LocatarioDAO.retornarPorId.Sucesso"));
		return loc;
	}

	public Locatario buscarPorLogin(String login) {
		Connection con = ConnectionFactory.getConnection();
		String sql = sqls.getProperty("LocatarioDAO.buscarPorLogin");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Locatario loc = new Locatario();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, login);
			rs = stmt.executeQuery();

			while (rs.next()) {

				loc.setId(rs.getInt(ID));
				loc.setNome(rs.getString(NOME));
				loc.setCpf(rs.getString(CPF));
				loc.setEndereco(edao.buscarPorId(rs.getInt(ID_ENDERECO)));
				loc.setCelular(rs.getString(CELULAR));
				loc.setLogin(rs.getString(LOGIN));
				loc.setSenha(rs.getString(SENHA));
				loc.setDataCadastro(rs.getDate(DATA_CADASTRO).toLocalDate());

			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("LocatarioDAO.retornarPorLogin.Fail") + " " + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		System.out.println(prop.getProperty("LocatarioDAO.retornarPorLogin.Sucesso"));
		return loc;
	}
}
