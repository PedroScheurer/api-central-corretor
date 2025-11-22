package br.edu.atitus.api_example.entities;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_imoveis")
public class ImovelEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(length = 255, nullable = false)
	private String nome;
	@Column(length = 255, nullable = false)
	private String logradouro;
	@Column(length = 15)
	private String numero;
	@Column(length = 255)
	private String bairro;
	@Column(length = 255)
	private String complemento;
	@Column(length = 8, nullable = false)
	private String cep;
	@Column(length = 255, nullable = false)
	private String cidade;
	@Column(length = 255, nullable = false)
	private String estado;
	@Column(columnDefinition = "decimal(10,2)", nullable = false)
	private double area;
	@Column(length = 11, nullable = false)
	private String tipo;
	@Column(columnDefinition = "decimal(10,2)", nullable = false)
	private BigDecimal valor;

	@JoinColumn(name="id_point")
	@OneToOne(cascade = CascadeType.ALL)
	private PointEntity point;

	@JoinColumn(name = "id_user", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private UserEntity user;

	public ImovelEntity() {
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public PointEntity getPoint() {
		return point;
	}

	public void setPoint(PointEntity point) {
		this.point = point;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
