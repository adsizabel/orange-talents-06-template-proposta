package br.com.zup.ot6.izabel.proposta.dto;

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
