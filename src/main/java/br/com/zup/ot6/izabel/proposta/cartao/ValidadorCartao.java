package br.com.zup.ot6.izabel.proposta.cartao;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zup.ot6.izabel.proposta.dto.ElegibilidadeRequest;
import br.com.zup.ot6.izabel.proposta.dto.ElegibilidadeResponse;
import br.com.zup.ot6.izabel.proposta.elegibilidade.ElegibilidadeClienteFeign;
import br.com.zup.ot6.izabel.proposta.elegibilidade.RetornoElegibilidade;
import br.com.zup.ot6.izabel.proposta.entidades.Bloqueio;
import br.com.zup.ot6.izabel.proposta.entidades.Cartao;
import br.com.zup.ot6.izabel.proposta.entidades.Proposta;
import feign.FeignException;

@Component
public class ValidadorCartao {

	private static final Logger logger = LoggerFactory.getLogger(ValidadorCartao.class);

	private final ElegibilidadeClienteFeign cliente;

	@Autowired
	public ValidadorCartao(ElegibilidadeClienteFeign cliente) {
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
	
	public Bloqueio atualizarBloqueio(Cartao cartao, String ip, String sistemaResponsavel) {
		if(jaBloqueado(cartao)) {
			throw new IllegalStateException("Cartão já está bloqueado");
		}
		return cartao.bloquearCartao(ip, sistemaResponsavel);
	}

	public boolean jaBloqueado(Cartao cartao) {
		return cartao.getBloqueio().equals(StatusBloqueio.BLOQUEADO);
		
	}
	
	public String recuperaIp(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}

		return ipAddress;
	}
}
