package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.Bicicleta;
import model.Endereco;
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
			JOptionPane.showMessageDialog(null, "Houve um erro ao salvar a bicicleta no banco de dados." + ex);
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
		String sql = "select * from bicicleta where idLocador = ?";

		List<Bicicleta> bikes = new LinkedList<>();

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, loc.getId());
			rs = stmt.executeQuery();

			while (rs.next()) {
				Bicicleta bic = new Bicicleta();
				bic.setLocador(loc);
				bic.setId(rs.getInt(1));
				bic.setModelo(rs.getString("modelo"));
				bic.setAno(rs.getString("ano"));
				bic.setValorDeAluguel(rs.getDouble("valorDeAluguel"));
				bic.setAcessorios(rs.getString("acessorios"));
				bic.setDisponivel(rs.getBoolean("disponivel"));
				bikes.add(bic);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao listar as bicicletas do banco de dados." + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}

		return bikes;
	}
	
	public Bicicleta retornarBikePorId(Bicicleta bic) {
		
		Connection con = ConnectionFactory.getConnection();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from bicicleta where id = ?";

		//Bicicleta bic = new Endereco();
		
		
		
		return null;
	}
	
	public boolean deletar(Bicicleta bic) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "delete from bicicleta where id = ? ";

		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, bic.getId());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Bicicleta deletada com sucesso.");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao deletar a bicicleta no banco de dados." + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
		return true;
	}
	
	public boolean atualizar(Bicicleta bic) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "update bicicleta set modelo = ?," + " ano = ?, valorDeAluguel = ?, acessorios = ?, disponivel = ? where id = ?";

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
			JOptionPane.showMessageDialog(null, "Bicicleta atualizada com sucesso!");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao atualizar a bicicleta no banco de dados. " + ex);
			return false;
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return true;
	}

}
