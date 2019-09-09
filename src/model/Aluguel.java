package model;

import java.time.LocalDate;

public class Aluguel {

	private int id;
	private Locador locador;
	private Locatario locatario;
	private Bicicleta bicicleta;
	private Ponto ponto;
	private LocalDate inicioPrevisto;
	private LocalDate fimPrevisto;
	private double valorTotal;
	
	public Aluguel() {}

	public Aluguel(Locador locador, Locatario locatario, Bicicleta bicicleta, Ponto ponto, LocalDate inicioPrevisto,
			LocalDate fimPrevisto, double valorTotal) {
		this.locador = locador;
		this.locatario = locatario;
		this.bicicleta = bicicleta;
		this.ponto = ponto;
		this.inicioPrevisto = inicioPrevisto;
		this.fimPrevisto = fimPrevisto;
		this.valorTotal = valorTotal;
	}

	public Aluguel(int id, Locador locador, Locatario locatario, Bicicleta bicicleta, Ponto ponto,
			LocalDate inicioPrevisto, LocalDate fimPrevisto, double valorTotal) {
		super();
		this.id = id;
		this.locador = locador;
		this.locatario = locatario;
		this.bicicleta = bicicleta;
		this.ponto = ponto;
		this.inicioPrevisto = inicioPrevisto;
		this.fimPrevisto = fimPrevisto;
		this.valorTotal = valorTotal;
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

	public Ponto getPonto() {
		return ponto;
	}

	public void setPonto(Ponto ponto) {
		this.ponto = ponto;
	}

	public LocalDate getInicioPrevisto() {
		return inicioPrevisto;
	}

	public void setInicioPrevisto(LocalDate inicioPrevisto) {
		this.inicioPrevisto = inicioPrevisto;
	}

	public LocalDate getFimPrevisto() {
		return fimPrevisto;
	}

	public void setFimPrevisto(LocalDate fimPrevisto) {
		this.fimPrevisto = fimPrevisto;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public String toString() {
		return this.getLocador().getNome() + " - " + this.getLocatario().getNome();
	}	
}