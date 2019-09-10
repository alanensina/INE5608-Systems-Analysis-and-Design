package model;

public abstract class Usuario {

	private String nome;
	private String cpf;
	private Endereco endereco;
	private String celular;
	
	public Usuario() {}
	
	public Usuario(String nome, String cpf, Endereco endereco, String celular) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.celular = celular;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return celular;
	}

	public void setTelefone(String telefone) {
		this.celular = telefone;
	}
}