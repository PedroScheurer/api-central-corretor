package br.edu.atitus.api_example.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;

import br.edu.atitus.api_example.entities.ImovelEntity;
import br.edu.atitus.api_example.entities.UserEntity;
import br.edu.atitus.api_example.repositories.ImovelRepository;
import jakarta.transaction.Transactional;

public class ImovelService {

	private final ImovelRepository repository;

	public ImovelService(ImovelRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Transactional
	public ImovelEntity save(ImovelEntity imovel) throws Exception {
		if(imovel == null) {
			throw new Exception("Objeto nulo");
		}
	
		if (imovel.getNome() == null || imovel.getNome().isEmpty()) {
			throw new Exception("Nome invalido");
		}
		
		if(imovel.getCep() != 8) {
			throw new Exception("CEP invalido");
		}
		
		if (imovel.getLogradouro() == null || imovel.getLogradouro().isEmpty()) {
			throw new Exception("Logradouro invalido");
		}
		
		if (imovel.getCidade() == null || imovel.getCidade().isEmpty()) {
			throw new Exception("Cidade invalido");
		}
		
		if (imovel.getEstado() == null || imovel.getEstado().isEmpty()) {
			throw new Exception("Estado invalido");
		}
		
		UserEntity userAuth = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		imovel.setUser(userAuth);
		
		imovel.setPoint();
		
		return repository.save(imovel);
		
	}
	
	@Transactional
	public void deleteById(UUID id)throws Exception{
		var imovelInBd = repository.findById(id).orElseThrow(() -> new Exception("Imovel nao localizado"));
		UserEntity userAuth = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!imovelInBd.getUser().getId().equals(userAuth.getId())) {
			throw new Exception("Sem permissao");
		};
		
		repository.deleteById(id);
	}
	
	public List<ImovelEntity> findAll(){
		return repository.findAll();
	}
	
}
