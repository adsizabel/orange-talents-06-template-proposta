package br.com.zup.ot6.izabel.proposta.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

@Component
public class ExecutorDeTransacao {

	@PersistenceContext
	private EntityManager manager;
	
	public ExecutorDeTransacao(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Transactional
	public <T> T salvaEComita(T objeto) {
		manager.persist(objeto);
		return objeto;
	}

	@Transactional
	public <T> T atualizaEComita(T objeto) {
		return manager.merge(objeto);
    }
	
	@Transactional
	public <T> T pesquisa(Class<T> classe, Long id) {
		return manager.find(classe, id);
    }
}