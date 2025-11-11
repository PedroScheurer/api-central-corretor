package br.edu.atitus.api_example.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_imoveis")
public class ImovelEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(length = 255, nullable = false)
	private String logradouro;
	@Column(length = 15)
	private String numero;
	@Column(length = 255)
	private String bairro;
	@Column(length = 255)
	private String complemento;
	@Column(length = 8, nullable = false)
	private int cep;
	@Column(length = 255, nullable = false)
	private String cidade;
	@Column(length = 255, nullable = false)
	private String estado;
	@Column(columnDefinition = "decimal(10,2)", nullable = false)
	private double area;
	
	@JoinColumn
	@OneToOne(fetch = FetchType.LAZY)
	private PointEntity point;
	
	public ImovelEntity(String logradouro, String numero, String bairro, String complemento, int cep, String cidade,
			String estado, double area) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.complemento = complemento;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		this.area = area;
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

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
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
	
}
