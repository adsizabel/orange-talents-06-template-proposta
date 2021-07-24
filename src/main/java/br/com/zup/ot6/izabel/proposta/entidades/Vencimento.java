package br.com.zup.ot6.izabel.proposta.entidades;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
public class Vencimento {
	@NotBlank
	private String sequenciaId;
	@NotNull
	private Integer diaVencimento;
	@NotNull
	private LocalDateTime dataCriacao;

	public Vencimento() {}

	public Vencimento(@NotBlank String sequenciaId, @NotNull Integer diaVencimento,
			@NotNull LocalDateTime dataCriacao) {
		super();
		this.sequenciaId = sequenciaId;
		this.diaVencimento = diaVencimento;
		this.dataCriacao = dataCriacao;
	}

	@Override
	public String toString() {
		return "Vencimento [ diaVencimento=" + diaVencimento + ", dataCriacao=" + dataCriacao + "]";
	}

	public Integer getDiaVencimento() {
		return diaVencimento;
	}

	public String getSequenciaId() {
		return sequenciaId;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	
	
}
