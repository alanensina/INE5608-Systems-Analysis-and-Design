package service;

import static service.UtilsService.getProp;

import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;

import javax.swing.JOptionPane;

import dao.AluguelDAO;
import dao.BicicletaDAO;
import dao.CarteiraLocatarioDAO;
import enumeration.Status;
import model.Aluguel;
import model.Bicicleta;
import model.CarteiraLocatario;
import model.Locador;
import model.Locatario;

public class AluguelService {
	private Properties prop = getProp();
	private AluguelDAO dao = new AluguelDAO();
	private BicicletaDAO bicDAO = new BicicletaDAO();
	private CarteiraLocatarioDAO cartDAO = new CarteiraLocatarioDAO();

	public boolean validaDatas(Date dtInicio, Date dtFim) {
		if (dtInicio == null || dtFim == null) {
			JOptionPane.showMessageDialog(null, prop.getProperty("AluguelService.Message.dataInvalida"));
			return false;
		}
		return true;
	}

	public boolean isDataInicioDepoisDataFim(LocalDate dtInicio, LocalDate dtFim) {
		if (dtInicio.isAfter(dtFim)) {
			JOptionPane.showMessageDialog(null, prop.getProperty("AluguelService.Message.dataInicioAntesDaDataFim"));
			return true;
		}
		return false;
	}

	public boolean enviarSolicitacaoDeAluguel(Locatario locatario, Locador locador, Bicicleta bic, LocalDate dtInicio,
			LocalDate dtFim, double valorPrevisto) {
		
		//TODO: Validar se o locatário está com algum aluguel ativo

		if(checarMultasPendentes(locatario)) {
			return false;
		}
		
		return dao.adicionarSolicitacao(locador, locatario, bic, dtInicio, dtFim, valorPrevisto, Status.SOLICITACAO_ENVIADA_LOCATARIO.getDescricao());
	}

	public boolean checarMultasPendentes(Locatario locatario) {
		CarteiraLocatario carteira = cartDAO.retornaPorId(locatario);

		if (carteira == null) {
			return false;
		}

		else if (carteira.getMultaAcumulada() > 0) {
			JOptionPane.showMessageDialog(null,
					prop.getProperty("AluguelService.Message.multaEncontrada")
							+ carteira.getMultaAcumulada());
			return true;
		}

		return false;
	}

	public boolean cancelarSolicitacao(Aluguel solicitacao) {
		return dao.cancelarSolicitacao(solicitacao);
	}

	public boolean aceitarSolicitacao(Aluguel solicitacao) {
		if (dao.aceitarSolicitacao(solicitacao)) {
			return bicDAO.atualizaDisponibilidade(solicitacao.getBicicleta());
		}
		return false;
	}

}
