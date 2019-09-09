package model;

public class Bicicleta {
	
	private int id;
	private String modelo;
	private String ano;
	private double valorDeAluguel;
	private String acessorios;
	
	public Bicicleta() {}

	// Construtor sem id
	public Bicicleta(String modelo, String ano, double valorDeAluguel, String acessorios) {
		this.modelo = modelo;
		this.ano = ano;
		this.valorDeAluguel = valorDeAluguel;
		this.acessorios = acessorios;
	}
	
	public Bicicleta(int id, String modelo, String ano, double valorDeAluguel, String acessorios) {
		this.id = id;
		this.modelo = modelo;
		this.ano = ano;
		this.valorDeAluguel = valorDeAluguel;
		this.acessorios = acessorios;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public double getValorDeAluguel() {
		return valorDeAluguel;
	}

	public void setValorDeAluguel(double valorDeAluguel) {
		this.valorDeAluguel = valorDeAluguel;
	}

	public String getAcessorios() {
		return acessorios;
	}

	public void setAcessorios(String acessorios) {
		this.acessorios = acessorios;
	}

	@Override
	public String toString() {
		return this.getModelo() + " " + this.getAno();
	}
}