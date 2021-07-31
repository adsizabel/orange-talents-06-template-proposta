package br.com.zup.ot6.izabel.proposta.excecoes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zup.ot6.izabel.proposta.dto.PropostaRequest;
import br.com.zup.ot6.izabel.proposta.entidades.Proposta;
import br.com.zup.ot6.izabel.proposta.repositorio.PropostaRepositorio;

@Component
public class ProspostaExistenteValidador implements Validator{

	private PropostaRepositorio propostaRepositorio;
	
	@Autowired
	public ProspostaExistenteValidador(PropostaRepositorio propostaRepositorio) {
		this.propostaRepositorio = propostaRepositorio;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return PropostaRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		PropostaRequest request = (PropostaRequest) target;
		
		List<Proposta> propostaPesquisada = propostaRepositorio.findByCpfCnpj(request.getCpfCnpj());
		
		if(!propostaPesquisada.isEmpty()) {
			errors.reject(null, "Proposta já existe.");
		}
	}

	
}
