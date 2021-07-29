package br.com.zup.ot6.izabel.proposta.controladores;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.ot6.izabel.proposta.cartao.ValidadorCartao;
import br.com.zup.ot6.izabel.proposta.dto.BloqueioRequest;
import br.com.zup.ot6.izabel.proposta.entidades.Bloqueio;
import br.com.zup.ot6.izabel.proposta.entidades.Cartao;

@RestController
@RequestMapping("/bloqueio")
public class BloqueioCartaoControlador {
	
	@PersistenceContext
	private EntityManager entityManager;
	private ValidadorCartao validadorCartao;
	
	@Autowired
	public BloqueioCartaoControlador(EntityManager entityManager, ValidadorCartao validadorCartao) {
		super();
		this.entityManager = entityManager;
		this.validadorCartao = validadorCartao;
	}

	@Transactional
	@PostMapping(value = "/cartao/{id}")
	public ResponseEntity<Bloqueio> bloquearCartao(@PathVariable("id") Long id, HttpServletRequest request) {
		
		String ip = validadorCartao.recuperaIp(request);
		String sistemaResponsavel = request.getHeader(HttpHeaders.USER_AGENT);
		
		BloqueioRequest bloqueioRequest = new BloqueioRequest(sistemaResponsavel);
		
		Cartao cartao = entityManager.find(Cartao.class, id);
		if(cartao == null) {
			return ResponseEntity.notFound().build();
		}
		
		validadorCartao.notificaBloqueio(cartao, bloqueioRequest);
		
		Bloqueio bloqueio = validadorCartao.atualizarBloqueio(cartao, sistemaResponsavel, ip);
		
		entityManager.persist(bloqueio);
		
		return ResponseEntity.ok().build();
	}


}
