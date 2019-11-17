package controller;

import java.time.LocalDate;
import java.util.Date;

import model.Aluguel;
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

	public boolean verificaSeHaMultasPendentes(Locatario locatario) {
		return service.checarMultasPendentes(locatario);
	}

	public boolean recusarSolicitacao(Aluguel solicitacao) {
		return service.cancelarSolicitacao(solicitacao);
	}

	public boolean aceitarSolicitacao(Aluguel solicitacao) {
		return service.aceitarSolicitacao(solicitacao);
	}

	public void enviaSolicitacaoDeInicioDeAluguel(Aluguel aluguel) {
		service.enviaSolicitacaoDeInicioDeAluguel(aluguel);
	}

	public void enviarSolicitacaoDeCancelamentoDeAluguel(Aluguel aluguel) {
		service.enviarSolicitacaoDeCancelamentoDeAluguelPeloLocatario(aluguel);
	}

	public void confirmarInicioDeAluguel(Aluguel aluguel) {
		service.confirmarInicioDeAluguel(aluguel);
	}

	public void enviarSolicitacaoDeCancelamentoDeAluguelPeloLocador(Aluguel aluguel) {
		service.enviarSolicitacaoDeCancelamentoDeAluguelPeloLocador(aluguel);		
	}

	public void enviarSolicitacaoDeFinalizacao(Aluguel alug, double valorFinal) {
		service.finalizarAluguel(alug, valorFinal);
	}

	public void finalizarAluguel(Aluguel aluguel) {
		service.encerrarAluguel(aluguel);
	}

	public void finalizarAluguelSemDevolucao(Aluguel aluguel) {
		service.finalizarAluguelSemDevolucao(aluguel);
		
	}
}
