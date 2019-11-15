package service;

import static service.UtilsService.getProp;
import static service.UtilsService.validaCampoObrigatorio;

import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.swing.JOptionPane;

import dao.CarteiraLocadorDAO;
import dao.CarteiraLocatarioDAO;
import dao.LocadorDAO;
import dao.LocatarioDAO;
import model.Endereco;
import model.Locador;
import model.Locatario;

public class LocatarioService {

	private LocadorDAO locadorDAO = new LocadorDAO();
	private LocatarioDAO locatarioDAO = new LocatarioDAO();
	UtilsService utils = new UtilsService();
	private Properties prop = getProp();
	private CarteiraLocatarioDAO carteiraDAO = new CarteiraLocatarioDAO();

	
	public LocatarioService() {}

	public boolean validaLocatario(Locatario loc, Endereco end) throws Exception {
		converterSenha(loc);
		
		if(loginUtilizado(loc.getLogin())) {
			return false;
		}
		
		boolean status = locatarioDAO.salvar(loc, end);
		
		try {
			Locatario locatario = locatarioDAO.buscarPorLogin(loc.getLogin());
			carteiraDAO.inicializaCarteira(locatario);			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return status;
	}
	
	public void converterSenha(Locatario loc) throws NoSuchAlgorithmException {
		loc.setSenha(utils.converterMD5(loc.getSenha()));
	}
	
	// Verifica se o login ja foi cadastrado
		public boolean loginUtilizado(String login) {
			Locador locador = locadorDAO.buscarPorLogin(login);
			Locatario locatario = locatarioDAO.buscarPorLogin(login);
			
			if(locador.getId() != 0 || locatario.getId() != 0) {
				JOptionPane.showMessageDialog(null, prop.getProperty("Utils.Message.LoginJaUtilizado"));
				return true;
			}		
			
			return false;
		}

		public boolean validaAtualizacaoLocatario(Locatario loc, Endereco end) throws Exception {
			converterSenha(loc);
			
			return locatarioDAO.editarLocatario(loc, end);
		}

		public static boolean validaCamposLocatario(Locatario loc, Endereco end) {

			return (validaCampoObrigatorio(loc.getNome()) || validaCampoObrigatorio(loc.getCpf())
					|| validaCampoObrigatorio(loc.getCelular()) || validaCampoObrigatorio(loc.getLogin())
					|| validaCampoObrigatorio(loc.getSenha()) || validaCampoObrigatorio(end.getLogradouro())
					|| validaCampoObrigatorio(end.getBairro()) || validaCampoObrigatorio(end.getNumero())
					|| validaCampoObrigatorio(end.getCep()) || validaCampoObrigatorio(end.getCidade())
					|| validaCampoObrigatorio(end.getEstado()));
		}

		public boolean enviarParaDeletar(Locatario locatario) {
			return locatarioDAO.deletarLocatario(locatario, locatario.getEndereco());
		}
}
