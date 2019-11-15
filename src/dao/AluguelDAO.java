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
import model.Locatario;

public class AluguelDAO {
	
	// Nomes das colunas no BD
	private static final String ID = "id";
	private static final String LOCADOR = "idLocador";
	private static final String LOCATARIO = "idLocatario";
	private static final String BICICLETA = "idBicicleta";
	private static final String DT_INICIO = "dtInicio";
	private static final String DT_FIM_PREVISTO = "dtFimPrevisto";
	private static final String DT_FIM_REALIZADO = "dtFimRealizado";
	private static final String VALOR_PREVISTO = "valorPrevisto";
	private static final String VALOR_MULTA = "valorMulta";
	private static final String VALOR_FINAL = "valorFinal";
	private static final String STATUS = "status";
	
	private Properties prop = getProp();
	private Properties stringSQL = getSqls();
	private BicicletaDAO bicDAO = new BicicletaDAO();
	private LocatarioDAO locatarioDAO = new LocatarioDAO();
	private LocadorDAO locadorDAO = new LocadorDAO();
	
	public AluguelDAO() {}
	
	public boolean adicionarSolicitacao(Locador locador, Locatario locatario, Bicicleta bike, LocalDate dtInicio, LocalDate dtFim, double valorPrevisto, String status) {
		Connection con = ConnectionFactory.getConnection();
		String sql = stringSQL.getProperty("AluguelDAO.adicionarSolicitacao");
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, locador.getId());
			stmt.setInt(2, locatario.getId());
			stmt.setInt(3, bike.getId());
			stmt.setDate(4, Date.valueOf(dtInicio));
			stmt.setDate(5, Date.valueOf(dtFim));
			stmt.setDouble(6, valorPrevisto);
			stmt.setString(7, status);
			stmt.executeUpdate();
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("AluguelDAO.adicionarSolicitacao.Fail") + " " + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
		
		JOptionPane.showMessageDialog(null, prop.getProperty("AluguelDAO.adicionarSolicitacao.Sucesso"));
		return true;
	}
	
	public List<Aluguel> listarSolicitacoesPendentes(Locador loc){
		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = stringSQL.getProperty("AluguelDAO.listarSolicitacoesPendentes");

		List<Aluguel> solicitacoes = new LinkedList<>();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, loc.getId());
			stmt.setString(2, Status.SOLICITACAO_ENVIADA_LOCATARIO.getDescricao());
			rs = stmt.executeQuery();

			while (rs.next()) {
				Aluguel aluguel = new Aluguel();
				aluguel.setId(rs.getInt(ID));
				aluguel.setLocador(loc);
				aluguel.setLocatario(locatarioDAO.buscarPorId(rs.getInt(LOCATARIO)));
				aluguel.setBicicleta(bicDAO.retornarBikePorId(rs.getInt(BICICLETA)));
				aluguel.setDtInicio(rs.getDate(DT_INICIO).toLocalDate());
				aluguel.setDtFimPrevisto(rs.getDate(DT_FIM_PREVISTO).toLocalDate());
				aluguel.setValorPrevisto(rs.getDouble(VALOR_PREVISTO));
				
				solicitacoes.add(aluguel);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("AluguelDAO.listarSolicitacoesPendentes.Fail") + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}

		System.out.println(prop.getProperty("AluguelDAO.listarSolicitacoesPendentes.Sucesso"));

		return solicitacoes;
		
	}

	public boolean cancelarSolicitacao(Aluguel solicitacao) {
		Connection con = ConnectionFactory.getConnection();
		String sql = stringSQL.getProperty("AluguelDAO.cancelarSolicitacao");

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, Status.CANCELADO_PELO_LOCADOR.getDescricao());
			stmt.setInt(2, solicitacao.getId());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, prop.getProperty("AluguelDAO.cancelarSolicitacao.Sucesso"));

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("AluguelDAO.cancelarSolicitacao.Fail") + " " + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}

		return true;
	
	}

	public boolean aceitarSolicitacao(Aluguel solicitacao) {
		Connection con = ConnectionFactory.getConnection();
		String sql = stringSQL.getProperty("AluguelDAO.aceitarSolicitacao");

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1,Status.AGUARDANDO_INICIO.getDescricao());
			stmt.setInt(2, solicitacao.getId());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, prop.getProperty("AluguelDAO.aceitarSolicitacao.Sucesso"));

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("AluguelDAO.aceitarSolicitacao.Fail") + " " + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}

		return true;
	}
	
	public List<Aluguel> buscarAlugueisPendentesLocatario(Locatario loc) {
		
		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = stringSQL.getProperty("AluguelDAO.buscarAluguelPendenteLocatario");

		List<Aluguel> solicitacoes = new LinkedList<>();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, loc.getId());
			stmt.setString(2, Status.AGUARDANDO_INICIO.getDescricao());
			rs = stmt.executeQuery();

			while (rs.next()) {
				Aluguel aluguel = new Aluguel();
				aluguel.setId(rs.getInt(ID));
				aluguel.setLocatario(loc);
				aluguel.setLocador(locadorDAO.buscarPorId(rs.getInt(LOCADOR)));
				aluguel.setBicicleta(bicDAO.retornarBikePorId(rs.getInt(BICICLETA)));
				aluguel.setDtInicio(rs.getDate(DT_INICIO).toLocalDate());
				aluguel.setDtFimPrevisto(rs.getDate(DT_FIM_PREVISTO).toLocalDate());
				aluguel.setValorPrevisto(rs.getDouble(VALOR_PREVISTO));
				
				solicitacoes.add(aluguel);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, prop.getProperty("AluguelDAO.buscarAluguelPendenteLocatario.Fail") + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}

		System.out.println(prop.getProperty("AluguelDAO.buscarAluguelPendenteLocatario.Sucesso"));

		return solicitacoes;
	}
	
	
	
}
