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
import model.Locador;

public class LocadorDAO {

	// Nome das colunas no BD
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

	public LocadorDAO() {
	}

	public boolean salvar(Locador loc, Endereco end) {

		edao.salvar(end);
		int idEndereco = edao.retornaIdEndereco(end);
		loc.setDataCadastro(LocalDate.now());

		con = ConnectionFactory.getConnection();
		String sql = sqls.getProperty("LocadorDAO.salvar");

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
			JOptionPane.showMessageDialog(null, prop.getProperty("LocadorDAO.salvar.Sucesso"));
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("LocadorDAO.salvar.Fail") + " " + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return true;
	}

	public boolean editarLocador(Locador loc, Endereco end) {

		Connection con = ConnectionFactory.getConnection();
		String sql = sqls.getProperty("LocadorDAO.update");

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
			JOptionPane.showMessageDialog(null, prop.getProperty("LocadorDAO.update.Fail") + " " + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}

		if(edao.atualizar(end)) {
			JOptionPane.showMessageDialog(null, prop.getProperty("LocadorDAO.update.Sucesso"));
			return true;
		}
		
		return false;
	}

	public boolean deletarLocador(Locador loc, Endereco end) {

		Connection con = ConnectionFactory.getConnection();
		String sql = sqls.getProperty("LocadorDAO.delete");

		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement(sql);
			stmt.setInt(1, loc.getId());
			stmt.executeUpdate();

			edao.deletar(end);
			

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					prop.getProperty("LocadorDAO.delete.Fail") + " " + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}

		JOptionPane.showMessageDialog(null, prop.getProperty("LocadorDAO.delete.Sucesso"));
		return true;
	}

	public List<Locador> listarLocadores() {
		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = sqls.getProperty("LocadorDAO.list");

		List<Locador> locadores = new LinkedList<>();

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {

				Locador loc = new Locador();
				loc.setId(rs.getInt(1));
				loc.setNome(rs.getString(NOME));
				loc.setCpf(rs.getString(CPF));
				loc.setEndereco(edao.buscarPorId(rs.getInt(ID_ENDERECO)));
				loc.setCelular(rs.getString(CELULAR));
				loc.setLogin(rs.getString(LOGIN));
				loc.setSenha(rs.getString(SENHA));
				loc.setDataCadastro(rs.getDate(DATA_CADASTRO).toLocalDate());

				locadores.add(loc);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					prop.getProperty("LocadorDAO.list.Fail") + " " + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		System.out.println(prop.getProperty("LocadorDAO.list.Sucesso"));
		
		return locadores;
	}

	public int retornaIdLocador(Locador loc) {
		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = sqls.getProperty("LocadorDAO.retornarId");

		Locador locador = new Locador();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, loc.getCpf());
			rs = stmt.executeQuery();

			while (rs.next()) {
				locador.setId(rs.getInt(1));
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					prop.getProperty("LocadorDAO.retornarId.Fail") + " " + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		System.out.println(prop.getProperty("LocadorDAO.retornarId.Sucesso"));
		
		return locador.getId();
	}

	public Locador buscarPorId(int id) {
		Connection con = ConnectionFactory.getConnection();
		String sql = sqls.getProperty("LocadorDAO.retornarPorId");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Locador loc = new Locador();

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
			JOptionPane.showMessageDialog(null,
					prop.getProperty("LocadorDAO.retornarPorId.Fail") + " " + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		System.out.println(prop.getProperty("LocadorDAO.retornarPorId.Sucesso"));
		
		return loc;
	}

	public Locador buscarPorLogin(String login) {
		Connection con = ConnectionFactory.getConnection();
		String sql = sqls.getProperty("LocadorDAO.buscarPorLogin");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Locador loc = new Locador();

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
			JOptionPane.showMessageDialog(null,
					prop.getProperty("LocadorDAO.retornarPorLogin.Fail") + " " + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		System.out.println(prop.getProperty("LocadorDAO.retornarPorLogin.Sucesso"));
		return loc;
	}
}
