package dao;

import static service.UtilsService.getProp;
import static service.UtilsService.getSqls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
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
	
}
