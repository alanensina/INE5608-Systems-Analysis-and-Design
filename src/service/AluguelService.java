package service;

import java.time.LocalDate;
import java.util.Date;

import javax.swing.JOptionPane;

import dao.AluguelDAO;
import dao.BicicletaDAO;
import dao.CarteiraLocatarioDAO;
import model.Aluguel;
import model.Bicicleta;
import model.CarteiraLocatario;
import model.Locador;
import model.Locatario;

public class AluguelService {
	private AluguelDAO dao = new AluguelDAO();
	private BicicletaDAO bicDAO = new BicicletaDAO();
	private CarteiraLocatarioDAO cartDAO = new CarteiraLocatarioDAO();

	public boolean validaDatas(Date dtInicio, Date dtFim) {
		if (dtInicio == null || dtFim == null) {
			JOptionPane.showMessageDialog(null, "Digite uma data válida!");
			return false;
		}
		return true;
	}

	public boolean isDataInicioDepoisDataFim(LocalDate dtInicio, LocalDate dtFim) {
		if (dtInicio.isAfter(dtFim)) {
			JOptionPane.showMessageDialog(null, "A data do início do aluguel deve ser anterior a data final.");
			return true;
		}
		return false;
	}

	public boolean enviarSolicitacaoDeAluguel(Locatario locatario, Locador locador, Bicicleta bic, LocalDate dtInicio,
			LocalDate dtFim, double valorPrevisto) {
		return dao.adicionarSolicitacao(locador, locatario, bic, dtInicio, dtFim, valorPrevisto);
	}

	public boolean checarMultasPendentes(Locatario locatario) {
		CarteiraLocatario carteira = cartDAO.retornaPorId(locatario);

		if (carteira == null) {
			return false;
		}

		else if (carteira.getMultaAcumulada() > 0) {
			JOptionPane.showMessageDialog(null,
					"Você não poderá solicitar aluguéis enquanto não acertar suas pendências. O valor atual da sua multa é de R$ "
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
