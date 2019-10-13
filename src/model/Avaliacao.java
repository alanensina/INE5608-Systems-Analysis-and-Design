package model;

public class Avaliacao {
	
	private int id;
	private Aluguel aluguel;
	private double notaDoLocadorParaLocatario;
	private double notaDoLocatarioParaLocador;

	public Avaliacao() {}

	public Avaliacao(int id, Aluguel aluguel, double notaDoLocadorParaLocatario, double notaDoLocatarioParaLocador) {
		this.id = id;
		this.aluguel = aluguel;
		this.notaDoLocadorParaLocatario = notaDoLocadorParaLocatario;
		this.notaDoLocatarioParaLocador = notaDoLocatarioParaLocador;
	}
	
	public Avaliacao(Aluguel aluguel, double notaDoLocadorParaLocatario, double notaDoLocatarioParaLocador) {
		this.aluguel = aluguel;
		this.notaDoLocadorParaLocatario = notaDoLocadorParaLocatario;
		this.notaDoLocatarioParaLocador = notaDoLocatarioParaLocador;
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

	public double getNotaDoLocadorParaLocatario() {
		return notaDoLocadorParaLocatario;
	}

	public void setNotaDoLocadorParaLocatario(double notaDoLocadorParaLocatario) {
		this.notaDoLocadorParaLocatario = notaDoLocadorParaLocatario;
	}

	public double getNotaDoLocatarioParaLocador() {
		return notaDoLocatarioParaLocador;
	}

	public void setNotaDoLocatarioParaLocador(double notaDoLocatarioParaLocador) {
		this.notaDoLocatarioParaLocador = notaDoLocatarioParaLocador;
	}
}