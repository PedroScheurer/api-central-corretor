package br.edu.atitus.api_example.dtos;

public record ImovelDTO(String nome, String logradouro, String numero, String bairro, String complemento, String cep,
		String cidade, String estado, double area, double latitude, double longitude, String descricao, String tipo,
		double valor) {

}