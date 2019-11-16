package service;

import static service.UtilsService.getProp;

import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;

import javax.swing.JOptionPane;

import dao.AluguelDAO;
import dao.BicicletaDAO;
import dao.CarteiraLocadorDAO;
import dao.CarteiraLocatarioDAO;
import enumeration.Status;
import model.Aluguel;
import model.Bicicleta;
import model.CarteiraLocador;
import model.CarteiraLocatario;
import model.Locador;
import model.Locatario;

public class AluguelService {
	private Properties prop = getProp();
	private AluguelDAO dao = new AluguelDAO();
	private BicicletaDAO bicDAO = new BicicletaDAO();
	private CarteiraLocatarioDAO carteiraLocatarioDAO = new CarteiraLocatarioDAO();
	private CarteiraLocadorDAO carteiraLocadorDAO = new CarteiraLocadorDAO();

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
		
		if(checarAluguelAtivo(locatario)) {
			return false;
		}

		if(checarMultasPendentes(locatario)) {
			return false;
		}
		
		return dao.adicionarSolicitacao(locador, locatario, bic, dtInicio, dtFim, valorPrevisto, Status.SOLICITACAO_ENVIADA_LOCATARIO.getDescricao());
	}

	private boolean checarAluguelAtivo(Locatario locatario) {
		return dao.verificarAluguelAtivo(locatario);
	}

	public boolean checarMultasPendentes(Locatario locatario) {
		CarteiraLocatario carteira = carteiraLocatarioDAO.retornaPorId(locatario);

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

	public void enviaSolicitacaoDeInicioDeAluguel(Aluguel aluguel) {
		dao.enviaSolicitacaoDeInicioDeAluguel(aluguel);
	}

	public void enviarSolicitacaoDeCancelamentoDeAluguelPeloLocatario(Aluguel aluguel) {
		dao.enviarSolicitacaoDeCancelamentoDeAluguelPeloLocatario(aluguel);
		aplicaMultaLocatario(aluguel);
		adicionaSaldoLocador(aluguel);
		bicDAO.liberaBicicleta(aluguel.getBicicleta());
	}

	public void confirmarInicioDeAluguel(Aluguel aluguel) {
		dao.confirmarInicioDeAluguel(aluguel);
	}

	public void enviarSolicitacaoDeCancelamentoDeAluguelPeloLocador(Aluguel aluguel) {
		dao.enviarSolicitacaoDeCancelamentoDeAluguelPeloLocador(aluguel);
		adicionaSaldoLocatario(aluguel);
		adicionaMultaLocador(aluguel);
		bicDAO.liberaBicicleta(aluguel.getBicicleta());
	}
	
	public void aplicaMultaLocatario(Aluguel aluguel) {
		CarteiraLocatario carteira = carteiraLocatarioDAO.retornaPorId(aluguel.getLocatario());
		double multa = carteira.getMultaAcumulada() + (aluguel.getValorPrevisto()/2);
		carteiraLocatarioDAO.adicionarMulta(aluguel, multa);
	}
	
	public void adicionaSaldoLocador(Aluguel aluguel) {
		CarteiraLocador carteiraLocador = carteiraLocadorDAO.retornaPorId(aluguel.getLocador());
		double saldo = carteiraLocador.getSaldo() + (aluguel.getValorPrevisto()/2);
		carteiraLocadorDAO.adicionaSaldo(aluguel, saldo);
	}
	
	public void adicionaSaldoLocatario(Aluguel aluguel) {
		CarteiraLocatario carteira = carteiraLocatarioDAO.retornaPorId(aluguel.getLocatario());
		double saldo = carteira.getMultaAcumulada() - (aluguel.getValorPrevisto()/2);
		carteiraLocatarioDAO.adicionarSaldo(aluguel, saldo);
	}
	
	public void adicionaMultaLocador(Aluguel aluguel) {
		CarteiraLocador carteiraLocador = carteiraLocadorDAO.retornaPorId(aluguel.getLocador());
		double multa = carteiraLocador.getSaldo() - (aluguel.getValorPrevisto()/2);
		carteiraLocadorDAO.adicionaMulta(aluguel, multa);
	}
}
