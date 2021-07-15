package br.com.zup.ot6.izabel.proposta.controladores;


import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zup.ot6.izabel.proposta.dto.PropostaRequestDTO;
import br.com.zup.ot6.izabel.proposta.entidades.Proposta;

@RestController
public class PropostaControlador {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@PostMapping(value = "/proposta")
	@Transactional
	public ResponseEntity<Object> cadastraProposta(@RequestBody @Valid PropostaRequestDTO propostaRequestDTO) {
		
		Proposta proposta = propostaRequestDTO.coverterParaEntidade(propostaRequestDTO);
		entityManager.persist(proposta);
		
		
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(proposta.getId())
                .toUri();
		
		return ResponseEntity.created(location).build();
	}


}
