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

	public AluguelDAO() {
	}

	public boolean adicionarSolicitacao(Locador locador, Locatario locatario, Bicicleta bike, LocalDate dtInicio,
			LocalDate dtFim, double valorPrevisto, String status) {
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

	public List<Aluguel> listarSolicitacoesPendentes(Locador loc) {
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
			stmt.setString(1, Status.AGUARDANDO_INICIO.getDescricao());
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
			JOptionPane.showMessageDialog(null,
					prop.getProperty("AluguelDAO.buscarAluguelPendenteLocatario.Fail") + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}

		System.out.println(prop.getProperty("AluguelDAO.buscarAluguelPendenteLocatario.Sucesso"));

		return solicitacoes;
	}

	public void enviaSolicitacaoDeInicioDeAluguel(Aluguel aluguel) {
		Connection con = ConnectionFactory.getConnection();
		String sql = stringSQL.getProperty("AluguelDAO.enviaSolicitacaoDeInicioDeAluguel");

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, Status.INICIADO_PELO_LOCATARIO.getDescricao());
			stmt.setInt(2, aluguel.getId());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null,
					prop.getProperty("AluguelDAO.enviaSolicitacaoDeInicioDeAluguel.Sucesso"));

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					prop.getProperty("AluguelDAO.enviaSolicitacaoDeInicioDeAluguel.Fail") + " " + ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}

	public void enviarSolicitacaoDeCancelamentoDeAluguelPeloLocatario(Aluguel aluguel) {
		Connection con = ConnectionFactory.getConnection();
		String sql = stringSQL.getProperty("AluguelDAO.enviarSolicitacaoDeCancelamentoDeAluguel");

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, Status.CANCELADO_PELO_LOCATARIO.getDescricao());
			stmt.setDouble(2, (aluguel.getValorPrevisto() / 2));
			stmt.setDouble(3, (aluguel.getValorPrevisto() / 2));
			stmt.setInt(4, aluguel.getId());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null,
					prop.getProperty("AluguelDAO.enviarSolicitacaoDeCancelamentoDeAluguel.Sucesso"));

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					prop.getProperty("AluguelDAO.enviarSolicitacaoDeCancelamentoDeAluguel.Fail") + " " + ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}

	public List<Aluguel> buscarAlugueisPendentesLocador(Locador loc) {

		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = stringSQL.getProperty("AluguelDAO.buscarAluguelPendenteLocador");

		List<Aluguel> solicitacoes = new LinkedList<>();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, loc.getId());
			stmt.setString(2, Status.INICIADO_PELO_LOCATARIO.getDescricao());
			rs = stmt.executeQuery();

			while (rs.next()) {
				Aluguel aluguel = new Aluguel();
				aluguel.setId(rs.getInt(ID));
				aluguel.setLocatario(locatarioDAO.buscarPorId(rs.getInt(LOCATARIO)));
				aluguel.setLocador(loc);
				aluguel.setBicicleta(bicDAO.retornarBikePorId(rs.getInt(BICICLETA)));
				aluguel.setDtInicio(rs.getDate(DT_INICIO).toLocalDate());
				aluguel.setDtFimPrevisto(rs.getDate(DT_FIM_PREVISTO).toLocalDate());
				aluguel.setValorPrevisto(rs.getDouble(VALOR_PREVISTO));

				solicitacoes.add(aluguel);
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}

		return solicitacoes;
	}

	public void confirmarInicioDeAluguel(Aluguel aluguel) {
		Connection con = ConnectionFactory.getConnection();
		String sql = stringSQL.getProperty("AluguelDAO.confirmarInicioDeAluguel");

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, Status.ALUGUEL_INICIADO.getDescricao());
			stmt.setInt(2, aluguel.getId());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null,
					prop.getProperty("AluguelDAO.confirmarInicioDeAluguel.Sucesso"));

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					prop.getProperty("AluguelDAO.confirmarInicioDeAluguel.Fail") + " " + ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}

	public void enviarSolicitacaoDeCancelamentoDeAluguelPeloLocador(Aluguel aluguel) {
		Connection con = ConnectionFactory.getConnection();
		String sql = stringSQL.getProperty("AluguelDAO.enviarSolicitacaoDeCancelamentoDeAluguel");

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, Status.CANCELADO_PELO_LOCADOR.getDescricao());
			stmt.setDouble(2, (aluguel.getValorPrevisto() / 2));
			stmt.setDouble(3, (aluguel.getValorPrevisto() / 2));
			stmt.setInt(4, aluguel.getId());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null,
					prop.getProperty("AluguelDAO.enviarSolicitacaoDeCancelamentoDeAluguel.Sucesso"));

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					prop.getProperty("AluguelDAO.enviarSolicitacaoDeCancelamentoDeAluguel.Fail") + " " + ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}

	public boolean verificarAluguelAtivo(Locatario locatario) {
		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = stringSQL.getProperty("AluguelDAO.buscarAlugueisAtivos");

		List<Aluguel> alugueisEmAndamento = new LinkedList<>();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, locatario.getId());
			stmt.setString(2, Status.ALUGUEL_INICIADO.getDescricao());
			rs = stmt.executeQuery();

			while (rs.next()) {
				Aluguel aluguel = new Aluguel();
				aluguel.setId(rs.getInt(ID));
				aluguel.setLocatario(locatario);
				aluguel.setLocador(locadorDAO.buscarPorId(rs.getInt(LOCADOR)));
				aluguel.setBicicleta(bicDAO.retornarBikePorId(rs.getInt(BICICLETA)));
				aluguel.setDtInicio(rs.getDate(DT_INICIO).toLocalDate());
				aluguel.setDtFimPrevisto(rs.getDate(DT_FIM_PREVISTO).toLocalDate());
				aluguel.setValorPrevisto(rs.getDouble(VALOR_PREVISTO));

				alugueisEmAndamento.add(aluguel);
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		if(alugueisEmAndamento.isEmpty()) {
			return false;
		}
		
		JOptionPane.showMessageDialog(null,
				prop.getProperty("AluguelDAO.alugueisAtivosEncontrados"));
		return true;
	}

	public List<Aluguel> buscarAluguelAtivo(Locatario loc) {
		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = stringSQL.getProperty("AluguelDAO.buscarAlugueisAtivos");

		List<Aluguel> alugueisAtivos = new LinkedList<>();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, loc.getId());
			stmt.setString(2, Status.ALUGUEL_INICIADO.getDescricao());
			rs = stmt.executeQuery();

			while (rs.next()) {
				Aluguel aluguel = new Aluguel();
				aluguel.setId(rs.getInt(ID));
				aluguel.setLocador(locadorDAO.buscarPorId(rs.getInt(LOCADOR)));
				aluguel.setLocatario(loc);
				aluguel.setBicicleta(bicDAO.retornarBikePorId(rs.getInt(BICICLETA)));
				aluguel.setDtInicio(rs.getDate(DT_INICIO).toLocalDate());
				aluguel.setDtFimPrevisto(rs.getDate(DT_FIM_PREVISTO).toLocalDate());
				aluguel.setValorPrevisto(rs.getDouble(VALOR_PREVISTO));

				alugueisAtivos.add(aluguel);
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return alugueisAtivos;
	}

	public void finalizarAluguel(Aluguel aluguel, double multa, double valorFinal) {
		Connection con = ConnectionFactory.getConnection();
		String sql = stringSQL.getProperty("AluguelDAO.finalizarAluguel");
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, Status.DEVOLUCAO_INICIADA.getDescricao());
			stmt.setDate(2, Date.valueOf(LocalDate.now()));
			stmt.setDouble(3, multa);
			stmt.setDouble(4, valorFinal);
			stmt.setInt(5, aluguel.getId());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null,
					prop.getProperty("AluguelDAO.finalizarAluguel.Sucesso"));

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					prop.getProperty("AluguelDAO.finalizarAluguel.Fail") + " " + ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public List<Aluguel> buscarAlugueisFinalizados(Locador loc) {
		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = stringSQL.getProperty("AluguelDAO.buscarAlugueisFinalizados");

		List<Aluguel> alugueisAtivos = new LinkedList<>();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, loc.getId());
			stmt.setString(2, Status.DEVOLUCAO_INICIADA.getDescricao());
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
				aluguel.setValorMulta(rs.getDouble(VALOR_MULTA));
				aluguel.setValorFinal(rs.getDouble(VALOR_FINAL));
				aluguel.setDtFimRealizado(rs.getDate(DT_FIM_REALIZADO).toLocalDate());

				alugueisAtivos.add(aluguel);
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return alugueisAtivos;
	}

	public void encerrarAluguel(Aluguel aluguel) {
		Connection con = ConnectionFactory.getConnection();
		String sql = stringSQL.getProperty("AluguelDAO.encerrarAluguel");
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, Status.ALUGUEL_FINALIZADO.getDescricao());
			stmt.setInt(2, aluguel.getId());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null,
					prop.getProperty("AluguelDAO.encerrarAluguel.Sucesso"));

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					prop.getProperty("AluguelDAO.encerrarAluguel.Fail") + " " + ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}

	public void finalizarAluguelSemDevolucao(Aluguel aluguel) {
		Connection con = ConnectionFactory.getConnection();
		String sql = stringSQL.getProperty("AluguelDAO.finalizarAluguelSemDevolucao");
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, Status.AGUARDANDO_DEVOLUCAO.getDescricao());
			stmt.setDouble(2, aluguel.getValorMulta());
			stmt.setDouble(3, aluguel.getValorFinal());
			stmt.setInt(4, aluguel.getId());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null,
					prop.getProperty("AluguelDAO.AguardandoDevolucao.Sucesso"));

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					prop.getProperty("AluguelDAO.AguardandoDevolucao.Fail") + " " + ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
}
