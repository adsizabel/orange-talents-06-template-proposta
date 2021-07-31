package br.com.zup.ot6.izabel.proposta.cartao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zup.ot6.izabel.proposta.dto.AvisoApiExternaRequest;
import br.com.zup.ot6.izabel.proposta.dto.AvisoApiExternaResponse;
import br.com.zup.ot6.izabel.proposta.dto.AvisoRequest;
import br.com.zup.ot6.izabel.proposta.dto.BloqueioRequest;
import br.com.zup.ot6.izabel.proposta.dto.BloqueioResponse;
import br.com.zup.ot6.izabel.proposta.dto.CarteiraApiExternaRequest;
import br.com.zup.ot6.izabel.proposta.dto.CarteiraApiExternaResponse;
import br.com.zup.ot6.izabel.proposta.dto.CarteiraRequest;
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

	private final ElegibilidadeClienteFeign clienteElegibilidade;
	private final CartaoClienteFeign clienteCartao;

	@Autowired
	public ValidadorCartao(ElegibilidadeClienteFeign clienteElegibilidade, CartaoClienteFeign clienteBloqueio) {
		this.clienteElegibilidade = clienteElegibilidade;
		this.clienteCartao = clienteBloqueio;
	}
	
	public RetornoElegibilidade avaliaElegibilidade(Proposta proposta) {
		
		logger.info("Inicio da verificação de elegibilidade para a proposta {}", proposta.getCpfCnpj());

		try {
			ElegibilidadeRequest elegibilidadeRequest = new ElegibilidadeRequest(proposta);
			
			logger.info("Enviando dados: {}", elegibilidadeRequest.toString());
			
			ElegibilidadeResponse elegibilidadeResponse = clienteElegibilidade.analisaElegibilidade(elegibilidadeRequest);
			
			logger.info("Elegibilidade: {}", elegibilidadeResponse.toString());
			
			return elegibilidadeResponse.getRetornoElegibilidade();
			
		}catch (FeignException.UnprocessableEntity e) {
			logger.info(e.getLocalizedMessage());
			return RetornoElegibilidade.COM_RESTRICAO;
		}
	}
	
	public BloqueioResponse notificaBloqueio(Cartao cartao, BloqueioRequest bloqueioRequest) {
		
		logger.info("Inicio da notificação de bloqueio do cartão");

		if(cartao.jaBloqueado()) {
			throw new IllegalStateException("Cartão já está bloqueado");
		}
		
		try {
			BloqueioResponse bloqueioResponse = clienteCartao.notificaBloqueioAoLegado(cartao.getNumero(), bloqueioRequest);
			logger.info("Notificação ao Legado Realizada");
			return bloqueioResponse;
		} catch (FeignException e) {
			logger.info("Não foi possível efetuar esta solicitação. {} ", e.getMessage());
			throw new IllegalStateException("Não foi possível efetuar esta solicitação.");
		}
		
	}
	
	public AvisoApiExternaResponse avisoViagem(Cartao cartao, AvisoRequest request) {
		
		logger.info("Inicio da notificação de viagem.");
		
		AvisoApiExternaRequest apiExternaRequest = new AvisoApiExternaRequest(request.getDestino(), request.getTerminoDaViagem());
		
		try {
			AvisoApiExternaResponse response = clienteCartao.avisoViagemAoLegado(cartao.getNumero(), apiExternaRequest);
			logger.info("Notificação ao Legado Realizada");
			return response;
		} catch (FeignException.BadRequest e) {
			logger.info("Não foi possível efetuar esta solicitação. {} ", e.getMessage());
			throw new IllegalStateException("Não foi possível efetuar esta solicitação.");
		}	
	}
	
	public CarteiraApiExternaResponse associarCarteira(Cartao cartao, CarteiraRequest carteiraRequest) {
		
		logger.info("Inicio da associação à carteira digital PayPal");
		
		CarteiraApiExternaRequest  apiExternaRequest = new CarteiraApiExternaRequest(carteiraRequest.getEmail(), carteiraRequest.getCarteira());
		
		try {
			CarteiraApiExternaResponse response = clienteCartao.associarCarteira(cartao.getNumero(), apiExternaRequest);
			logger.info("Associação a Carteira digital realizada");
			return response;
		} catch (FeignException.BadRequest e) {
			throw new IllegalStateException("Não foi possível efetuar esta solicitação.");
		}
	}
	
	public Bloqueio atualizarBloqueio(Cartao cartao, String ip, String sistemaResponsavel) {
		if(cartao.jaBloqueado()) {
			throw new IllegalStateException("Cartão já está bloqueado");
		}
		return cartao.bloquearCartao(ip, sistemaResponsavel);
	}

}
