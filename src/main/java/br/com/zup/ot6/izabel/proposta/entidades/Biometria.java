package br.com.zup.ot6.izabel.proposta.entidades;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Biometria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String impressaoDigital;
	@NotNull
	private LocalDateTime criadoEm = LocalDateTime.now();
	@ManyToOne
	@JoinColumn(name = "id_cartao")
	private Cartao cartao;
	
	@Deprecated
	public Biometria() {}
	
	public Biometria(String impressaoDigital, Cartao cartao) {
		super();
		this.impressaoDigital = Base64.getEncoder().encodeToString(impressaoDigital.getBytes());
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

}
