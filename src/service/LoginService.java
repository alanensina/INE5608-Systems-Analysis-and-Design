package service;

import dao.LocadorDAO;
import dao.LocatarioDAO;
import model.Locador;
import model.Locatario;

public class LoginService {

	private UtilsService utils = new UtilsService();
	private LocadorDAO locadorDAO = new LocadorDAO();
	private LocatarioDAO locatarioDAO = new LocatarioDAO();

	public LoginService() {
	}

	public boolean validaLogin(String login, String senha) throws Exception {
		String senhaCriptografada = utils.converterMD5(senha);
		Locador locador = locadorDAO.buscarPorLogin(login);
		Locatario locatario = locatarioDAO.buscarPorLogin(login);
		
		if(locador.getId() != 0 && locatario.getId() != 0) {
			return false;
		}
		
		else if(locador.getId() != 0) {
			String senhaValida = locador.getSenha();
			return senhaValida.equalsIgnoreCase(senhaCriptografada);
		}
		
		else if(locatario.getId() != 0) {
			String senhaValida = locatario.getSenha();
			return senhaValida.equalsIgnoreCase(senhaCriptografada);
		}		
		
		return false;
	}
	
	public Locador retornaLocador(String login) {
		return locadorDAO.buscarPorLogin(login);
	}
	
	public Locatario retornaLocatario(String login) {
		return locatarioDAO.buscarPorLogin(login);
	}
}
