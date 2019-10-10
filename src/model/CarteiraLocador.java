package model;

public class CarteiraLocador {
	
	private int id;
	private Locador locador;
	private double saldo;
	
	public CarteiraLocador() {}

	public CarteiraLocador(int id, Locador locador, double saldo) {
		this.id = id;
		this.locador = locador;
		this.saldo = saldo;
	}
	
	public CarteiraLocador(Locador locador, double saldo) {
		this.locador = locador;
		this.saldo = saldo;
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

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Carteira do " + locador.getNome();
	}
}
