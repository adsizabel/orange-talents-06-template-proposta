package br.com.zup.ot6.izabel.proposta.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.zup.ot6.izabel.proposta.entidades.Cartao;
import br.com.zup.ot6.izabel.proposta.entidades.Carteira;

public class CarteiraRequest {

	@Email
	@NotBlank
	private String email;
	@NotBlank
	private String carteira;
	
	public CarteiraRequest(@Email @NotBlank String email, @NotBlank String carteira) {
		super();
		this.email = email;
		this.carteira = carteira;
	}

	public Carteira converterParaEntidade(Cartao cartao) {
		return new Carteira(email, cartao, carteira);
	}
	
	public String getEmail() {
		return email;
	}

	public String getCarteira() {
		return carteira;
	}
	
	
}
