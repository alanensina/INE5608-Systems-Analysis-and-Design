package dao;

import static service.UtilsService.getProp;
import static service.UtilsService.getSqls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.Aluguel;
import model.CarteiraLocador;
import model.CarteiraLocatario;
import model.Locador;
import model.Locatario;

public class CarteiraLocadorDAO {

	private static final String ID = "id";
	private static final String LOCADOR = "idLocador";
	private static final String SALDO = "saldo";

	private Properties prop = getProp();
	private Properties stringSQL = getSqls();

	public CarteiraLocadorDAO() {
	}

	public void inicializaCarteira(Locador loc) {

		Connection con = ConnectionFactory.getConnection();
		String sql = stringSQL.getProperty("CarteiraLocadorDAO.inicializaCarteira");
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
	
	public void adicionaSaldo(Aluguel aluguel, double saldo) {
		Connection con = ConnectionFactory.getConnection();
		String sql = stringSQL.getProperty("CarteiraLocadorDAO.adicionaSaldo");

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setDouble(1, saldo);
			stmt.setInt(2, aluguel.getLocador().getId());
			
			stmt.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public CarteiraLocador retornaPorId(Locador loc) {
		Connection con = ConnectionFactory.getConnection();
		String sql = stringSQL.getProperty("CarteiraLocadorDAO.retornarPorIdLocador");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CarteiraLocador carteira = new CarteiraLocador();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, loc.getId());
			rs = stmt.executeQuery();

			while (rs.next()) {

				carteira.setId(rs.getInt(ID));
				carteira.setLocador(loc);
				carteira.setSaldo(rs.getDouble(SALDO));

			}
		} catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return carteira;
	}

	public void adicionaMulta(Aluguel aluguel, double multa) {
		Connection con = ConnectionFactory.getConnection();
		String sql = stringSQL.getProperty("CarteiraLocadorDAO.adicionarMulta");
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setDouble(1, multa);
			stmt.setInt(2, aluguel.getLocador().getId());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}

}
