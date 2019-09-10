package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.Endereco;
import model.Locatario;

public class LocatarioDAO {

	private EnderecoDAO edao = new EnderecoDAO();
	private Connection con = null;

	public LocatarioDAO() {}
	
	public boolean salvar(Locatario loc, Endereco end) {
		
		edao.salvar(end);
		int idEndereco = edao.retornaIdEndereco(end);
		
		con = ConnectionFactory.getConnection();
		String sql = "insert into locatario (nome, cpf, idEndereco, celular, login, senha, dataCadastro) values (?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, loc.getNome()); 
			stmt.setString(2, loc.getCpf());
			stmt.setInt(3, idEndereco); 
			stmt.setString(4, loc.getCelular());
			stmt.setString(5, loc.getLogin());
			stmt.setString(6, loc.getSenha());
			stmt.setDate(7, Date.valueOf(loc.getDataCadastro()));
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Locatário registrado com sucesso!");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao salvar o locatário no banco de dados (LocatarioDAO.salvar)" + ex);
			throw new RuntimeException(ex);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return true;
	}
	
	public boolean editarLocador(Locatario loc ) { return false; }
	
	public boolean deletarLocador(Locatario loc ) { return false; }
	
	public List<Locatario> listarLocatarios() { return null; }
	
	public Locatario retornaPorID(int id) { return null; }
}
