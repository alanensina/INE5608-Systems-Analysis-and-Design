package service;

import java.time.LocalDate;
import java.util.Date;

import javax.swing.JOptionPane;

import dao.AluguelDAO;
import model.Bicicleta;
import model.Locador;
import model.Locatario;

public class AluguelService {
	private AluguelDAO dao = new AluguelDAO();

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

	public boolean enviarSolicitacaoDeAluguel(Locatario locatario, Locador locador, Bicicleta bic, LocalDate dtInicio, LocalDate dtFim, double valorPrevisto) {
		return dao.adicionarSolicitacao(locador, locatario, bic, dtInicio, dtFim, valorPrevisto);
	}
}
