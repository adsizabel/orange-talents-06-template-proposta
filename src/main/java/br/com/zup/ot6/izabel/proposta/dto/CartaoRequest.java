package br.com.zup.ot6.izabel.proposta.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zup.ot6.izabel.proposta.entidades.Proposta;
import br.com.zup.ot6.izabel.proposta.excecoes.CpfCnpj;

public class CartaoRequest {
	@NotNull
	@JsonProperty("idProposta")
	private String idProposta;
	@NotBlank
	@JsonProperty("nome")
	private String nome;
	@NotBlank
	@CpfCnpj
	@JsonProperty("documento")
	private String documento;
	
	public CartaoRequest(@NotNull String idProposta, @NotBlank String nome, @NotBlank String documento) {
		super();
		this.idProposta = idProposta;
		this.nome = nome;
		this.documento = documento;
	}

	public CartaoRequest converterDaEntidade(Proposta proposta) {
		return new CartaoRequest(Long.toString(proposta.getId()), proposta.getNome(), proposta.getCpfCnpj());
	}
	
	@Override
	public String toString() {
		return "ElegibilidadeRequest [idProposta=" + idProposta + ", nome=" + nome + ", documento=" + documento + "]";
	}
	
	
}
