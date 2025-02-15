package br.com.zup.ot6.izabel.proposta.entidades;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.zup.ot6.izabel.proposta.elegibilidade.Elegibilidade;
import br.com.zup.ot6.izabel.proposta.security.CriptografarDados;

@Entity
public class Proposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
	@OneToOne(cascade = CascadeType.ALL)
	private Cartao cartao;
	
	@Deprecated
	public Proposta() {}

	public Proposta(@NotBlank String nome, String cpfCnpj, @NotBlank @Email String email, @NotBlank String endereco,
			@NotNull @Positive BigDecimal salario) {
		super();
		this.nome = nome;
		this.cpfCnpj = CriptografarDados.criptografar(cpfCnpj);
		this.email = email;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}

	public Elegibilidade getElegibilidade() {
		return elegibilidade;
	}
	
	public String getNome() {
		return nome;
	}

	public String getCpfCnpj() {
		return CriptografarDados.descriptografar(cpfCnpj);
	}

	public String getEndereco() {
		return endereco;
	}

	public Cartao getCartao() {
		return cartao;
	}
	
	public BigDecimal getSalario() {
		return salario;
	}

	public String getEmail() {
		return email;
	}

	public void associaCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	/** Este método é utilizado para atualização do status de elegibilidade do cliente
	 * após a consulta de seus dados de solicitação de prosposta na API externa que valida a elegibilidade do mesmo.
	 * 
	 * @param elegibilidade
	 */
	public void setElegibilidade(Elegibilidade elegibilidade) {
		this.elegibilidade = elegibilidade;
	}
	
	public String getOfuscadorDados(String dado) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
		return encoder.encode(dado);
	}
	
	@Override
	public String toString() {
		return "Proposta [id=" + id + ", nome=" + nome + ", cpfCnpj=" + cpfCnpj + ", email=" + email + ", endereco="
				+ endereco + ", salario=" + salario + ", elegibilidade=" + elegibilidade + "]";
	}

	
}
