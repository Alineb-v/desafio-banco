package br.com.algartelecom.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.algartelecom.repository.ClienteRepository;

class ClienteServiceTest {
	
	private ClienteService service;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@BeforeEach
	public void beforeEach () {
		MockitoAnnotations.openMocks(clienteRepository);
		this.service = new ClienteService(clienteRepository);
	}

	@Test
	void deveriaSalvarUmCliente() {
		
	
	}

}
