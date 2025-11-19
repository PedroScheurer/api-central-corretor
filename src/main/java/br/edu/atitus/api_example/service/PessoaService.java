package br.edu.atitus.api_example.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.edu.atitus.api_example.entities.PessoaEntity;
import br.edu.atitus.api_example.entities.UserEntity;
import br.edu.atitus.api_example.repositories.PessoaRepository;
import jakarta.transaction.Transactional;

@Service
public class PessoaService {

	private final PessoaRepository repository;

	public PessoaService(PessoaRepository repository) {
		super();
		this.repository = repository;
	}

	@Transactional
	public PessoaEntity save(PessoaEntity pessoa) throws Exception {
		if(pessoa == null) {
			throw new IllegalArgumentException("Objeto nulo");
		}
		if(pessoa.getNome() == null || pessoa.getNome().isEmpty()) {
			throw new IllegalArgumentException("Nome invalido");
		}
		if(pessoa.getCpf() == null || pessoa.getCpf().isEmpty()) {
			throw new IllegalArgumentException("CPF invalido");
		}
		if(pessoa.getEmail() == null || pessoa.getEmail().isEmpty()) {
			throw new IllegalArgumentException("Email invalido");
		}
		if(pessoa.getLogradouro() == null || pessoa.getLogradouro().isEmpty()) {
			throw new IllegalArgumentException("Logradouro invalido");
		}
		if(pessoa.getCidade() == null || pessoa.getCidade().isEmpty()) {
			throw new IllegalArgumentException("Cidade invalido");
		}
		if(pessoa.getEstado() == null || pessoa.getEstado().isEmpty()) {
			throw new IllegalArgumentException("Estado invalido");
		}
		if (pessoa.getCep() == null || !pessoa.getCep().matches("\\d{8}")) {
			throw new IllegalArgumentException("CEP invalido: deve conter exatamente 8 digitos.");
		}

		
		UserEntity userAuth = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		pessoa.setUser(userAuth);
		return repository.save(pessoa);
	}
	
	@Transactional
	public void deleteById(UUID id) throws Exception {
		var pessoaInBd = repository.findById(id).orElseThrow(() -> new Exception("Pessoa nao localizada"));
		UserEntity userAuth = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!pessoaInBd.getUser().getId().equals(userAuth.getId())) {
			throw new Exception("Sem permissao");
		}

		repository.deleteById(id);
	}
	
	public List<PessoaEntity> findAll(){
		UserEntity userAuth = (UserEntity )SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return repository.findByUser(userAuth);
	}
	
	public List<PessoaEntity> findByName(String nome){
		UserEntity userAuth = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return repository.findByNomeContainsIgnoreCaseAndUser(nome, userAuth);
	}
}
