package br.com.zup.ot6.izabel.proposta.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private LocalDateTime emitidoEm;
	@NotBlank
	private String titular;
	@NotBlank
	private String numero;
	@NotNull
	private BigDecimal limite;
	@OneToOne(mappedBy = "cartao")
	private Proposta proposta;
	@Embedded
	private Vencimento vencimento;
	
	@Deprecated
	public Cartao() {}

	public Cartao(@NotNull LocalDateTime emitidoEm, @NotBlank String titular, @NotBlank String numero,
			@NotNull BigDecimal limite, Proposta proposta, Vencimento vencimento) {
		super();
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.numero = numero;
		this.limite = limite;
		this.proposta = proposta;
		this.vencimento = vencimento;
	}

	@Override
	public String toString() {
		return "Cartao [id=" + id + ", emitidoEm=" + emitidoEm + ", titular=" + titular + ", numero=" + numero
				+ ", limite=" + limite + ", proposta=" + proposta + ", vencimento=" + vencimento + "]";
	}

	public String getNumero() {
		return numero;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public Proposta getProposta() {
		return proposta;
	}

	public Vencimento getVencimento() {
		return vencimento;
	}

	public void setVencimento(Vencimento vencimento) {
		this.vencimento = vencimento;
	}

	public void associaProposta(Proposta proposta) {
		this.proposta = proposta;
	}
	
}
