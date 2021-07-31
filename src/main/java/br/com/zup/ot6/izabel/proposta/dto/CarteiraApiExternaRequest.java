package br.com.zup.ot6.izabel.proposta.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CarteiraApiExternaRequest {

	@Email
	@NotBlank
	private String email;
	@NotBlank
	private String carteira;
	
	public CarteiraApiExternaRequest(@Email @NotBlank String email, @NotBlank String carteira) {
		super();
		this.email = email;
		this.carteira = carteira;
	}

	public String getEmail() {
		return email;
	}

	public String getCarteira() {
		return carteira;
	}
	
	
}
