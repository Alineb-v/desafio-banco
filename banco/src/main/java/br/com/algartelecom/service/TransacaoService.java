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

	// valorTransacao Double
	public Conta depositar(Transacao transacaoDeposito, String numConta) throws Exception {
		Conta conta = contaRepository.findByNumConta(numConta);
		if (transacaoDeposito.getValorTransacao() < 0) {
			throw new Exception();
		}
		Double novoValor = somar(transacaoDeposito.getValorTransacao(), conta.getSaldo());
		if (conta.getStatus() == StatusConta.ATIVO) {
			conta.setSaldo(novoValor);
			// Pq salvar transacao no Repository ?
			transacaoRepository.save(transacaoDeposito);
			contaRepository.save(conta);
			return conta;
		}
		return null;

	}

	public Conta sacar(Transacao transacaoSaque, String numConta) throws Exception {
		Conta conta = contaRepository.findByNumConta(numConta);
		if (conta.getSaldo() < 0 || conta.getSaldo() < transacaoSaque.getValorTransacao()) {
			throw new Exception();
		}

		Double novoValor = subtrair(conta.getSaldo(), transacaoSaque.getValorTransacao());
		if (conta.getStatus() == StatusConta.ATIVO) {
			conta.setSaldo(novoValor);
			transacaoRepository.save(transacaoSaque);
			contaRepository.save(conta);
			return conta;
		}

		return null;

	}

	public Conta transferir(Transacao transacaoTransferencia, String numConta) {
		Conta contaOrigem = contaRepository.findByNumConta(numConta);
		Conta contaDestino = contaRepository.findByNumConta(transacaoTransferencia.getNumContaDestino());
		
		if(contaOrigem == contaDestino || contaOrigem.getStatus() == StatusConta.INATIVO || 
				contaDestino.getStatus() == StatusConta.INATIVO || contaOrigem.getSaldo() < transacaoTransferencia.getValorTransacao()) {
			return null;
		}
	
		Double valorAtual = subtrair(contaOrigem.getSaldo(), transacaoTransferencia.getValorTransacao());
		contaOrigem.setSaldo(valorAtual);
		contaRepository.save(contaOrigem);
		contaDestino.setSaldo(transacaoTransferencia.getValorTransacao() + contaDestino.getSaldo());
		contaRepository.save(contaDestino);
		
		return contaOrigem;
		
		 
		
	}

	public Conta saldo(String numConta) {
		Conta conta = contaRepository.findByNumConta(numConta);
		if (conta.getStatus() == StatusConta.ATIVO) {
			return conta;

		}
		return null;

	}

	private Double somar(Double valorTransacao, Double saldo) {
		return valorTransacao + saldo;
	}

	private Double subtrair(Double valorTransacao, Double saldo) {
		return valorTransacao - saldo;

	}
}
