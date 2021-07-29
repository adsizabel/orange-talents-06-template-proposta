package br.com.zup.ot6.izabel.proposta.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BloqueioRequest {
	
	@JsonProperty("sistemaResponsavel")
	private String sistemaResponsavel;

	public BloqueioRequest() {
	}

	@JsonCreator
	public BloqueioRequest(String sistemaResponsavel) {
		super();
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}
}
