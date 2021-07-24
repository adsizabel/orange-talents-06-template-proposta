package br.com.zup.ot6.izabel.proposta.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

public class Renegociacao {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private Integer quantidadeRenegociacao;
	@NotNull
	private BigDecimal valorRenegociacao;
	@NotNull
	@CreationTimestamp
	private LocalDate dataDeCriacao;

	public Renegociacao() {}

	public Renegociacao(@NotNull Integer quantidadeRenegociacao, @NotNull BigDecimal valorRenegociacao) {
		super();
		this.quantidadeRenegociacao = quantidadeRenegociacao;
		this.valorRenegociacao = valorRenegociacao;
	}


	@Override
	public String toString() {
		return "Renegociacao [id=" + id + ", quantidadeRenegociacao=" + quantidadeRenegociacao + ", valorRenegociacao="
				+ valorRenegociacao + ", dataDeCriacao=" + dataDeCriacao + "]";
	}
	
	
}
