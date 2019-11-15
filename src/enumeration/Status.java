package enumeration;

public enum Status {
	
	SOLICITACAO_ENVIADA_LOCATARIO("Solicitação enviada pelo locatário"),
	CANCELADO_PELO_LOCADOR("Cancelado pelo locador"),
	CANCELADO_PELO_LOCATARIO("Cancelado pelo locatário"),
	AGUARDANDO_INICIO("Aguardando início"),
	INICIADO_PELO_LOCATARIO("Iniciado pelo locatário"),
	ALUGUEL_INICIADO("Aluguel iniciado"),
	DEVOLUCAO_INICIADA("Devolução iniciada"),
	AGUARDANDO_DEVOLUCAO("Aguardando devolução."),
	ALUGUEL_FINALIZADO("Aluguel finalizado");
	
	private String descricao;
	
	Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
