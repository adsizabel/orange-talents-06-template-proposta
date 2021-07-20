package br.com.zup.ot6.izabel.proposta.elegibilidade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zup.ot6.izabel.proposta.dto.ElegibilidadeRequest;
import br.com.zup.ot6.izabel.proposta.dto.ElegibilidadeResponse;
import br.com.zup.ot6.izabel.proposta.entidades.Proposta;
import feign.FeignException;

@Component
public class AvaliadorElegibilidade {

	private static final Logger logger = LoggerFactory.getLogger(AvaliadorElegibilidade.class);

	private final ElegibilidadeClientFeign cliente;

	@Autowired
	public AvaliadorElegibilidade(ElegibilidadeClientFeign cliente) {
		this.cliente = cliente;
	}
	
	public RetornoElegibilidade avaliaElegibilidade(Proposta proposta) {
		
		logger.info("Inicio da verificação de elegibilidade para a proposta {}", proposta.getCpfCnpj());

		try {
			ElegibilidadeRequest elegibilidadeRequest = new ElegibilidadeRequest(proposta);
			
			logger.info("Enviando dados: {}", elegibilidadeRequest.toString());
			
			ElegibilidadeResponse elegibilidadeResponse = cliente.analisaElegibilidade(elegibilidadeRequest);
			
			logger.info("Elegibilidade: {}", elegibilidadeResponse.toString());
			
			return elegibilidadeResponse.getRetornoElegibilidade();
			
		}catch (FeignException.UnprocessableEntity e) {
			logger.info(e.getLocalizedMessage());
			return RetornoElegibilidade.COM_RESTRICAO;
		}
	}

}
