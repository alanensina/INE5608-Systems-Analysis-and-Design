package controller;

import model.Endereco;
import model.Locatario;
import service.LocatarioService;

public class LocatarioController {

private LocatarioService service = new LocatarioService();
	
	public LocatarioController() {}
	
	public boolean enviaParaService(Locatario loc, Endereco end) throws Exception {
		return service.validaLocatario(loc,end);
	}
	
	public boolean enviaParaServiceAtualizar(Locatario loc, Endereco end) throws Exception {
		return service.validaAtualizacaoLocatario(loc,end);
	}

	public boolean enviarParaDeletar(Locatario locatario) {
		return service.enviarParaDeletar(locatario);
	}
}
