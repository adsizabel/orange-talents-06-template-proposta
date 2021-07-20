package br.com.zup.ot6.izabel.proposta.controladores;


import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zup.ot6.izabel.proposta.dto.PropostaRequestDTO;
import br.com.zup.ot6.izabel.proposta.elegibilidade.AvaliadorElegibilidade;
import br.com.zup.ot6.izabel.proposta.elegibilidade.RetornoElegibilidade;
import br.com.zup.ot6.izabel.proposta.entidades.Proposta;
import br.com.zup.ot6.izabel.proposta.excecoes.ProspostaExistenteValidador;

@RestController
public class PropostaControlador {
	
	private static final Logger logger = LoggerFactory.getLogger(PropostaControlador.class);

	@PersistenceContext
	private EntityManager entityManager;

	public ProspostaExistenteValidador propostaValidador;
	
	public AvaliadorElegibilidade avaliadorElegibilidade;
	
	@Autowired
	public PropostaControlador(ProspostaExistenteValidador propostaValidador,
			AvaliadorElegibilidade avaliadorElegibilidade) {
		super();
		this.propostaValidador = propostaValidador;
		this.avaliadorElegibilidade = avaliadorElegibilidade;
	}

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(propostaValidador);
	}
	
	@PostMapping(value = "/proposta")
	@Transactional
	public ResponseEntity<?> cadastrarProposta(@RequestBody @Valid PropostaRequestDTO propostaRequestDTO) {

		Proposta proposta = propostaRequestDTO.coverterParaEntidade(propostaRequestDTO);
		entityManager.persist(proposta);
		
		logger.info("Proposta {} salva.", proposta.toString());
		
		RetornoElegibilidade status = avaliadorElegibilidade.avaliaElegibilidade(proposta);
		
		logger.info("Status de elegibilidade {}", status);

		proposta.setElegibilidade(status.getElegibilidade());
		
		entityManager.merge(proposta);

		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(proposta.getId())
                .toUri();
		
		return ResponseEntity.created(location).build();
	}


}
