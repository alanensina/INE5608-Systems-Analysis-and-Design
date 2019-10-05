package service;

import static service.UtilsService.validaCampoObrigatorio;

import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;

import dao.LocadorDAO;
import dao.LocatarioDAO;
import model.Endereco;
import model.Locador;
import model.Locatario;

public class LocadorService {

	private LocadorDAO locadorDAO = new LocadorDAO();
	private LocatarioDAO locatarioDAO = new LocatarioDAO();
	UtilsService utils = new UtilsService();

	public LocadorService() {
	}

	public boolean validaLocador(Locador loc, Endereco end) throws Exception {
		converterSenha(loc);

		if (loginUtilizado(loc.getLogin())) {
			return false;
		}

		return locadorDAO.salvar(loc, end);
	}

	public boolean validaAtualizacaoLocador(Locador loc, Endereco end) throws Exception {
		converterSenha(loc);

		return locadorDAO.editarLocador(loc, end);
	}

	public void converterSenha(Locador loc) throws NoSuchAlgorithmException {
		loc.setSenha(UtilsService.converterMD5(loc.getSenha()));
	}

	// Verifica se o login ja foi cadastrado
	public boolean loginUtilizado(String login) {
		Locador locador = locadorDAO.buscarPorLogin(login);
		Locatario locatario = locatarioDAO.buscarPorLogin(login);

		if (locador.getId() != 0 || locatario.getId() != 0) {
			JOptionPane.showMessageDialog(null, "Login já utilizado!");
			return true;
		}

		return false;
	}

	public static boolean validaCamposLocador(Locador loc, Endereco end) {

		return (validaCampoObrigatorio(loc.getNome()) || validaCampoObrigatorio(loc.getCpf())
				|| validaCampoObrigatorio(loc.getCelular()) || validaCampoObrigatorio(loc.getLogin())
				|| validaCampoObrigatorio(loc.getSenha()) || validaCampoObrigatorio(end.getLogradouro())
				|| validaCampoObrigatorio(end.getBairro()) || validaCampoObrigatorio(end.getNumero())
				|| validaCampoObrigatorio(end.getCep()) || validaCampoObrigatorio(end.getCidade())
				|| validaCampoObrigatorio(end.getEstado()));
	}

}
