package br.com.algartelecom.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transacao{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	private String numConta;
	private double valorTransacao;
	private LocalDateTime dataDaTransacao = LocalDateTime.now();
	@Enumerated(EnumType.STRING)
	private TipoTransacao tipoTransacao;
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
	public double getValorTransacao() {
		return valorTransacao;
	}
	public void setValorTransacao(double valorTransacao) {
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
	
	
}