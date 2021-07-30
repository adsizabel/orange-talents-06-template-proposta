package br.com.zup.ot6.izabel.proposta.dto;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zup.ot6.izabel.proposta.entidades.Aviso;
import br.com.zup.ot6.izabel.proposta.entidades.Cartao;

public class AvisoRequest {
	
	@Future
	@NotNull
	private LocalDate terminoDaViagem;
	@NotBlank
	private String destino;
	
	@JsonCreator
	public AvisoRequest(@Future @NotNull LocalDate terminoDaViagem, @NotBlank String destino) {
		super();
		this.terminoDaViagem = terminoDaViagem;
		this.destino = destino;
	}

	public Aviso toModel(String ip, String sistemaResponsavel, Cartao cartao) {
		return new Aviso(terminoDaViagem, destino, ip, sistemaResponsavel, cartao);
	}

	public LocalDate getTerminoDaViagem() {
		return terminoDaViagem;
	}

	public String getDestino() {
		return destino;
	}
	

}
