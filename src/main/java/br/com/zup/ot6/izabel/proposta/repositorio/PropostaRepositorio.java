package br.com.zup.ot6.izabel.proposta.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.zup.ot6.izabel.proposta.entidades.Proposta;

@Repository
public interface PropostaRepositorio extends JpaRepository<Proposta, Long> {
	
	public List<Proposta> findByCpfCnpj(String cpfCnpj);
	
	@Query("select p from Proposta p where p.elegibilidade = 'ELEGIVEL' and p.cartao = null")
	List<Proposta> propostasElegiveisSemCartaoEmitido();
}
