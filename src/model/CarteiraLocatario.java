package model;

public class CarteiraLocatario {

	private int id;
	private Locatario locatario;
	private double multaAcumulada;
	
	public CarteiraLocatario() {}

	public CarteiraLocatario(int id, Locatario locatario, double multaAcumulada) {
		this.id = id;
		this.locatario = locatario;
		this.multaAcumulada = multaAcumulada;
	}
	
	public CarteiraLocatario(Locatario locatario, double multaAcumulada) {
		this.locatario = locatario;
		this.multaAcumulada = multaAcumulada;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Locatario getLocatario() {
		return locatario;
	}

	public void setLocatario(Locatario locatario) {
		this.locatario = locatario;
	}

	public double getMultaAcumulada() {
		return multaAcumulada;
	}

	public void setMultaAcumulada(double multaAcumulada) {
		this.multaAcumulada = multaAcumulada;
	}
}