package br.com.zup.ot6.izabel.proposta.controladores;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zup.ot6.izabel.proposta.cartao.ValidadorCartao;
import br.com.zup.ot6.izabel.proposta.dto.CarteiraRequest;
import br.com.zup.ot6.izabel.proposta.entidades.Cartao;
import br.com.zup.ot6.izabel.proposta.entidades.Carteira;

@RestController
public class CarteiraControlador {

	@PersistenceContext
	private EntityManager entityManager;
	private ValidadorCartao validadorCartao;

	@Autowired
	public CarteiraControlador(EntityManager entityManager, ValidadorCartao validadorCartao) {
		super();
		this.entityManager = entityManager;
		this.validadorCartao = validadorCartao;
	}

	@Transactional
	@PostMapping(value = "/carteiras/{id}")
	public ResponseEntity<Carteira> associarCarteira(@PathVariable("id") Long id, @RequestBody CarteiraRequest carteiraRequest) {

		Cartao cartao = entityManager.find(Cartao.class, id);
		if(cartao == null) {
			return ResponseEntity.notFound().build();
		}
		
		validadorCartao.associarCarteira(cartao, carteiraRequest);
		
		Carteira carteira = carteiraRequest.converterParaEntidade(cartao);
		entityManager.persist(carteira);
		
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("cartao/{id}/biometria/{id}")
                .buildAndExpand(cartao.getId(), carteira.getId())
                .toUri();
		
		return ResponseEntity.created(location).build();
	}

}
