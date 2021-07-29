package br.com.zup.ot6.izabel.proposta.entidades;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Bloqueio {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private LocalDate bloqueadoEm = LocalDate.now();
	@NotBlank
	private String sistemaResponsavel;
	@NotBlank
	private String ip;
	@ManyToOne(cascade = CascadeType.ALL)
	private Cartao cartao;
	
	@Deprecated
	public Bloqueio() {}

	public Bloqueio(@NotBlank String sistemaResponsavel, @NotBlank String ip, Cartao cartao) {
		super();
		this.sistemaResponsavel = sistemaResponsavel;
		this.ip = ip;
		this.cartao = cartao;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

	public String getIp() {
		return ip;
	}

	public Cartao getCartao() {
		return cartao;
	}
	
	
}
