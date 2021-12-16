package br.com.algartelecom.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.algartelecom.model.Conta;
import br.com.algartelecom.model.StatusConta;
import br.com.algartelecom.model.TipoConta;
import br.com.algartelecom.model.TipoTransacao;
import br.com.algartelecom.model.Transacao;
import br.com.algartelecom.repository.ContaRepository;
import br.com.algartelecom.repository.TransacaoRepository;

class TransacaoServiceTest {

	private TransacaoService service;

	@Mock
	private ContaRepository contaRepository;

	@Mock
	private TransacaoRepository transacaoRepository;

	// estudar anotacao
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		this.service = new TransacaoService(contaRepository, transacaoRepository);
	}

	@Test
	void deveriaDepositarValorNaConta() throws Exception {
		// declarativo
		String numConta = "1";
		Transacao trans = new Transacao(1L, numConta, 50.0, null, null, null);
		Conta conta = new Conta(1L, "TESTE", "10045-3", TipoConta.CONTA_CORRENTE, 100.0, StatusConta.ATIVO);

		Mockito.when(contaRepository.findByNumConta(numConta)).thenReturn(conta);

		// execucao
		Conta contaDepoisExecucao = service.depositar(trans, numConta);

		// comparacao
		assertEquals(conta.getNumConta(), contaDepoisExecucao.getNumConta());
	}

	@Test
	void deveriaDarErroAoDepositarValorNaContaPeloStatusConta() throws Exception {
		// declarativo
		String numConta = "1";
		Transacao trans = new Transacao(1L, numConta, 50.0, null, null, null);
		Conta conta = new Conta(1L, null, "10045-3", null, 100.0, StatusConta.INATIVO);

		Mockito.when(contaRepository.findByNumConta(numConta)).thenReturn(conta);

		// execucao
		Conta contaDepoisExecucao = service.depositar(trans, numConta);

		// comparacao
		assertEquals(null, contaDepoisExecucao);
	}

	@Test
	void deveriaDarErroAoDepositarValorNaContaPeloValorTransacao() {
		// declarativo
		String numConta = "1";
		Transacao trans = new Transacao(1L, numConta, -50.0, null, null, null);
		Conta conta = new Conta(1L, null, "10045-3", null, 100.0, StatusConta.ATIVO);

		Mockito.when(contaRepository.findByNumConta(numConta)).thenReturn(conta);

		Assertions.assertThrows(Exception.class, () -> service.depositar(trans, numConta));

	}
	
	@Test
	void deveriaSacarValorDaConta () throws Exception{
		String numConta = "1";
		Transacao trans = new Transacao(1L, numConta, 50.0, null, null, numConta);
		Conta conta = new Conta(1L, "TESTE", "10045-3", null, 100.0, StatusConta.ATIVO);
		
		Mockito.when(contaRepository.findByNumConta(numConta)).thenReturn(conta);
		
		Conta contaDepoisExecucao = service.sacar(trans, numConta);
		
		assertEquals(conta.getNumConta(), contaDepoisExecucao.getNumConta());
	}
	
	@Test
	void deveriaDarErroPeloValorDoSaldoSerNegativo () throws Exception {
		//declarativo
		String numConta = "1";
		Transacao trans = new Transacao(1L, numConta, 50.0, null, null, numConta);
		Conta conta = new Conta(1L, "TESTE", numConta, null, -30.0, StatusConta.ATIVO);
		
		Mockito.when(contaRepository.findByNumConta(numConta)).thenReturn(conta);
		
		Assertions.assertThrows(Exception.class, () -> service.sacar(trans, numConta));
		
				
	}
	
	@Test 
	void deveriaDarErroPeloValorTransacaoSerMaiorQueSaldo() throws Exception {
		String numConta = "1";
		Transacao trans = new Transacao(1L, numConta, 100.0, null, null, numConta);
		Conta conta = new Conta(1L, "TESTE", numConta, null, 50.0, StatusConta.ATIVO);
		
		Mockito.when(contaRepository.findByNumConta(numConta)).thenReturn(conta);
		
		Assertions.assertThrows(Exception.class, () -> service.sacar(trans, numConta));
		}
	
	@Test
	void deveriaTransferirValor() throws Exception {
		String numContaOrigem = "1";
		String numContaDestino = "2";
		
		Transacao trans = new Transacao(1L, numContaOrigem, 50.0, null, null, numContaDestino);
		Conta contaOrigem = new Conta(1L, "TESTE", numContaOrigem, null, 100.0, StatusConta.ATIVO);
		Conta contaDestino = new Conta(2L, "TESTE", numContaDestino, null, 80.0, StatusConta.ATIVO);
		Double novoSaldo = contaOrigem.getSaldo() - trans.getValorTransacao();
		
		Mockito.when(contaRepository.findByNumConta(numContaDestino)).thenReturn(contaOrigem).thenReturn(contaDestino);
		Conta contaDepoisExecucao = service.transferir(trans, numContaDestino);
		
		assertEquals(novoSaldo, contaDepoisExecucao.getSaldo());
	}
	
	@Test
	void deveriaDarErroPorqueAsContasSaoIguais () throws Exception {
		String numContaOrigem = "1";
		String numContaDestino = "2";
		
		Transacao trans = new Transacao(1L, "10045-3", 50.0, null, null, "10045-3");
		Conta contaOrigem = new Conta(1L, "TESTE", "10045-3", null, 100.0, StatusConta.ATIVO);
		Conta contaDestino = new Conta(2L, "TESTE", "10045-3", null, 80.0, StatusConta.ATIVO);
		
		Mockito.when(contaRepository.findByNumConta(numContaDestino)).thenReturn(contaOrigem);
		
		Assertions.assertThrows(Exception.class, () -> service.transferir(trans, numContaOrigem));

		
	}
	
	@Test
	void deveriaDarErroPorqueContaEstaInativa() throws Exception{
		String numContaOrigem = "1";
		String numContaDestino = "2";
		
		Transacao trans = new Transacao(1L, numContaOrigem, 60.0, null, null, numContaDestino);
		Conta contaOrigem = new Conta(1L, numContaOrigem, numContaDestino, null, null, StatusConta.INATIVO);
		Conta contaDestino = new Conta(2L, numContaOrigem, numContaDestino, null, null, StatusConta.INATIVO);
		
		Mockito.when(contaRepository.findByNumConta(numContaDestino)).thenReturn(contaOrigem).thenReturn(contaDestino);
		
		Assertions.assertThrows(Exception.class, () ->service.transferir(trans, numContaOrigem));
	}
	
	@Test
	void deveriaDarErroPoqueValorSaldoEMenorValorTransacao() {
		String numContaOrigem = "1";
		String numContaDestino = "2";
		
		Transacao trans = new Transacao(1L, numContaOrigem, 80.0, null, null, numContaDestino);
		Conta contaOrigem = new Conta(1L, "Teste", numContaOrigem, null, 30.0, StatusConta.ATIVO);
		Conta contaDestino = new Conta(2L, "TESTE", numContaDestino, null, 60.0, StatusConta.ATIVO);
		
		Mockito.when(contaRepository.findByNumConta(numContaDestino)).thenReturn(contaOrigem);
		
		Assertions.assertThrows(Exception.class, () -> service.transferir(trans, numContaOrigem));
	
	
	}
	@Test 
	void deveriaMostrarSaldo () throws Exception {
		//declarativo
		String numConta = "1";
		Transacao trans = new Transacao(1L, numConta, 100.0, LocalDateTime.now(), null, numConta);
		Conta conta = new Conta(1L, "TESTE", "10045-3", TipoConta.CONTA_CORRENTE, 100.0, StatusConta.ATIVO);
		
		Mockito.when(contaRepository.findByNumConta(numConta)).thenReturn(conta);
		
		//execucao
		Conta contaDepoisExecucao = service.depositar(trans, numConta);
		
		//comparacao
		assertEquals(conta.getNumConta(), contaDepoisExecucao.getNumConta());
		
	}
	
	@Test
	//alterar nome
	void deveriaDarErroAoMostrarSaldoPeloStatusDaConta() throws Exception {
		String numConta = "1";
		Transacao trans = new Transacao(1L, numConta, 50.0, null, null, null);
		Conta conta = new Conta(1L, null, "10045-3", null, 100.0, StatusConta.INATIVO);
		
		Mockito.when(contaRepository.findByNumConta(numConta)).thenReturn(conta);
		
		Conta contaDepoisExecucao = service.depositar(trans, numConta);
		
		assertEquals(null, contaDepoisExecucao);
	}
	
	@Test
	void deveriaMostrarUmExtratoComTodasTransacoes() {
		ArrayList<Transacao> arraylist = new ArrayList<Transacao>();
				arraylist.add(new Transacao(TipoTransacao.DEPOSITO));
			
		Mockito.when(transacaoRepository.findAll());
		
		//colocar exceção
	}
}
