package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.Bicicleta;
import model.Locador;

public class BicicletaDAO {

	public BicicletaDAO() {
	}

	public boolean salvar(Locador loc, Bicicleta bic) {
		Connection con = ConnectionFactory.getConnection();

		String sql = "insert into bicicleta (idLocador, modelo, ano, valorDeAluguel, acessorios, disponivel) values (?, ?, ?, ?, ?, ?)";

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
			JOptionPane.showMessageDialog(null, "Bicicleta cadastrada com sucesso!");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					"Houve um erro ao salvar a bicicleta no banco de dados." + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return true;
	}

}
