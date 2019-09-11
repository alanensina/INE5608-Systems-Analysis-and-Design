package service;

import java.security.NoSuchAlgorithmException;

import dao.LocadorDAO;
import model.Endereco;
import model.Locador;

public class LocadorService {
	
	LocadorDAO locDAO = new LocadorDAO();
	UtilsService utils = new UtilsService();
	
	public LocadorService() {}

	public boolean validaLocador(Locador loc, Endereco end) throws Exception {
		converterSenha(loc);
		return locDAO.salvar(loc, end);
	}
	
	public void converterSenha(Locador loc) throws NoSuchAlgorithmException {
		loc.setSenha(utils.converterMD5(loc.getSenha()));
	}
	
	
	

}
