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
	
	public Conta depositar (Transacao transacaoDeposito, String numConta) {
		Conta conta = contaRepository.findByNumConta(numConta);
		if() {
			//verifica o valor da transacao <0 - return null
		}
		Double novoValor = somar(transacaoDeposito.getValorTransacao(), conta.getSaldo());
		if(conta.getStatus() == StatusConta.ATIVO) {
			conta.setSaldo(novoValor);
			//Pq salvar transacao no Repository ?
			transacaoRepository.save(transacaoDeposito);
			contaRepository.save(conta);
			return conta;
		}
		return null;
				
	}

	private Double somar(Double valorTransacao, Double saldo) {
		return valorTransacao + saldo;
	}

}
