package dao;

import static service.UtilsService.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.Endereco;

public class EnderecoDAO {

	// Nomes das colunas no BD
	private static final String LOGRADOURO = "logradouro";
	private static final String NUMERO = "numero";
	private static final String BAIRRO = "bairro";
	private static final String COMPLEMENTO = "complemento";
	private static final String CEP = "cep";
	private static final String CIDADE = "cidade";
	private static final String ESTADO = "estado";

	private Properties prop = getProp();
	private Properties sqls = getSqls();

	public EnderecoDAO() {
	}

	public boolean salvar(Endereco end) {
		Connection con = ConnectionFactory.getConnection();
		String sql = sqls.getProperty("EnderecoDAO.salvar");

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, end.getLogradouro());
			stmt.setString(2, end.getNumero());
			stmt.setString(3, end.getComplemento());
			stmt.setString(4, end.getCep());
			stmt.setString(5, end.getBairro());
			stmt.setString(6, end.getCidade());
			stmt.setString(7, end.getEstado());

			stmt.executeUpdate();

			System.out.println(prop.getProperty("EnderecoDAO.Salvar.Sucesso"));

		} catch (SQLException ex) {
			return false;
		} catch (Throwable e) {
			JOptionPane.showMessageDialog(null, prop.getProperty("EnderecoDAO.Salvar.Fail") + " " + e);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return true;
	}

	public boolean deletar(Endereco end) {

		Connection con = ConnectionFactory.getConnection();
		String sql = sqls.getProperty("EnderecoDAO.deletar");

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, end.getId());
			stmt.executeUpdate();
			System.out.println(prop.getProperty("EnderecoDAO.Delete.Sucesso"));

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("EnderecoDAO.Delete.Fail") + " " + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return true;
	}

	public boolean atualizar(Endereco end) {
		Connection con = ConnectionFactory.getConnection();
		String sql = sqls.getProperty("EnderecoDAO.update");

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, end.getLogradouro());
			stmt.setString(2, end.getNumero());
			stmt.setString(3, end.getComplemento());
			stmt.setString(4, end.getCep());
			stmt.setString(5, end.getBairro());
			stmt.setString(6, end.getCidade());
			stmt.setString(7, end.getEstado());
			stmt.setInt(8, end.getId());
			stmt.executeUpdate();
			System.out.println(prop.getProperty("EnderecoDAO.Update.Sucesso"));

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("EnderecoDAO.Update.Fail") + " " + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return true;
	}

	public List<Endereco> listar() {
		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = sqls.getProperty("EnderecoDAO.listar");

		List<Endereco> enderecos = new LinkedList<>();

		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {

				Endereco end = new Endereco();
				end.setId(rs.getInt(1));
				end.setLogradouro(rs.getString(LOGRADOURO));
				end.setNumero(rs.getString(NUMERO));
				end.setComplemento(rs.getString(COMPLEMENTO));
				end.setCep(rs.getString(CEP));
				end.setBairro(rs.getString(BAIRRO));
				end.setCidade(rs.getString(CIDADE));
				end.setEstado(rs.getString(ESTADO));
				enderecos.add(end);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("EnderecoDAO.List.Fail") + " " + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		System.out.println(prop.getProperty("EnderecoDAO.List.Sucesso"));
		return enderecos;
	}

	public int retornaIdEndereco(Endereco end) {

		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = sqls.getProperty("EnderecoDAO.recuperarIdEndereco");
		
		Endereco endereco = new Endereco();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, end.getLogradouro());
			stmt.setString(2, end.getNumero());
			stmt.setString(3, end.getCep());
			rs = stmt.executeQuery();

			while (rs.next()) {
				endereco.setId(rs.getInt("id"));
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("EnderecoDAO.recuperarIdEndereco.Fail") + " " + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		System.out.println(prop.getProperty("EnderecoDAO.recuperarIdEndereco.Sucesso"));
		return endereco.getId();
	}

	public Endereco buscarPorId(int id) {
		Connection con = ConnectionFactory.getConnection();
		String sql = sqls.getProperty("EnderecoDAO.buscarPorId");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Endereco end = new Endereco();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while (rs.next()) {

				end.setId(id);
				end.setLogradouro(rs.getString(LOGRADOURO));
				end.setNumero(rs.getString(NUMERO));
				end.setBairro(rs.getString(BAIRRO));
				end.setComplemento(rs.getString(COMPLEMENTO));
				end.setCep(rs.getString(CEP));
				end.setCidade(rs.getString(CIDADE));
				end.setEstado(rs.getString(ESTADO));

			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("EnderecoDAO.BuscarPorId.Fail") + " " + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		System.out.println(prop.getProperty("EnderecoDAO.BuscarPorId.Sucesso"));
		return end;
	}
}