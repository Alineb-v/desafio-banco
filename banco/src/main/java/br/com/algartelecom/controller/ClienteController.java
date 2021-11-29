package br.com.algartelecom.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.algartelecom.model.Cliente;
import br.com.algartelecom.repository.ClienteRepository;
import br.com.algartelecom.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteService clienteService;

	@PostMapping
	public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente novoCliente, UriComponentsBuilder uriBuilder) {

		try {
			URI uri = uriBuilder.path("/clientes").buildAndExpand(novoCliente.getId()).toUri();
			clienteRepository.save(novoCliente);

			return ResponseEntity.created(uri).body(novoCliente);

		} catch (WebServerException e) {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping
	public ResponseEntity<List<Cliente>> listar() {
		List<Cliente> clientes = clienteRepository.findAll();
		if (clientes != null) {
			return ResponseEntity.ok(clientes);
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> detalhar(@PathVariable Long id) {
		Cliente cliente = clienteService.detalhar(id);
		if (cliente != null) {
			return ResponseEntity.ok(cliente);

		}

		return ResponseEntity.notFound().build();

	}

	
	//a camada service aceita o .findById pq tá injetado o repository 
	//aqui não aceita o .findById porquê não tem na camada service.
	
	//USAR DE EXEMPLO - POST
	@PutMapping("/{id}")
	public ResponseEntity<Optional<Cliente>> atualizar(@PathVariable Long id, @RequestBody Cliente clienteParaAtualizar) {
		Optional<Cliente> cliente = clienteService.atualizar(id, clienteParaAtualizar);
		if (cliente.isPresent()) {
		return ResponseEntity.ok(cliente);
	}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if(cliente.isPresent()) {
			clienteRepository.deleteById(id);
			return ResponseEntity.ok().build();
			
		}
		return ResponseEntity.notFound().build();
		
	}

}
