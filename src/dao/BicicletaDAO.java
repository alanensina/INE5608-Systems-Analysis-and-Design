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
import enumeration.Status;
import model.Aluguel;
import model.Bicicleta;
import model.Locador;

public class BicicletaDAO {

	// Nomes das colunas no BD
	private static final String ID = "id";
	private static final String MODELO = "modelo";
	private static final String ANO = "ano";
	private static final String VALOR_DE_ALUGUEL = "valorDeAluguel";
	private static final String ACESSORIOS = "acessorios";
	private static final String DISPONIVEL = "disponivel";
	private static final String LOCADOR = "idLocador";

	private Properties prop = getProp();
	private Properties stringSQL = getSqls();
	private LocadorDAO locDao = new LocadorDAO();

	public BicicletaDAO() {
	}

	public boolean salvar(Locador loc, Bicicleta bic) {
		Connection con = ConnectionFactory.getConnection();

		String sql = stringSQL.getProperty("BicicletaDAO.salvar");

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, loc.getId());
			stmt.setString(2, bic.getModelo());
			stmt.setString(3, bic.getAno());
			stmt.setDouble(4, bic.getValorDeAluguel());
			stmt.setString(5, bic.getAcessorios());
			stmt.setBoolean(6, bic.isDisponivel());

			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, prop.getProperty("BicicletaDAO.salvar.Sucesso"));
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("BicicletaDAO.salvar.Fail") + " " + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return true;
	}

	public List<Bicicleta> listarBicicletasPeloIdLocador(Locador loc) {
		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = stringSQL.getProperty("BicicletaDAO.getPorId");

		List<Bicicleta> bikes = new LinkedList<>();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, loc.getId());
			rs = stmt.executeQuery();

			while (rs.next()) {
				Bicicleta bic = new Bicicleta();
				bic.setLocador(loc);
				bic.setId(rs.getInt(1));
				bic.setModelo(rs.getString(MODELO));
				bic.setAno(rs.getString(ANO));
				bic.setValorDeAluguel(rs.getDouble(VALOR_DE_ALUGUEL));
				bic.setAcessorios(rs.getString(ACESSORIOS));
				bic.setDisponivel(rs.getBoolean(DISPONIVEL));
				bikes.add(bic);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("BicicletaDAO.listar.Fail") + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}

		System.out.println(prop.getProperty("BicicletaDAO.listar.Sucesso"));

		return bikes;
	}

	public Bicicleta retornarBikePorId(int id) {
		Connection con = ConnectionFactory.getConnection();
		String sql = stringSQL.getProperty("BicicletaDAO.getPorId");
		PreparedStatement stmt = null;
		ResultSet rs = null;

		Bicicleta bic = new Bicicleta();
		LocadorDAO locDao = new LocadorDAO();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while (rs.next()) {
				bic.setLocador(locDao.buscarPorId(rs.getInt(LOCADOR)));
				bic.setId(rs.getInt(ID));
				bic.setModelo(rs.getString(MODELO));
				bic.setAno(rs.getString(ANO));
				bic.setValorDeAluguel(rs.getDouble(VALOR_DE_ALUGUEL));
				bic.setAcessorios(rs.getString(ACESSORIOS));
				bic.setDisponivel(rs.getBoolean(DISPONIVEL));
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("BicicletaDAO.listar.Fail") + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}

		System.out.println(prop.getProperty("BicicletaDAO.listar.Sucesso"));

		return bic;
	}

	public boolean deletar(Bicicleta bic) {
		Connection con = ConnectionFactory.getConnection();
		String sql = stringSQL.getProperty("BicicletaDAO.deletar");

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, bic.getId());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, prop.getProperty("BicicletaDAO.delete.Sucesso"));

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("BicicletaDAO.delete.Fail") + " " + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}

		return true;
	}

	public boolean atualizar(Bicicleta bic) {
		Connection con = ConnectionFactory.getConnection();
		String sql = stringSQL.getProperty("BicicletaDAO.atualizar");

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bic.getModelo());
			stmt.setString(2, bic.getAno());
			stmt.setDouble(3, bic.getValorDeAluguel());
			stmt.setString(4, bic.getAcessorios());
			stmt.setBoolean(5, bic.isDisponivel());
			stmt.setInt(6, bic.getId());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, prop.getProperty("BicicletaDAO.update.Sucesso"));

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("BicicletaDAO.update.Fail") + " " + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return true;
	}

	public boolean deletarTodasAsBicicletas(Locador locador) {
		Connection con = ConnectionFactory.getConnection();
		String sql = stringSQL.getProperty("BicicletaDAO.deletarTodas");

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, locador.getId());
			stmt.executeUpdate();
			System.out.println(prop.getProperty("BicicletaDAO.deletarTodas.Sucesso"));

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("BicicletaDAO.deletarTodas.Fail") + " " + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}

		return true;
	}

	public List<Bicicleta> listarBikesDisponiveisParaLocacao(LocalDate dtInicio, LocalDate dtFim) {
		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = stringSQL.getProperty("BicicletaDAO.listarBikesDisponiveisParaAluguel");

		List<Bicicleta> bikes = new LinkedList<>();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setBoolean(1, true);
			stmt.setString(2, Status.ALUGUEL_INICIADO.getDescricao());
			stmt.setDate(3, Date.valueOf(dtInicio));
			stmt.setDate(4, Date.valueOf(dtFim));
			rs = stmt.executeQuery();

			while (rs.next()) {
				Bicicleta bic = new Bicicleta();
				bic.setLocador(locDao.buscarPorId(rs.getInt(LOCADOR)));
				bic.setId(rs.getInt(1));
				bic.setModelo(rs.getString(MODELO));
				bic.setAno(rs.getString(ANO));
				bic.setValorDeAluguel(rs.getDouble(VALOR_DE_ALUGUEL));
				bic.setAcessorios(rs.getString(ACESSORIOS));
				bic.setDisponivel(rs.getBoolean(DISPONIVEL));
				bikes.add(bic);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("BicicletaDAO.listarBikesParaAluguel.Fail") + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}

		System.out.println(prop.getProperty("BicicletaDAO.listarBikesParaAluguel.Sucesso"));

		return bikes;
	}

	public List<Bicicleta> listarBikesDisponiveisParaEdicao(Locador locador) {
		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = stringSQL.getProperty("BicicletaDAO.listarBikesDisponiveisParaEdicao");

		List<Bicicleta> bikes = new LinkedList<>();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, locador.getId());
			stmt.setString(2, Status.ALUGUEL_INICIADO.getDescricao());
			stmt.setInt(3, locador.getId());
			rs = stmt.executeQuery();

			while (rs.next()) {
				Bicicleta bic = new Bicicleta();
				bic.setLocador(locador);
				bic.setId(rs.getInt(1));
				bic.setModelo(rs.getString(MODELO));
				bic.setAno(rs.getString(ANO));
				bic.setValorDeAluguel(rs.getDouble(VALOR_DE_ALUGUEL));
				bic.setAcessorios(rs.getString(ACESSORIOS));
				bic.setDisponivel(rs.getBoolean(DISPONIVEL));
				bikes.add(bic);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("BicicletaDAO.listarBikesParaEdicao.Fail") + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}

		System.out.println(prop.getProperty("BicicletaDAO.listarBikesParaEdicao.Sucesso"));

		return bikes;
	}

	public boolean atualizaDisponibilidade(Bicicleta bicicleta) {
		Connection con = ConnectionFactory.getConnection();
		String sql = stringSQL.getProperty("BicicletaDAO.atualizaDisponibilidadeAposSolicitacao");

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, bicicleta.getId());
			stmt.executeUpdate();
			System.out.println(prop.getProperty("BicicletaDAO.atualizaDisponibilidadeAposSolicitacao.Sucesso"));

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("BicicletaDAO.atualizaDisponibilidadeAposSolicitacao.Fail") + " " + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return true;
	}
}