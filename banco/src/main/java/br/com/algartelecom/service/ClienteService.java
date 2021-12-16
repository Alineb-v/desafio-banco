package br.com.algartelecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.algartelecom.model.Cliente;
import br.com.algartelecom.repository.ClienteRepository;

@Service
public class ClienteService {
	
	ClienteRepository clienteRepository;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	// Save mesmo ?
	public Cliente cadastrar(Cliente novoCliente) {
		if (novoCliente == null) {
			return clienteRepository.save(novoCliente);

		}
		return null;

	}

	// Tenho que colocar parametro ?
	public Cliente listar(int Cliente) {
		List<Cliente> clientes = clienteRepository.findAll();
		if (clientes != null) {
			return clientes.get(Cliente);

		}
		return null;

	}

	public Cliente detalhar(Long id) {
		Optional<Cliente> clientes = clienteRepository.findById(id);
		if (clientes.isPresent()) {
			return clientes.get();
		}

		return null;

	}
	
	//retorna GET ? GET(Cliente) ?
	public Optional<Cliente> atualizar(Long id, Cliente clienteParaAtualizar) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
			if(cliente.isPresent()) {
				clienteRepository.save(clienteParaAtualizar);
				return cliente;
				
			}
			return null;
		}
	
	public Cliente deletar(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if(cliente.isPresent()) {
			clienteRepository.deleteById(id);
	
	}
		return null;
	
	}
	
	
}
