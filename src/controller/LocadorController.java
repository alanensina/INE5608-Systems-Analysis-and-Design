package controller;

import model.Endereco;
import model.Locador;
import service.LocadorService;

public class LocadorController {
	
	private LocadorService service = new LocadorService();
	
	public LocadorController() {}
	
	public boolean enviaParaService(Locador loc, Endereco end) throws Exception {
		return service.validaLocador(loc,end);
	}
	
	public boolean enviaParaServiceAtualizar(Locador loc, Endereco end) throws Exception {
		return service.validaAtualizacaoLocador(loc,end);
	}
}
