package br.com.zup.ot6.izabel.proposta.elegibilidade;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.zup.ot6.izabel.proposta.dto.CartaoRequest;
import br.com.zup.ot6.izabel.proposta.dto.CartaoResponse;

@FeignClient(name = "solicitacao-numero-cartao", url = "http://localhost:8888/api/cartoes")
public interface CartaoClienteFeign {

	@PostMapping
	public CartaoResponse solicitaCartao(CartaoRequest solicitacaoCartaorequest);

}
