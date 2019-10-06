package service;

import static service.UtilsService.getProp;

import java.util.Properties;

import javax.swing.JOptionPane;

import dao.BicicletaDAO;
import model.Bicicleta;
import model.Locador;

public class BicicletaService {
	private Properties prop = getProp();
	private BicicletaDAO dao = new BicicletaDAO();

	public BicicletaService() {
	}

	public boolean validaDados(Bicicleta bic) {
		if (bic.getModelo() == null || bic.getModelo().isEmpty() || bic.getValorDeAluguel() <= 0) {
			JOptionPane.showMessageDialog(null, prop.getProperty("Service.Bicicleta.CamposInvalidos"));
			return false;
		}
		return true;
	}

	public void salvarBicicleta(Bicicleta bic, Locador loc) {
		dao.salvar(loc, bic);
	}

}
