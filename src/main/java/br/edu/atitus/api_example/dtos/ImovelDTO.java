package br.edu.atitus.api_example.dtos;

import java.math.BigDecimal;

public record ImovelDTO(String nome, String logradouro, String numero, String bairro, String complemento, String cep,
		String cidade, String estado, double area, double latitude, double longitude, String descricao, String tipo,
		BigDecimal valor) {

}