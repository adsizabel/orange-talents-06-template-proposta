package br.com.zup.ot6.izabel.proposta.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class AvisoApiExternaResponse {

	private String resultado;

	@JsonCreator
	public AvisoApiExternaResponse(String resultado) {
		super();
		this.resultado = resultado;
	}

	public String getResultado() {
		return resultado;
	}
	
	
}
