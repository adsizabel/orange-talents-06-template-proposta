package br.com.zup.ot6.izabel.proposta.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zup.ot6.izabel.proposta.entidades.Vencimento;

public class VencimentoCartaoResponse {
	@JsonProperty("id")
	private String sequenciaId;
	@JsonProperty("dia")
	private Integer dia;
	@JsonProperty("dataDeCriacao")
	private LocalDateTime dataDeCriacao;
	
	public VencimentoCartaoResponse() {}
	
	public VencimentoCartaoResponse(Vencimento vencimento) {
		super();
		this.sequenciaId = vencimento.getSequenciaId();
		this.dia = vencimento.getDiaVencimento();
		this.dataDeCriacao = vencimento.getDataCriacao();
	}

	public Vencimento converterParaEntidade() {
		return new Vencimento(sequenciaId, dia, dataDeCriacao);
	}
	
	@Override
	public String toString() {
		return "VencimentoCartaoResponse [sequenciaId=" + sequenciaId + ", dia=" + dia + ", dataDeCriacao="
				+ dataDeCriacao + "]";
	}

	
}
