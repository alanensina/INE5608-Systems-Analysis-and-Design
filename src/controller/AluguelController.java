package controller;

import java.time.LocalDate;
import java.util.Date;

import model.Bicicleta;
import model.Locador;
import model.Locatario;
import service.AluguelService;

public class AluguelController {
	private AluguelService service = new AluguelService();
	
	public AluguelController() {}

	public boolean validaDatas(Date dtInicio, Date dtFim) {
		return service.validaDatas(dtInicio, dtFim);
	}

	public boolean validaDataInicioPosteriorDataFim(LocalDate dtInicio, LocalDate dtFim) {
		return service.isDataInicioDepoisDataFim(dtInicio,dtFim);
	}

	public boolean enviarSolicitacao(Locatario locatario, Locador locador, Bicicleta bic, LocalDate dtInicio, LocalDate dtFim, double valorPrevisto) {
		return service.enviarSolicitacaoDeAluguel(locatario, locador, bic, dtInicio, dtFim, valorPrevisto);
	}

	
}
