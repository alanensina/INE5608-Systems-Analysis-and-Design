package dao;

import static service.UtilsService.getProp;
import static service.UtilsService.getSqls;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Properties;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
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
	private static final String PENDENTE = "pendente";
	private static final String CANCELADO = "cancelado";
	private static final String INICIADO = "iniciado";
	
	private Properties prop = getProp();
	private Properties stringSQL = getSqls();
	
	public AluguelDAO() {}
	
	public boolean adicionarSolicitacao(Locador locador, Locatario locatario, Bicicleta bike, LocalDate dtInicio, LocalDate dtFim, double valorPrevisto) {
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
	
	
	
}
