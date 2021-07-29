package br.com.zup.ot6.izabel.proposta.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zup.ot6.izabel.proposta.entidades.Cartao;
import br.com.zup.ot6.izabel.proposta.entidades.Proposta;

public class CartaoResponse {
	
	@JsonProperty("id")
	private String numeroCartao;
	private LocalDateTime emitidoEm;
	private String titular;
	private BigDecimal limite;
	private VencimentoCartaoResponse vencimento;
	private Long idProposta;

	public CartaoResponse(String numeroCartao, LocalDateTime emitidoEm, String titular,
			BigDecimal limite, VencimentoCartaoResponse vencimento) {
	super();
	this.numeroCartao = numeroCartao;
	this.emitidoEm = emitidoEm;
	this.titular = titular;
	this.limite = limite;
	this.vencimento = vencimento;
	}
	
	public String getNumeroCartao() {
		return numeroCartao;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public VencimentoCartaoResponse getVencimento() {
		return vencimento;
	}

	public Long getIdProposta() {
		return idProposta;
	}

	public Cartao converterParaEntidade(Proposta proposta) {
		return new Cartao(titular, numeroCartao, limite, proposta, vencimento.converterParaEntidade());
	}
	
	
	@Override
	public String toString() {
		return "CartaoResponse [numero=" + numeroCartao + ", emitidoEm=" + emitidoEm + ", titular=" + titular
				+ ", limite=" + limite + ", vencimento=" + vencimento + "]";
	}
	
	
	
}
