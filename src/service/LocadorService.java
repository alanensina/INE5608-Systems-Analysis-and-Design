package service;

import static service.UtilsService.getProp;
import static service.UtilsService.validaCampoObrigatorio;

import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.swing.JOptionPane;

import dao.BicicletaDAO;
import dao.CarteiraLocadorDAO;
import dao.EnderecoDAO;
import dao.LocadorDAO;
import dao.LocatarioDAO;
import model.CarteiraLocador;
import model.Endereco;
import model.Locador;
import model.Locatario;

public class LocadorService {

	private LocadorDAO locadorDAO = new LocadorDAO();
	private BicicletaDAO bikeDAO = new BicicletaDAO();
	private LocatarioDAO locatarioDAO = new LocatarioDAO();
	UtilsService utils = new UtilsService();
	private Properties prop = getProp();
	private CarteiraLocadorDAO carteiraDAO = new CarteiraLocadorDAO();

	public LocadorService() {
	}

	public boolean validaLocador(Locador loc, Endereco end) throws Exception {
		converterSenha(loc);

		if (loginUtilizado(loc.getLogin())) {
			return false;
		}

		boolean status = locadorDAO.salvar(loc, end);
		
		try {
			Locador locador = locadorDAO.buscarPorLogin(loc.getLogin());
			carteiraDAO.inicializaCarteira(locador);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return status;
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
			JOptionPane.showMessageDialog(null, prop.getProperty("Utils.Message.LoginJaUtilizado"));
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

	public boolean deletarBicicletas(Locador locador) {
		return bikeDAO.deletarTodasAsBicicletas(locador);
	}

	public boolean deletarLocador(Locador locador) {
		return locadorDAO.deletarLocador(locador, locador.getEndereco());
	}

}
