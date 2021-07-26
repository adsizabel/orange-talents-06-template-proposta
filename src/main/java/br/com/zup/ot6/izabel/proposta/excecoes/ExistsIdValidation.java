package br.com.zup.ot6.izabel.proposta.excecoes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class ExistsIdValidation implements ConstraintValidator<ExistsId, Long>{

	private String domainAttribute;
	private Class<?> klass;
	
	@PersistenceContext
	EntityManager entityManager;
	
	public void initialize(ExistsId existsId) {
		domainAttribute = existsId.fieldName();
        klass = existsId.domainClass();
        
    }
	
	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {

		Query query = entityManager.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + "=:value");
		query.setParameter("value", value);	
		List<?> list = query.getResultList();
		
		Assert.isTrue(list.size() <=1, "ID invalido");

		return !list.isEmpty();

		
	}

}
