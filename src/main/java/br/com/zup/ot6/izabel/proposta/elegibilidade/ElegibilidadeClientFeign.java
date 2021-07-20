package br.com.zup.ot6.izabel.proposta.elegibilidade;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.zup.ot6.izabel.proposta.dto.ElegibilidadeRequest;
import br.com.zup.ot6.izabel.proposta.dto.ElegibilidadeResponse;


@FeignClient(name="elegibilidade", url = "http://localhost:9999/api/solicitacao")
public interface ElegibilidadeClientFeign {

	@PostMapping
	ElegibilidadeResponse analisaElegibilidade(ElegibilidadeRequest elegibilidadeRequest);
		
	
}
