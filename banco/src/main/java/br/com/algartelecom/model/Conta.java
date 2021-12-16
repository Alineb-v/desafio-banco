package br.com.algartelecom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Conta {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String numConta;
	@Enumerated(EnumType.STRING)
	private TipoConta tipoConta = TipoConta.CONTA_CORRENTE;
	//tratar saldo para não aceitar valor negativo
	private Double saldo = 0.0;	
	@Enumerated(EnumType.STRING)
	private StatusConta status = StatusConta.ATIVO;



	public Conta() {
		super();
	}

	public Conta(Long id, String nome, String numConta, TipoConta tipoConta, Double saldo, StatusConta status) {
		this.id = id;
		this.nome = nome;
		this.numConta = numConta;
		this.tipoConta = tipoConta;
		this.saldo = saldo;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumConta() {
		return numConta;
	}

	public void setNumConta(String numConta) {
		this.numConta = numConta;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public StatusConta getStatus() {
		return status;
	}

	public void setStatus(StatusConta status) {
		this.status = status;
	}

}
