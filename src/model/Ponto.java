package model;

import java.util.List;

public class Ponto {

	private int id;
	private String nomeDoPonto;
	private Locador locador;
	private List<Bicicleta> bicicletas;
	private Endereco endereco;
	
	public Ponto() {}

	// Construtor sem id
	public Ponto(String nomeDoPonto, Locador locador, List<Bicicleta> bicicletas, Endereco endereco) {
		this.nomeDoPonto = nomeDoPonto;
		this.locador = locador;
		this.bicicletas = bicicletas;
		this.endereco = endereco;
	}

	public Ponto(int id, String nomeDoPonto, Locador locador, List<Bicicleta> bicicletas, Endereco endereco) {
		this.id = id;
		this.nomeDoPonto = nomeDoPonto;
		this.locador = locador;
		this.bicicletas = bicicletas;
		this.endereco = endereco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNomeDoPonto() {
		return nomeDoPonto;
	}

	public void setNomeDoPonto(String nomeDoPonto) {
		this.nomeDoPonto = nomeDoPonto;
	}

	public Locador getLocador() {
		return locador;
	}

	public void setLocador(Locador locador) {
		this.locador = locador;
	}

	public List<Bicicleta> getBicicletas() {
		return bicicletas;
	}

	public void setBicicletas(List<Bicicleta> bicicletas) {
		this.bicicletas = bicicletas;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return this.getNomeDoPonto();
	}
}