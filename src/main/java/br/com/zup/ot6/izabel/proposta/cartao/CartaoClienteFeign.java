package br.com.zup.ot6.izabel.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zup.ot6.izabel.proposta.dto.AvisoApiExternaRequest;
import br.com.zup.ot6.izabel.proposta.dto.AvisoApiExternaResponse;
import br.com.zup.ot6.izabel.proposta.dto.BloqueioRequest;
import br.com.zup.ot6.izabel.proposta.dto.BloqueioResponse;
import br.com.zup.ot6.izabel.proposta.dto.CartaoRequest;
import br.com.zup.ot6.izabel.proposta.dto.CartaoResponse;
import br.com.zup.ot6.izabel.proposta.dto.CarteiraApiExternaRequest;
import br.com.zup.ot6.izabel.proposta.dto.CarteiraApiExternaResponse;

@FeignClient(name = "solicitacao-numero-cartao", url = "${feign.cartao.url}")
public interface CartaoClienteFeign {

	@PostMapping
	public CartaoResponse solicitaCartao(CartaoRequest solicitacaoCartaorequest);

	@PostMapping("/{id}/bloqueios")
	public BloqueioResponse notificaBloqueioAoLegado(@PathVariable String id, @RequestBody BloqueioRequest request);
	
	@PostMapping("/{id}/avisos")
	public AvisoApiExternaResponse avisoViagemAoLegado(@PathVariable String id, @RequestBody AvisoApiExternaRequest request);
	
	@PostMapping("/{id}/carteiras")
	CarteiraApiExternaResponse associarCarteira(@PathVariable String id, @RequestBody CarteiraApiExternaRequest request);
	
}
