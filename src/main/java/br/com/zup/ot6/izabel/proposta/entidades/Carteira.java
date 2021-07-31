package br.com.zup.ot6.izabel.proposta.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class Carteira {
	@Id
	@GeneratedValue
	private Long id;
	@Email
	@NotBlank
	private String email;
	@OneToOne(cascade = CascadeType.ALL)
	private Cartao cartao;
	@NotBlank
	private String carteira;
	
	@Deprecated
	public Carteira() {}


	public Carteira(@Email @NotBlank String email, Cartao cartao, @NotBlank String carteira) {
		super();
		this.email = email;
		this.cartao = cartao;
		this.carteira = carteira;
	}

	public Long getId() {
		return id;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public String getEmail() {
		return email;
	}
	
	

}
