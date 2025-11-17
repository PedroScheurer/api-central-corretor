package br.edu.atitus.api_example.dtos;

public record ImovelDTO(String logradouro, String numero, String bairro,  String complemento, int cep,
		String cidade, String estado,double area, double latitude, double longitude, String description) {

}