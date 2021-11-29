package br.com.algartelecom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.algartelecom.model.Conta;
import br.com.algartelecom.model.StatusConta;
import br.com.algartelecom.model.Transacao;
import br.com.algartelecom.repository.ContaRepository;
import br.com.algartelecom.repository.TransacaoRepository;

@Service
public class TransacaoService {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	public Conta depositar (Transacao transacao, String numConta, Long id) {
		Conta conta = contaRepository.findByNumConta(numConta);
		Double novoValor = somar(transacao.getValorTransacao(), conta.getSaldo());
		if(conta.getStatus() == StatusConta.ATIVO) {
			conta.setSaldo(novoValor);
			transacaoRepository.save(transacao);
			contaRepository.save(conta);
			return conta;
		}
		return null;
				
	}

	private Double somar(double valorTransacao, Double saldo) {
		return valorTransacao + saldo;
	}

}
