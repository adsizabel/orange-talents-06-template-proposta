package br.com.zup.ot6.izabel.proposta.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.ot6.izabel.proposta.entidades.Proposta;
import br.com.zup.ot6.izabel.proposta.excecoes.CpfCnpj;

public class PropostaRequestDTO {
	@NotBlank
	private String nome;
	@CpfCnpj
	private String cpfCnpj;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String endereco;
	@NotNull
	@Positive
	private BigDecimal salario;
	
	public PropostaRequestDTO(@NotBlank String nome, String cpfCnpj, @NotBlank @Email String email,
			@NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
		super();
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.email = email;
		this.endereco = endereco;
		this.salario = salario;
	}
	
	public Proposta coverterParaEntidade(PropostaRequestDTO propostaRequestDTO) {
		return new Proposta(nome, cpfCnpj, email, endereco, salario);
	}

	
}
