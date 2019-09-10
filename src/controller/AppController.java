package controller;

import view.TelaLogin;

public class AppController {
	
	public static void inicializa(String[] args) {
		TelaLogin login = new TelaLogin(args);
		login.setVisible(true);
	}

}
