package model;

import java.time.LocalDate;

public class Locatario extends Usuario{

	private int id;
	private String login;
	private String senha;
	private LocalDate dataCadastro;
	
	public Locatario() {}

	// Contrutor sem id
	public Locatario(String nome, String cpf, Endereco endereco, String celular, String login, String senha, LocalDate dataCadastro) {
		super(nome, cpf, endereco, celular);
		this.login = login;
		this.senha = senha;
		this.dataCadastro = dataCadastro;
	}

	public Locatario(String nome, String cpf, Endereco endereco, String celular, int id, String login, String senha, LocalDate dataCadastro) {
		super(nome, cpf, endereco, celular);
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.dataCadastro = dataCadastro;
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
	
	@Override
	public String toString() {
		return super.getNome();
	}
}