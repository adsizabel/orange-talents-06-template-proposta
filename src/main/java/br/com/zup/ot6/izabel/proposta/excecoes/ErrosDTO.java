package br.com.zup.ot6.izabel.proposta.excecoes;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrosDTO {

	@JsonProperty("timestamp")
	private Long timestamp;
	@JsonProperty("quantidadeErros")
	private Integer quantidadeErros;	
	@JsonProperty("errosCampos")
	private List<RespostaPadraoDTO> errosCampos = new ArrayList<>();
	@JsonProperty("errosGlobais")
	private List<String> errosGlobais = new ArrayList<>();
	
	public ErrosDTO() {}
	
	public ErrosDTO(List<RespostaPadraoDTO> erros, List<String> errosGlobais) {
		super();
		this.quantidadeErros = erros.size();
		this.errosCampos = erros;
		this.errosGlobais = errosGlobais;
		this.timestamp = System.currentTimeMillis();
	}
	
	public void addErrosGlobais(String messagem) {
		errosGlobais.add(messagem);
	}
	
	public void addErrosCampos(String campo, String mensagem) {
		RespostaPadraoDTO camposErros = new RespostaPadraoDTO(campo, mensagem);
		errosCampos.add(camposErros);
	}
	
	public Long getTimestamp() {
		return timestamp;
	}
	public Integer getQuantidadeErros() {
		return quantidadeErros;
	}
	public List<RespostaPadraoDTO> errosCampos() {
		return errosCampos;
	}
	public List<String> getErrosGlobais() {
		return errosGlobais;
	}
	
	
	
	
}
