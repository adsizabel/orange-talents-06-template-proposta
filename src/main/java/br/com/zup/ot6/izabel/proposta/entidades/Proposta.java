package br.com.zup.ot6.izabel.proposta.entidades;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.ot6.izabel.proposta.excecoes.CpfCnpj;

@Entity
public class Proposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
	
	@Deprecated
	public Proposta() {}

	public Proposta(@NotBlank String nome, String cpfCnpj, @NotBlank @Email String email, @NotBlank String endereco,
			@NotNull @Positive BigDecimal salario) {
		super();
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.email = email;
		this.endereco = endereco;
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Proposta [id=" + id + ", nome=" + nome + ", cpfCnpj=" + cpfCnpj + ", email=" + email + ", endereco="
				+ endereco + ", salario=" + salario + "]";
	}

	public Long getId() {
		return id;
	}
	
	

}
