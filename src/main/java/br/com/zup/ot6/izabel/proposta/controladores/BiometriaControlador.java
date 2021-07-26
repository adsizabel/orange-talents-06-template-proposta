package br.com.zup.ot6.izabel.proposta.controladores;

import java.net.URI;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.zup.ot6.izabel.proposta.dao.ExecutorDeTransacao;
import br.com.zup.ot6.izabel.proposta.dto.BiometriaRequest;
import br.com.zup.ot6.izabel.proposta.entidades.Biometria;
import br.com.zup.ot6.izabel.proposta.entidades.Cartao;

@RestController
public class BiometriaControlador {
	
	ExecutorDeTransacao transacao;
	
	@Autowired
	public BiometriaControlador(ExecutorDeTransacao transacao) {
		super();
		this.transacao = transacao;
	}

	@PostMapping(value = "cartao/{id}/biometria")
	public ResponseEntity<Biometria> cadastrarBiometria(@PathVariable("id") Long id, @RequestBody @Valid BiometriaRequest biometriaRequest) {
		Cartao cartao = transacao.pesquisa(Cartao.class, id);
		if(cartao == null) {
			return ResponseEntity.notFound().build();
		}
		
		Biometria biometria = biometriaRequest.converterParaEntidade(cartao);
		transacao.salvaEComita(biometria);
		
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("cartao/{id}/biometria/{id}")
                .buildAndExpand(cartao.getId(), biometria.getId())
                .toUri();
	
		return ResponseEntity.created(location).build();
	}


}
