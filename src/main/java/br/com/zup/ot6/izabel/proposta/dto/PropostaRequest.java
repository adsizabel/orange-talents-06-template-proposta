package br.com.zup.ot6.izabel.proposta.dto;

import java.math.BigDecimal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.zup.ot6.izabel.proposta.elegibilidade.Elegibilidade;
import br.com.zup.ot6.izabel.proposta.entidades.Proposta;
import br.com.zup.ot6.izabel.proposta.excecoes.CpfCnpj;

public class PropostaRequest {
	@NotBlank
	private String nome;
	private String cpfCnpj;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String endereco;
	@NotNull
	@Positive
	private BigDecimal salario;
	@Enumerated(EnumType.STRING)
	private Elegibilidade elegibilidade;
	
	public PropostaRequest(@NotBlank String nome, String cpfCnpj, @NotBlank @Email String email,
			@NotBlank String endereco, @NotNull @Positive BigDecimal salario, Elegibilidade elegibilidade) {
		super();
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.email = email;
		this.endereco = endereco;
		this.salario = salario;
		this.elegibilidade = elegibilidade;
	}

	public Proposta coverterParaEntidade(PropostaRequest propostaRequest) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        this.cpfCnpj = encoder.encode(cpfCnpj);
		return new Proposta(nome, cpfCnpj, email, endereco, salario);
	}

	public String getNome() {
		return nome;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public String getEmail() {
		return email;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public Elegibilidade getElegibilidade() {
		return elegibilidade;
	}

	@Override
	public String toString() {
		return "PropostaRequest [nome=" + nome + ", cpfCnpj=" + cpfCnpj + ", email=" + email + ", endereco="
				+ endereco + ", salario=" + salario + ", elegibilidade=" + elegibilidade + "]";
	}

	
	
}
