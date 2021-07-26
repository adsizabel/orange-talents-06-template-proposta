package br.com.zup.ot6.izabel.proposta.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.zup.ot6.izabel.proposta.entidades.Biometria;
import br.com.zup.ot6.izabel.proposta.entidades.Cartao;

public class BiometriaRequest {
	
	@NotBlank
	@JsonProperty("impressaoDigital")
	private String impressaoDigital;
	
	@JsonCreator
	public BiometriaRequest(String impressaoDigital) {
		this.impressaoDigital = impressaoDigital;
	}
	
	public Biometria converterParaEntidade(Cartao cartao) {
		return new Biometria(impressaoDigital, cartao);
	}

	public String getImpressaoDigital() {
		return impressaoDigital;
	}
	
	

}
