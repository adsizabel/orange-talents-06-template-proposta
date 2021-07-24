package br.com.zup.ot6.izabel.proposta.entidades;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Carteira {
	@Id
	@GeneratedValue
	private Long id;
	@Email
	@NotBlank
	private String email;
	@NotNull
	@CreationTimestamp
	private LocalDate associadaEm;
	@NotBlank
	private String emissor;
	
	public Carteira() {}

	public Carteira(@Email @NotBlank String email, @NotBlank String emissor) {
		super();
		this.email = email;
		this.emissor = emissor;
	}

	@Override
	public String toString() {
		return "Carteira [id=" + id + ", email=" + email + ", associadaEm=" + associadaEm + ", emissor=" + emissor
				+ "]";
	}
	
	

}
