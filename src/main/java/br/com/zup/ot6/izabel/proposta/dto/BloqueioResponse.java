package br.com.zup.ot6.izabel.proposta.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BloqueioResponse {
	
	private String resultado;

	public BloqueioResponse() {
	}

	public BloqueioResponse(String sistemaResponsavel) {
		super();
		this.resultado = sistemaResponsavel;
	}

	public String getSistemaResponsavel() {
		return resultado;
	}
}
