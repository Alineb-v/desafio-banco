package br.com.algartelecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
}
