package br.com.zup.ot6.izabel.proposta.controladores;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.ot6.izabel.proposta.cartao.ValidadorCartao;
import br.com.zup.ot6.izabel.proposta.dto.AvisoRequest;
import br.com.zup.ot6.izabel.proposta.entidades.Aviso;
import br.com.zup.ot6.izabel.proposta.entidades.Cartao;

@RestController
public class AvisoControlador {
	
	@PersistenceContext
	private EntityManager entityManager;
	private ValidadorCartao validadorCartao;


	@Autowired
	public AvisoControlador(EntityManager entityManager, ValidadorCartao validadorCartao) {
		super();
		this.entityManager = entityManager;
		this.validadorCartao = validadorCartao;
	}

	@Transactional
	@PostMapping(value = "/avisoViagem/{id}")
	public ResponseEntity<Aviso> enviarAviso(@PathVariable("id") Long id, @RequestBody @Valid AvisoRequest avisoRequest,
			HttpServletRequest http) {
		
		String ip = http.getRemoteAddr();
		String sistemaResponsavel = http.getHeader(HttpHeaders.USER_AGENT);
		
		
		Cartao cartao = entityManager.find(Cartao.class, id);
		if(cartao == null) {
			return ResponseEntity.notFound().build();
		}
		
		validadorCartao.avisoViagem(cartao, avisoRequest);
		
		Aviso aviso = avisoRequest.toModel(ip, sistemaResponsavel, cartao);
		entityManager.persist(aviso);
		
		return ResponseEntity.ok().build();
	}

}
