package br.com.zup.ot6.izabel.proposta.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.ot6.izabel.proposta.entidades.Proposta;
import br.com.zup.ot6.izabel.proposta.excecoes.CpfCnpj;

public class ElegibilidadeRequest {
	@NotNull
	private Long idProposta;
	@NotBlank
	private String nome;
	@NotBlank
	@CpfCnpj
	private String documento;
	
	public ElegibilidadeRequest(Proposta proposta) {
		this.idProposta = proposta.getId();
		this.nome = proposta.getNome();
		this.documento = proposta.getCpfCnpj();
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
		return "ElegibilidadeRequest [idProposta=" + idProposta + ", nome=" + nome + ", documento=" + documento + "]";
	}
	
	
}
