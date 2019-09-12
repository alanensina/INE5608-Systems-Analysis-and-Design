package app;

import view.TelaMenuPrincipalLocador;
import view.TelaMenuPrincipalLocatario;

public class Main {

	public static void main(String[] args) {
		
		//inicializa(args);
		
		TelaMenuPrincipalLocador telaLocador = new TelaMenuPrincipalLocador(args);
		telaLocador.inicializaTela();
		
//		TelaMenuPrincipalLocatario telaLocatario = new TelaMenuPrincipalLocatario();
//		telaLocatario.inicializaTela();
		
	}
}