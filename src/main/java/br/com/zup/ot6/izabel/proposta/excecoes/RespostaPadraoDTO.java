package br.com.zup.ot6.izabel.proposta.excecoes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RespostaPadraoDTO {
	@JsonProperty("campo")
	private String campo;
	@JsonProperty("mensagem")
	private String mensagem;
	
	public RespostaPadraoDTO(String campo, String mensagem) {
		super();
		this.campo = campo;
		this.mensagem = mensagem;
	}
	
	public String getCampo() {
		return campo;
	}

	public String getMensagem() {
		return mensagem;
	}
	
	
	
}
