package br.com.zup.ot6.izabel.proposta.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.ot6.izabel.proposta.cartao.StatusBloqueio;


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
	@Enumerated(EnumType.STRING)
	private StatusBloqueio bloqueio;
	
	
	@Deprecated
	public Cartao() {}

	public Cartao(@NotBlank String titular, @NotBlank String numero,
			@NotNull BigDecimal limite, Proposta proposta, Vencimento vencimento) {
		super();
		this.emitidoEm = LocalDateTime.now();
		this.titular = titular;
		this.numero = numero;
		this.limite = limite;
		this.proposta = proposta;
		this.vencimento = vencimento;
		this.bloqueio = StatusBloqueio.DESBLOQUEADO;	
	}

	public Long getId() {
		return id;
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

	public StatusBloqueio getBloqueio() {
		return bloqueio;
	}

	public void setVencimento(Vencimento vencimento) {
		this.vencimento = vencimento;
	}

	public void associaProposta(Proposta proposta) {
		this.proposta = proposta;
	}
	
	public Bloqueio bloquearCartao(String ip, String userAgent) {
		this.bloqueio = StatusBloqueio.BLOQUEADO;
		return new Bloqueio(ip, userAgent, this);
	}
	
}
