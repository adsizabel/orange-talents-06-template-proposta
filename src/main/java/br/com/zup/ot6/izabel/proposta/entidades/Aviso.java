package br.com.zup.ot6.izabel.proposta.entidades;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Aviso {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@CreationTimestamp
	private LocalDate validoAte;
	@NotBlank
	private String destino;
	
	public Aviso() {}

	public Aviso(@NotBlank String destino) {
		super();
		this.destino = destino;
	}

	@Override
	public String toString() {
		return "Aviso [id=" + id + ", validoAte=" + validoAte + ", destino=" + destino + "]";
	}
	
	
}
