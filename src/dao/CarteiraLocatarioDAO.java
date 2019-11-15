package dao;

import static service.UtilsService.getProp;
import static service.UtilsService.getSqls;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import enumeration.Status;
import model.Aluguel;
import model.CarteiraLocatario;
import model.Locador;
import model.Locatario;

public class CarteiraLocatarioDAO {

	// Colunas no BD
	private static final String ID = "id";
	private static final String LOCATARIO = "idLocatario";
	private static final String MULTA = "multaAcumulada";
	
	private Properties prop = getProp();
	private Properties stringSQL = getSqls();
	
	public CarteiraLocatario retornaPorId(Locatario loc) {
		Connection con = ConnectionFactory.getConnection();
		String sql = stringSQL.getProperty("CarteiraLocatarioDAO.retornarPorIdLocatario");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CarteiraLocatario carteira = new CarteiraLocatario();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, loc.getId());
			rs = stmt.executeQuery();

			while (rs.next()) {

				carteira.setId(rs.getInt(ID));
				carteira.setLocatario(loc);
				carteira.setMultaAcumulada(rs.getDouble(MULTA));

			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					prop.getProperty("CarteiraLocatarioDAO.retornarPorIdLocatario.Fail") + " " + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		System.out.println(prop.getProperty("CarteiraLocatarioDAO.retornarPorIdLocatario.Sucesso"));
		
		return carteira;
	}

	public void adicionarMulta(Aluguel aluguel, double multa) {
		Connection con = ConnectionFactory.getConnection();
		String sql = stringSQL.getProperty("CarteiraLocatarioDAO.adicionarMulta");
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setDouble(1, multa);
			stmt.setInt(2, aluguel.getLocatario().getId());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public void inicializaCarteira(Locatario loc) {
		
		Connection con = ConnectionFactory.getConnection();
		String sql = stringSQL.getProperty("CarteiraLocatarioDAO.inicializaCarteira");
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, loc.getId());
			stmt.setDouble(2, 0);
			
			stmt.executeUpdate();
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
}
