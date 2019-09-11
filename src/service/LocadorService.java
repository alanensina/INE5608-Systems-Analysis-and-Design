package service;

import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;

import dao.LocadorDAO;
import dao.LocatarioDAO;
import model.Endereco;
import model.Locador;
import model.Locatario;

public class LocadorService {
	
	private LocadorDAO locadorDAO = new LocadorDAO();
	private LocatarioDAO locatarioDAO = new LocatarioDAO();
	UtilsService utils = new UtilsService();
	
	public LocadorService() {}

	public boolean validaLocador(Locador loc, Endereco end) throws Exception {
		converterSenha(loc);
		
		if(loginUtilizado(loc.getLogin())) {
			return false;
		}		
		
		return locadorDAO.salvar(loc, end);
	}
	
	public void converterSenha(Locador loc) throws NoSuchAlgorithmException {
		loc.setSenha(utils.converterMD5(loc.getSenha()));
	}	
	
	// Verifica se o login ja foi cadastrado
	public boolean loginUtilizado(String login) {
		Locador locador = locadorDAO.buscarPorLogin(login);
		Locatario locatario = locatarioDAO.buscarPorLogin(login);
		
		if(locador.getId() != 0 || locatario.getId() != 0) {
			JOptionPane.showMessageDialog(null, "Login já utilizado!");
			return true;
		}		
		
		return false;
	}
	
	
	

}
