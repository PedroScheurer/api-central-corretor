package br.edu.atitus.api_example.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_vendas")
public class VendaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@JoinColumn(name="id_imovel", nullable = false)
	@OneToOne
	private ImovelEntity imovel;
	
	@JoinColumn(name="id_cliente", nullable = false)
	@ManyToOne
	private PessoaEntity cliente;
	
	@JoinColumn(name="id_user", nullable = false)
	@ManyToOne
	private UserEntity user;
	
	@Column(columnDefinition = "decimal(10,2)", nullable = false)
	private BigDecimal valor;
	
	@Column(columnDefinition = "decimal(10,2)")
	private BigDecimal entrada;
	
	@Column(columnDefinition = "decimal(10,2)")
	private BigDecimal desconto;
	
	@Column(columnDefinition = "decimal(10,2)")
	private BigDecimal acrescimo;
	
	@Column(columnDefinition = "decimal(10,2)")
	private BigDecimal comissao;
	
	@Column(name="forma_pagamento", nullable=false)
	private String formaPagamento;
	
	@Column(name="numero_parcelas", nullable=false)
	private int numeroParcelas;
	
	@Column(nullable=false)
	private LocalDate data;
	
	public VendaEntity() {}

	public ImovelEntity getImovel() {
		return imovel;
	}

	public void setImovel(ImovelEntity imovel) {
		this.imovel = imovel;
	}

	public PessoaEntity getCliente() {
		return cliente;
	}

	public void setCliente(PessoaEntity cliente) {
		this.cliente = cliente;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getEntrada() {
		return entrada;
	}

	public void setEntrada(BigDecimal entrada) {
		this.entrada = entrada;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public BigDecimal getAcrescimo() {
		return acrescimo;
	}

	public void setAcrescimo(BigDecimal acrescimo) {
		this.acrescimo = acrescimo;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public int getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(int numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public BigDecimal getComissao() {
		return comissao;
	}

	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
}	
