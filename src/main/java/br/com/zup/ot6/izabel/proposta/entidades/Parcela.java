package br.com.zup.ot6.izabel.proposta.entidades;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class Parcela {
	@Id
	@GeneratedValue
	private Long id ;
	@NotNull
	private Integer quantidadeParcela;
	@NotNull
	private BigDecimal valorParcela;

	public Parcela() {}

	public Parcela(@NotNull Integer quantidadeParcela, @NotNull BigDecimal valorParcela) {
		super();
		this.quantidadeParcela = quantidadeParcela;
		this.valorParcela = valorParcela;
	}


	@Override
	public String toString() {
		return "Parcela [id=" + id + ", quantidadeParcela=" + quantidadeParcela + ", valorParcela=" + valorParcela
				+ "]";
	}
	
	
}
