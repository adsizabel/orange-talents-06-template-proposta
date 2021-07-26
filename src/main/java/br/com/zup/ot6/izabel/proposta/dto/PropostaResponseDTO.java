package br.com.zup.ot6.izabel.proposta.dto;

import java.math.BigDecimal;

import br.com.zup.ot6.izabel.proposta.entidades.Proposta;

public class PropostaResponseDTO {
	private String nome;
	private String cpfCnpj;
	private String email;
	private String endereco;
	private BigDecimal salario;
	private String elegibilidade;
	
	public PropostaResponseDTO(Proposta proposta) {
		this.nome = proposta.getNome();
		this.cpfCnpj = proposta.getCpfCnpj();
		this.email = proposta.getEmail();
		this.endereco = proposta.getEndereco();
		this.salario = proposta.getSalario();
		this.elegibilidade = proposta.getElegibilidade().toString();
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

	public String getElegibilidade() {
		return elegibilidade;
	}

}
