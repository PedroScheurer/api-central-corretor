package br.edu.atitus.api_example.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.edu.atitus.api_example.dtos.ImovelDTO;
import br.edu.atitus.api_example.entities.ImovelEntity;
import br.edu.atitus.api_example.entities.PointEntity;
import br.edu.atitus.api_example.entities.UserEntity;
import br.edu.atitus.api_example.repositories.ImovelRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class ImovelService {

	private final ImovelRepository repository;
	@PersistenceContext
	private EntityManager entityManager;

	public ImovelService(ImovelRepository repository) {
		super();
		this.repository = repository;
	}

	@Transactional
	public ImovelEntity save(ImovelEntity imovel) throws Exception {

		validar(imovel);
		
		UserEntity userAuth = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		imovel.setUser(userAuth);

		return repository.save(imovel);

	}

	@Transactional
	public ImovelEntity update(UUID id, ImovelEntity novosDados) {

		ImovelEntity imovel = repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Imóvel não encontrado"));

		validar(novosDados);

		UserEntity userAuth = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity userManaged = entityManager.getReference(UserEntity.class, userAuth.getId());

		imovel.setNome(novosDados.getNome());
		imovel.setCep(novosDados.getCep());
		imovel.setLogradouro(novosDados.getLogradouro());
		imovel.setCidade(novosDados.getCidade());
		imovel.setEstado(novosDados.getEstado());
		imovel.setValor(novosDados.getValor());
		imovel.setTipo(novosDados.getTipo());
		imovel.setPoint(novosDados.getPoint());
		imovel.setUser(userManaged);

		return repository.save(imovel);
	}

	private void validar(ImovelEntity imovel) {
		if (imovel == null) {
			throw new IllegalArgumentException("Objeto nulo");
		}

		if (imovel.getNome() == null || imovel.getNome().isEmpty()) {
			throw new IllegalArgumentException("Nome invalido");
		}

		if (imovel.getCep() == null || !imovel.getCep().matches("\\d{8}")) {
			throw new IllegalArgumentException("CEP invalido: deve conter exatamente 8 digitos.");
		}

		if (imovel.getLogradouro() == null || imovel.getLogradouro().isEmpty()) {
			throw new IllegalArgumentException("Logradouro invalido");
		}

		if (imovel.getCidade() == null || imovel.getCidade().isEmpty()) {
			throw new IllegalArgumentException("Cidade invalido");
		}

		if (imovel.getEstado() == null || imovel.getEstado().isEmpty()) {
			throw new IllegalArgumentException("Estado invalido");
		}

		if (imovel.getValor() == null || imovel.getValor().compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Valor invalido");
		}

		if (!imovel.getTipo().equalsIgnoreCase("casa") && !imovel.getTipo().equalsIgnoreCase("apartamento")
				&& !imovel.getTipo().equalsIgnoreCase("terreno")) {
			throw new IllegalArgumentException("Tipo invalido");
		}

		if (imovel.getPoint().getLongitude() == null
				|| !(imovel.getPoint().getLongitude() >= -180 && imovel.getPoint().getLongitude() <= 180)) {
			throw new IllegalArgumentException("Longitude invalida");
		}

		if (imovel.getPoint().getLatitude() == null
				|| !(imovel.getPoint().getLatitude() >= -90 && imovel.getPoint().getLatitude() <= 90)) {
			throw new IllegalArgumentException("Latitude invalida");
		}
	}

	public ImovelEntity fromDTO(ImovelDTO dto) {
		ImovelEntity imovel = new ImovelEntity();

		PointEntity point = new PointEntity();
		point.setDescricao(dto.descricao());
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
		imovel.setTipo(dto.tipo());
		imovel.setValor(dto.valor());

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

	public List<ImovelEntity> findByName(String nome) {
		UserEntity userAuth = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		List<ImovelEntity> imoveis = repository.findByNomeContainsIgnoreCaseAndUser(nome, userAuth);
		return imoveis;
	}

}
