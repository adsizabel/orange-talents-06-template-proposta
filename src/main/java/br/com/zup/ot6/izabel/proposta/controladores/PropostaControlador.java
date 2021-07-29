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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zup.ot6.izabel.proposta.cartao.ValidadorCartao;
import br.com.zup.ot6.izabel.proposta.dto.PropostaRequestDTO;
import br.com.zup.ot6.izabel.proposta.dto.PropostaResponseDTO;
import br.com.zup.ot6.izabel.proposta.elegibilidade.RetornoElegibilidade;
import br.com.zup.ot6.izabel.proposta.entidades.Proposta;
import br.com.zup.ot6.izabel.proposta.excecoes.ProspostaExistenteValidador;

@RestController
public class PropostaControlador {
	
	private static final Logger logger = LoggerFactory.getLogger(PropostaControlador.class);

	@PersistenceContext
	private EntityManager entityManager;

	public ProspostaExistenteValidador propostaValidador;
	
	public ValidadorCartao validadorCartao;
	
	@Autowired
	public PropostaControlador(ProspostaExistenteValidador propostaValidador,
			ValidadorCartao validadorCartao) {
		super();
		this.propostaValidador = propostaValidador;
		this.validadorCartao = validadorCartao;
	}

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(propostaValidador);
	}
	
	@GetMapping(value = "/proposta/{id}")
	public ResponseEntity<PropostaResponseDTO> acompanharProposta(@PathVariable("id") Long id) {
		Proposta proposta = entityManager.find(Proposta.class, id);
		
		if(proposta == null) {
			return ResponseEntity.notFound().build();
		}
		
		PropostaResponseDTO propostaResponseDTO = new PropostaResponseDTO(proposta);
		return ResponseEntity.ok(propostaResponseDTO);
	}

	
	@PostMapping(value = "/proposta")
	@Transactional
	public ResponseEntity<?> cadastrarProposta(@RequestBody @Valid PropostaRequestDTO propostaRequestDTO) {

		Proposta proposta = propostaRequestDTO.coverterParaEntidade(propostaRequestDTO);
		entityManager.persist(proposta);
		
		logger.info("Proposta {} salva.", proposta.toString());
		
		RetornoElegibilidade status = validadorCartao.avaliaElegibilidade(proposta);
		
		logger.info("Status de elegibilidade {}", status);

		proposta.setElegibilidade(status.getElegibilidade());
		
		entityManager.merge(proposta);
		
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(proposta.getId())
                .toUri();
		
		if(status.equals(RetornoElegibilidade.COM_RESTRICAO)) {
			return ResponseEntity.unprocessableEntity().build();
		}
			
			return ResponseEntity.created(location).build();
		
	}


}
