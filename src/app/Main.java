package app;

import view.TelaMenuPrincipalLocador;
import view.TelaMenuPrincipalLocatario;

public class Main {

	public static void main(String[] args) {
		
		//inicializa(args);
		
		TelaMenuPrincipalLocador tela = new TelaMenuPrincipalLocador(args);
		tela.inicializaTela();
		
//		TelaMenuPrincipalLocatario tela = new TelaMenuPrincipalLocatario();
//		tela.inicializaTela();
		
	}
}