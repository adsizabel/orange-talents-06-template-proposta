package br.com.zup.ot6.izabel.proposta.dto;

public class CarteiraApiExternaResponse {

	private String resultado;
	private String id;
	
	public CarteiraApiExternaResponse(String resultado, String id) {
		super();
		this.resultado = resultado;
		this.id = id;
	}

	public String getResultado() {
		return resultado;
	}

	public String getId() {
		return id;
	}
	
	
}
