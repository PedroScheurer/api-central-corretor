package br.edu.atitus.api_example.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.edu.atitus.api_example.dtos.ImovelDTO;
import br.edu.atitus.api_example.entities.ImovelEntity;
import br.edu.atitus.api_example.entities.PointEntity;
import br.edu.atitus.api_example.entities.UserEntity;
import br.edu.atitus.api_example.repositories.ImovelRepository;
import jakarta.transaction.Transactional;

@Service
public class ImovelService {

	private final ImovelRepository repository;

	public ImovelService(ImovelRepository repository) {
		super();
		this.repository = repository;
	}

	@Transactional
	public ImovelEntity save(ImovelEntity imovel) throws Exception {
		if (imovel == null) {
			throw new Exception("Objeto nulo");
		}

		if (imovel.getNome() == null || imovel.getNome().isEmpty()) {
			throw new Exception("Nome invalido");
		}

		if (imovel.getCep() == null || !imovel.getCep().matches("\\d{8}")) {
			throw new IllegalArgumentException("CEP inválido: deve conter exatamente 8 dígitos.");
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

		if (imovel.getPoint().getLongitude() == null
				|| !(imovel.getPoint().getLongitude() >= -180 && imovel.getPoint().getLongitude() <= 180)) {
			throw new Exception("Longitude invalida");
		}

		if (imovel.getPoint().getLatitude() == null
				|| !(imovel.getPoint().getLatitude() >= -90 && imovel.getPoint().getLatitude() <= 90)) {
			throw new Exception("Latitude invalida");
		}

		if (imovel.getPoint().getDescription() == null || imovel.getPoint().getDescription().isEmpty()) {
			throw new Exception("Descricao invalida");
		}

		UserEntity userAuth = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		imovel.setUser(userAuth);

		return repository.save(imovel);

	}

	public ImovelEntity fromDTO(ImovelDTO dto) {
		ImovelEntity imovel = new ImovelEntity();

		PointEntity point = new PointEntity();
		point.setDescription(dto.description());
		point.setLatitude(dto.latitude());
		point.setLongitude(dto.longitude());

		imovel.setNome(dto.nome());
		imovel.setLogradouro(dto.logradouro());
		imovel.setNumero(dto.numero());
		imovel.setBairro(dto.bairro());
		imovel.setComplemento(dto.complemento());
		imovel.setCep(dto.cep());
		imovel.setCidade(dto.cidade());
		imovel.setEstado(dto.estado());
		imovel.setArea(dto.area());

		imovel.setPoint(point);

		return imovel;
	}

	@Transactional
	public void deleteById(UUID id) throws Exception {
		var imovelInBd = repository.findById(id).orElseThrow(() -> new Exception("Imovel nao localizado"));
		UserEntity userAuth = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!imovelInBd.getUser().getId().equals(userAuth.getId())) {
			throw new Exception("Sem permissao");
		}

		repository.deleteById(id);
	}

	public List<ImovelEntity> findAll() {
		UserEntity userAuth = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<ImovelEntity> imoveis = repository.findByUser(userAuth);
		
		return imoveis;
	}
	
	public List<ImovelEntity> findByName(String nome){
		List<ImovelEntity> imoveis = repository.findByNomeContainsIgnoreCase(nome);
		return imoveis;
	}

}
