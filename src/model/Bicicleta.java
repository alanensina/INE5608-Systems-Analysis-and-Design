package model;

public class Bicicleta {
	
	private int id;
	private Locador locador;
	private String modelo;
	private String ano;
	private double valorDeAluguel;
	private String acessorios;
	private boolean disponivel;
	
	public Bicicleta() {}

	// Construtor sem id
	public Bicicleta(Locador locador, String modelo, String ano, double valorDeAluguel, String acessorios, boolean disponivel) {
		this.locador = locador;
		this.modelo = modelo;
		this.ano = ano;
		this.valorDeAluguel = valorDeAluguel;
		this.acessorios = acessorios;
		this.disponivel = disponivel;
	}
	
	public Bicicleta(int id, Locador locador, String modelo, String ano, double valorDeAluguel, String acessorios, boolean disponivel) {
		this.id = id;
		this.locador = locador;
		this.modelo = modelo;
		this.ano = ano;
		this.valorDeAluguel = valorDeAluguel;
		this.acessorios = acessorios;
		this.disponivel = disponivel;
	}

	public int getId() {
		return id;
	}

	public Locador getLocador() {
		return locador;
	}

	public void setLocador(Locador locador) {
		this.locador = locador;
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

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	@Override
	public String toString() {
		return this.getModelo() + " " + this.getAno();
	}
}