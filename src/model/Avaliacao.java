package model;

public class Avaliacao {
	
	private int id;
	private Aluguel aluguel;
	private double notaLocadorParaLocatario;
	private double notaLocatarioParaLocador;

	public Avaliacao() {}

	// Construtor sem id
	public Avaliacao(Aluguel aluguel, double notaLocadorParaLocatario, double notaLocatarioParaLocador) {
		this.aluguel = aluguel;
		this.notaLocadorParaLocatario = notaLocadorParaLocatario;
		this.notaLocatarioParaLocador = notaLocatarioParaLocador;
	}

	public Avaliacao(int id, Aluguel aluguel, double notaLocadorParaLocatario, double notaLocatarioParaLocador) {
		this.id = id;
		this.aluguel = aluguel;
		this.notaLocadorParaLocatario = notaLocadorParaLocatario;
		this.notaLocatarioParaLocador = notaLocatarioParaLocador;
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

	public double getNotaLocadorParaLocatario() {
		return notaLocadorParaLocatario;
	}

	public void setNotaLocadorParaLocatario(double notaLocadorParaLocatario) {
		this.notaLocadorParaLocatario = notaLocadorParaLocatario;
	}

	public double getNotaLocatarioParaLocador() {
		return notaLocatarioParaLocador;
	}

	public void setNotaLocatarioParaLocador(double notaLocatarioParaLocador) {
		this.notaLocatarioParaLocador = notaLocatarioParaLocador;
	}
}