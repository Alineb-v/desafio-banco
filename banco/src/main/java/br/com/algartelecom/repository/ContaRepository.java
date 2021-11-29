package br.com.algartelecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.algartelecom.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{

	@Query("SELECT c FROM Conta c WHERE c.numConta = :numConta")
	Conta findByTeste(@Param("numConta") String numConta);
	
	Conta findByNumConta(String numConta);

}





