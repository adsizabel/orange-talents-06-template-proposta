package br.com.zup.ot6.izabel.proposta.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Aviso {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private LocalDate terminoDaViagem;
	@NotNull
	private LocalDateTime criadoEm = LocalDateTime.now();
	@NotBlank
	private String destino;
	@NotBlank
	private String ip;
	@NotBlank
	private String sistemaResponsavel;
	@ManyToOne(cascade = CascadeType.ALL)
	private Cartao cartao;
	
	@Deprecated
	public Aviso() {}

	public Aviso(@NotNull LocalDate terminoDaViagem, @NotBlank String destino, @NotBlank String ip,
			@NotBlank String sistemaResponsavel, Cartao cartao) {
		super();
		this.terminoDaViagem = terminoDaViagem;
		this.destino = destino;
		this.ip = ip;
		this.sistemaResponsavel = sistemaResponsavel;
		this.cartao = cartao;
	}

}
