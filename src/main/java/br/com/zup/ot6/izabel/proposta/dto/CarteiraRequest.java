package br.com.zup.ot6.izabel.proposta.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CarteiraRequest {

	@Email
	@NotBlank
	private String email;
	
	@Deprecated
	public CarteiraRequest() {}
	
	@JsonCreator
	public CarteiraRequest(@Email @NotBlank String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

}
