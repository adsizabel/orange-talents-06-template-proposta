package br.com.zup.ot6.izabel.proposta.entidades;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Bloqueio {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@CreationTimestamp
	private LocalDate bloqueadoEm;
	@NotBlank
	private String sistemaResponsavel;
	@NotNull
	private Boolean ativo;
	
	public Bloqueio() {}

	public Bloqueio(@NotBlank String sistemaResponsavel, @NotNull Boolean ativo) {
		super();
		this.sistemaResponsavel = sistemaResponsavel;
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "Bloqueio [id=" + id + ", bloqueadoEm=" + bloqueadoEm + ", sistemaResponsavel=" + sistemaResponsavel
				+ ", ativo=" + ativo + "]";
	}
	
	
}
