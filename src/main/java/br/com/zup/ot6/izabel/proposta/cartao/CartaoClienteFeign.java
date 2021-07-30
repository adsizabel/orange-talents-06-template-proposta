package br.com.zup.ot6.izabel.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.zup.ot6.izabel.proposta.dto.AvisoApiExternaRequest;
import br.com.zup.ot6.izabel.proposta.dto.AvisoApiExternaResponse;
import br.com.zup.ot6.izabel.proposta.dto.AvisoRequest;
import br.com.zup.ot6.izabel.proposta.dto.BloqueioRequest;
import br.com.zup.ot6.izabel.proposta.dto.BloqueioResponse;
import br.com.zup.ot6.izabel.proposta.dto.CartaoRequest;
import br.com.zup.ot6.izabel.proposta.dto.CartaoResponse;

@FeignClient(name = "solicitacao-numero-cartao", url = "${feign.cartao.url}")
public interface CartaoClienteFeign {

	@PostMapping
	public CartaoResponse solicitaCartao(CartaoRequest solicitacaoCartaorequest);

	@PostMapping("/{id}/bloqueios")
	public BloqueioResponse notificaBloqueioAoLegado(@PathVariable String id, BloqueioRequest request);
	
	@PostMapping("/{id}/avisos")
	public AvisoApiExternaResponse avisoViagemAoLegado(@PathVariable String id, AvisoApiExternaRequest request);
}
