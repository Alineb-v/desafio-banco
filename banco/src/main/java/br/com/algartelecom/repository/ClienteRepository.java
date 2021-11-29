package br.com.algartelecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.algartelecom.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	

}
