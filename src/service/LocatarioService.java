package service;

import java.security.NoSuchAlgorithmException;

import dao.LocatarioDAO;
import model.Endereco;
import model.Locatario;

public class LocatarioService {

	LocatarioDAO locDAO = new LocatarioDAO();
	UtilsService utils = new UtilsService();
	
	public LocatarioService() {}

	public boolean validaLocatario(Locatario loc, Endereco end) throws Exception {
		converterSenha(loc);
		return locDAO.salvar(loc, end);
	}
	
	public void converterSenha(Locatario loc) throws NoSuchAlgorithmException {
		loc.setSenha(utils.converterMD5(loc.getSenha()));
	}
}
