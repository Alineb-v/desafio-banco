package br.com.algartelecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.algartelecom.model.Conta;
import br.com.algartelecom.model.Transacao;
import br.com.algartelecom.service.TransacaoService;

@RestController
@RequestMapping("/transacao")
//Controller serve para retornar a lógica do service
public class TransacaoController {

	
	@Autowired
	private TransacaoService transacaoService;
	

	@PostMapping("depositar/{numConta}")
	public ResponseEntity<Conta> depositar(@PathVariable String numConta, @RequestBody Transacao transacaoDeposito) {
		try {
			Conta conta = transacaoService.depositar(transacaoDeposito, numConta);
			return ResponseEntity.ok(conta);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("sacar/{numConta}")
	public ResponseEntity<Conta> sacar(@PathVariable String numConta, @RequestBody Transacao transacaoSaque) {
		try {
			Conta conta = transacaoService.sacar(transacaoSaque, numConta);
			return ResponseEntity.ok(conta);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("transferir/{numConta}")
	public ResponseEntity<Conta> transferir(@PathVariable String numConta,
			@RequestBody Transacao transacaoTransferencia) {
		try {
			Conta conta = transacaoService.transferir(transacaoTransferencia, numConta);
			return ResponseEntity.ok(conta);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("saldo/{numConta}")
	public ResponseEntity<Conta> saldo(@PathVariable String numConta) {
		try {
			Conta conta = transacaoService.saldo(numConta);
			return ResponseEntity.ok(conta);

		} catch (Exception e) {

			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("extrato/{numConta}")
	public ResponseEntity<List<Transacao>> extrato(@PathVariable String numConta) {
		try {
			return ResponseEntity.ok(transacaoService.extrato(numConta));

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("extrato")
	public ResponseEntity<List<Transacao>> extrato() {
		try {
			return ResponseEntity.ok(transacaoService.pegaTodas());

		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

}
