package br.com.zup.ot6.izabel.proposta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zup.ot6.izabel.proposta.elegibilidade.RetornoElegibilidade;

public class ElegibilidadeResponse {
	
	@JsonProperty("idProposta")
	private Long idProposta;
	@JsonProperty("nome")
	private String nome;	
	@JsonProperty("documento")
	private String documento;
	@JsonProperty("resultadoSolicitacao")
	private RetornoElegibilidade resultadoSolicitacao;
	
	public ElegibilidadeResponse() {}

	public ElegibilidadeResponse(Long idProposta, String nome, String documento,
			RetornoElegibilidade resultadoSolicitacao) {
		super();
		this.idProposta = idProposta;
		this.nome = nome;
		this.documento = documento;
		this.resultadoSolicitacao = resultadoSolicitacao;
	}

	public RetornoElegibilidade getRetornoElegibilidade() {
		return resultadoSolicitacao;
	}
	
	public Long getIdProposta() {
		return idProposta;
	}

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}

	@Override
	public String toString() {
		return "ElegibilidadeResponse [idProposta=" + idProposta + ", nome=" + nome + ", documento=" + documento
				+ ", retornoElegibilidade=" + resultadoSolicitacao + "]";
	}

	
}
