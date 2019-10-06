package controller;

import model.Bicicleta;
import model.Locador;
import service.BicicletaService;

public class BicicletaController {
	private BicicletaService service = new BicicletaService();
	
	public BicicletaController() {}
	
	public boolean enviaParaValidacoesObrigatorias(Bicicleta bic) {
		return service.validaDados(bic);
	}
	
	public void salvar(Bicicleta bic, Locador loc) {
		service.salvarBicicleta(bic,loc);
	}

	public boolean deletar(Bicicleta bic) {
		return service.deletar(bic);
	}

	public boolean atualizar(Bicicleta bic) {
		return service.validarBikeParaAtualizar(bic);
	}	
}