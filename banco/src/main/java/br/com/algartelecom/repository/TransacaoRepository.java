package br.com.algartelecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.algartelecom.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
	


}
