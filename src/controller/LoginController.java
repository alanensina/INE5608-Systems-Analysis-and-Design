package controller;

import service.LoginService;

public class LoginController {
	
	private LoginService service = new LoginService();

	public LoginController(){}
	
	public boolean enviaParaService(String login, String senha) throws Exception {
		return service.validaLogin(login,senha);
	}
}
