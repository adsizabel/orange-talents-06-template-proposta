package br.com.zup.ot6.izabel.proposta.dto;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvisoApiExternaRequest {
	@NotBlank
    @JsonProperty("destino")
    private final String destino;

    @NotNull
    @Future
    @JsonProperty("validoAte")
    private final LocalDate validoAte;

	public AvisoApiExternaRequest(@NotBlank String destino, @NotNull @Future LocalDate validoAte) {
		super();
		this.destino = destino;
		this.validoAte = validoAte;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}
	
	
}
