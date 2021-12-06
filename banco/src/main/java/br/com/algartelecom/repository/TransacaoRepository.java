package br.com.algartelecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.algartelecom.model.Transacao;

//responsavel por acessar as informacoes
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
	
	List<Transacao> findByNumConta(String numConta);
	


}
