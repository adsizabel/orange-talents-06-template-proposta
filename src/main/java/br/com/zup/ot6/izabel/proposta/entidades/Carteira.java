package br.com.zup.ot6.izabel.proposta.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.zup.ot6.izabel.proposta.cartao.CarteiraDigital;

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
	@Enumerated(EnumType.STRING)
	private CarteiraDigital carteira;
	
	@Deprecated
	public Carteira() {}

	public Carteira(@Email @NotBlank String email, Cartao cartao, @NotBlank CarteiraDigital carteira) {
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

	public CarteiraDigital getCarteira() {
		return carteira;
	}

}
