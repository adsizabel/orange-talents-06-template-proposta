package br.com.zup.ot6.izabel.proposta.elegibilidade;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zup.ot6.izabel.proposta.dao.ExecutorDeTransacao;
import br.com.zup.ot6.izabel.proposta.dto.CartaoRequest;
import br.com.zup.ot6.izabel.proposta.dto.CartaoResponse;
import br.com.zup.ot6.izabel.proposta.entidades.Cartao;
import br.com.zup.ot6.izabel.proposta.entidades.Proposta;
import br.com.zup.ot6.izabel.proposta.repositorio.PropostaRepositorio;
import feign.FeignException;

@Component
public class CartaoScheduling {
	private static final Logger logger = LoggerFactory.getLogger(CartaoScheduling.class);
	
	private PropostaRepositorio propostaRepositorio;
	private CartaoClienteFeign cartaoFeign;
	private ExecutorDeTransacao transacao;
	
	@Autowired
	public CartaoScheduling(PropostaRepositorio propostaRepositorio, CartaoClienteFeign cartaoFeign,
			ExecutorDeTransacao transacao) {
		super();
		this.propostaRepositorio = propostaRepositorio;
		this.cartaoFeign = cartaoFeign;
		this.transacao = transacao;
	}
	
	@Scheduled(fixedDelayString = "${periodicidade.associar.cartao}")
    private void recuperarPropostasElegiveisSemCartao() {

		logger.info("Início do Scheduling de cartão");
		
		List<Proposta> propostasElegiveisSemCartao = propostaRepositorio.propostasElegiveisSemCartaoEmitido();
		
		for (Proposta proposta : propostasElegiveisSemCartao) {
			try {
				
				logger.info("Solicita cartão para a proposta: {}", proposta.getCpfCnpj());
				
				CartaoRequest cartaoRequest = new CartaoRequest(Long.toString(proposta.getId()), proposta.getNome(), proposta.getCpfCnpj());
				
				logger.info("Cartao Request: {}", cartaoRequest.toString());
				
				CartaoResponse cartaoResponse = cartaoFeign.solicitaCartao(cartaoRequest);
				
				logger.info("Numero do Cartão: {}", cartaoResponse.getNumeroCartao());
				
				Cartao cartao = cartaoResponse.converterParaEntidade(proposta);

				proposta.associaCartao(cartao);
				
				transacao.atualizaEComita(proposta);
				
				logger.info("Proposta [ {} ]", proposta.toString());
				
			} catch (FeignException e) {
				e.getCause();
			}
			
		}}	}

