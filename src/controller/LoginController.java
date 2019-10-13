package controller;

import model.Locador;
import model.Locatario;
import service.LoginService;

public class LoginController {
	
	private LoginService service = new LoginService();

	public LoginController(){}
	
	public boolean enviaParaService(String login, String senha) throws Exception {
		return service.validaLogin(login,senha);
	}
	
	public Locador getLocador(String login) {
		return service.retornaLocador(login);
	}
	
	public Locatario getLocatario(String login) {
		return service.retornaLocatario(login);
	}
}
