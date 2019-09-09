package model;

import java.time.LocalDate;

public class Avaliacao {
	
	private int id;
	private Aluguel aluguel;
	private double nota;
	private LocalDate dataAvaliacao;
	
	public Avaliacao() {}

	public Avaliacao(int id, Aluguel aluguel, double nota, LocalDate dataAvaliacao) {
		this.id = id;
		this.aluguel = aluguel;
		this.nota = nota;
		this.dataAvaliacao = dataAvaliacao;
	}
	
	// Construtor sem id
	public Avaliacao( Aluguel aluguel, double nota, LocalDate dataAvaliacao) {
		this.aluguel = aluguel;
		this.nota = nota;
		this.dataAvaliacao = dataAvaliacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public LocalDate getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(LocalDate dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}
}