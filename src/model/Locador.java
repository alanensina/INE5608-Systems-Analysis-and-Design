package model;

import java.time.LocalDate;
import java.util.List;

public class Locador extends Usuario{
	
	private int id;
	private String login;
	private String senha;
	private LocalDate dataCadastro;
	private List<Bicicleta> bicicletas;
	
	public Locador() {}

	// Construtor sem id
	public Locador(String nome, String cpf, Endereco endereco, String telefone, String login, String senha,
			LocalDate dataCadastro, List<Bicicleta> bicicletas) {
		super(nome, cpf, endereco, telefone);
		this.login = login;
		this.senha = senha;
		this.dataCadastro = dataCadastro;
		this.bicicletas = bicicletas;
	}
	
	public Locador(int id, String nome, String cpf, Endereco endereco, String telefone, String login, String senha,
			LocalDate dataCadastro, List<Bicicleta> bicicletas) {
		super(nome, cpf, endereco, telefone);
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.dataCadastro = dataCadastro;
		this.bicicletas = bicicletas;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<Bicicleta> getBicicletas() {
		return bicicletas;
	}

	public void setBicicletas(List<Bicicleta> bicicletas) {
		this.bicicletas = bicicletas;
	}

	@Override
	public String toString() {
		return super.getNome();
	}	
}