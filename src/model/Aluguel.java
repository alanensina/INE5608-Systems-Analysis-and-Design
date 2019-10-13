package model;

import java.time.LocalDate;

public class Aluguel {

	private int id;
	private Locador locador;
	private Locatario locatario;
	private Bicicleta bicicleta;
	private LocalDate dtInicio;
	private LocalDate dtFimPrevisto;
	private LocalDate dtFimRealizado;
	private double valorPrevisto;
	private double valorMulta;
	private double valorFinal;
	private boolean iniciado;
	private boolean pendente;
	private boolean cancelado;
	
	public Aluguel() {}
	
	public Aluguel(int id, Locador locador, Locatario locatario, Bicicleta bicicleta, LocalDate dtInicio,
			LocalDate dtFimPrevisto, LocalDate dtFimRealizado, double valorPrevisto, double valorMulta,
			double valorFinal, boolean iniciado, boolean pendente, boolean cancelado) {
		this.id = id;
		this.locador = locador;
		this.locatario = locatario;
		this.bicicleta = bicicleta;
		this.dtInicio = dtInicio;
		this.dtFimPrevisto = dtFimPrevisto;
		this.dtFimRealizado = dtFimRealizado;
		this.valorPrevisto = valorPrevisto;
		this.valorMulta = valorMulta;
		this.valorFinal = valorFinal;
		this.iniciado = iniciado;
		this.pendente = pendente;
		this.cancelado = cancelado;
	}
	
	public Aluguel(Locador locador, Locatario locatario, Bicicleta bicicleta, LocalDate dtInicio,
			LocalDate dtFimPrevisto, LocalDate dtFimRealizado, double valorPrevisto, double valorMulta,
			double valorFinal, boolean iniciado, boolean pendente, boolean cancelado) {
		this.locador = locador;
		this.locatario = locatario;
		this.bicicleta = bicicleta;
		this.dtInicio = dtInicio;
		this.dtFimPrevisto = dtFimPrevisto;
		this.dtFimRealizado = dtFimRealizado;
		this.valorPrevisto = valorPrevisto;
		this.valorMulta = valorMulta;
		this.valorFinal = valorFinal;
		this.iniciado = iniciado;
		this.pendente = pendente;
		this.cancelado = cancelado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Locador getLocador() {
		return locador;
	}

	public void setLocador(Locador locador) {
		this.locador = locador;
	}

	public Locatario getLocatario() {
		return locatario;
	}

	public void setLocatario(Locatario locatario) {
		this.locatario = locatario;
	}

	public Bicicleta getBicicleta() {
		return bicicleta;
	}

	public void setBicicleta(Bicicleta bicicleta) {
		this.bicicleta = bicicleta;
	}

	public LocalDate getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(LocalDate dtInicio) {
		this.dtInicio = dtInicio;
	}

	public LocalDate getDtFimPrevisto() {
		return dtFimPrevisto;
	}

	public void setDtFimPrevisto(LocalDate dtFimPrevisto) {
		this.dtFimPrevisto = dtFimPrevisto;
	}

	public LocalDate getDtFimRealizado() {
		return dtFimRealizado;
	}

	public void setDtFimRealizado(LocalDate dtFimRealizado) {
		this.dtFimRealizado = dtFimRealizado;
	}

	public double getValorPrevisto() {
		return valorPrevisto;
	}

	public void setValorPrevisto(double valorPrevisto) {
		this.valorPrevisto = valorPrevisto;
	}

	public double getValorMulta() {
		return valorMulta;
	}

	public void setValorMulta(double valorMulta) {
		this.valorMulta = valorMulta;
	}

	public double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(double valorFinal) {
		this.valorFinal = valorFinal;
	}
	
	public boolean isPendente() {
		return pendente;
	}

	public void setPendente(boolean pendente) {
		this.pendente = pendente;
	}

	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}

	public boolean isIniciado() {
		return iniciado;
	}

	public void setIniciado(boolean iniciado) {
		this.iniciado = iniciado;
	}

	@Override
	public String toString() {
		return this.locatario.getNome() + " - " + this.bicicleta.getModelo() + " - R$ " + this.valorPrevisto;
	}
}