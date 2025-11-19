package br.edu.atitus.api_example.dtos;

public record PessoaDTO(String nome, String sobrenome, String cpf, String rg, String orgEmissor, String uf,
		String logradouro, String numero, String bairro, String complemento, String cep, String cidade, String estado,
		String email, String telefone1, String telefone2, String nacionalidade, String sexo, String observacoes) {

}
