package br.com.algartelecom.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numConta;
	private Double valorTransacao;
	private LocalDateTime dataDaTransacao = LocalDateTime.now();
	@Enumerated(EnumType.STRING)
	private TipoTransacao tipoTransacao;
	private String numContaDestino;
	
	public Transacao() {
		super();
	}

	public Transacao(Long id, String numConta, Double valorTransacao, LocalDateTime dataDaTransacao,
			TipoTransacao tipoTransacao, String numContaDestino) {
		this.id = id;
		this.numConta = numConta;
		this.valorTransacao = valorTransacao;
		this.dataDaTransacao = dataDaTransacao;
		this.tipoTransacao = tipoTransacao;
		this.numContaDestino = numContaDestino;
	}

	public Transacao(TipoTransacao deposito) {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumConta() {
		return numConta;
	}

	public void setNumConta(String numConta) {
		this.numConta = numConta;
	}

	public Double getValorTransacao() {
		return valorTransacao;
	}

	public void setValorTransacao(Double valorTransacao) {
		this.valorTransacao = valorTransacao;
	}

	public LocalDateTime getDataDaTransacao() {
		return dataDaTransacao;
	}

	public void setDataDaTransacao(LocalDateTime dataDaTransacao) {
		this.dataDaTransacao = dataDaTransacao;
	}

	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(TipoTransacao tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}

	public String getNumContaDestino() {
		return numContaDestino;
	}

	public void setNumContaDestino(String numContaDestino) {
		this.numContaDestino = numContaDestino;
	}

}
