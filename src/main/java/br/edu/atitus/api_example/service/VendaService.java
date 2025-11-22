package br.edu.atitus.api_example.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.edu.atitus.api_example.dtos.VendaDTO;
import br.edu.atitus.api_example.entities.PessoaEntity;
import br.edu.atitus.api_example.entities.UserEntity;
import br.edu.atitus.api_example.entities.VendaEntity;
import br.edu.atitus.api_example.repositories.ImovelRepository;
import br.edu.atitus.api_example.repositories.PessoaRepository;
import br.edu.atitus.api_example.repositories.VendaRepository;
import jakarta.transaction.Transactional;

@Service
public class VendaService {

	private final VendaRepository repository;
	private final PessoaRepository pessoaRepository;
	private final ImovelRepository imovelRepository;

	public VendaService(VendaRepository repository, PessoaRepository pessoaRepository,
			ImovelRepository imovelRepository) {
		super();
		this.repository = repository;
		this.pessoaRepository = pessoaRepository;
		this.imovelRepository = imovelRepository;
	}

	@Transactional
	public VendaEntity save(VendaEntity venda) {
		if (venda == null) {
			throw new IllegalArgumentException("Objeto nulo");
		}
		if (venda.getImovel() == null) {
			throw new IllegalArgumentException("Imovel invalido");
		}
		if (venda.getCliente() == null) {
			throw new IllegalArgumentException("Cliente invalido");
		}
		if (venda.getValor() == null || venda.getValor().compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Valor invalido");
		}
		if (venda.getNumeroParcelas() <= 0 || venda.getNumeroParcelas() > 420) {
			throw new IllegalArgumentException("Numero de parcelas invalido");
		}
		if (!venda.getFormaPagamento().equalsIgnoreCase("a_vista")
				&& !venda.getFormaPagamento().equalsIgnoreCase("financiamento")
				&& !venda.getFormaPagamento().equalsIgnoreCase("consorcio")
				&& !venda.getFormaPagamento().equalsIgnoreCase("permuta")) {
			throw new IllegalArgumentException("Forma de pagamento invalida");
		}

		UserEntity userAuth = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		venda.setUser(userAuth);
		venda.setData(LocalDate.now());

		return repository.save(venda);
	}

	public VendaEntity fromDTO(VendaDTO dto) {
		VendaEntity venda = new VendaEntity();

		venda.setCliente(pessoaRepository.findById(dto.idCliente())
				.orElseThrow(() -> new RuntimeException("Cliente nao localizado")));

		venda.setImovel(imovelRepository.findById(dto.idImovel())
				.orElseThrow(() -> new RuntimeException("Imovel nao localizado")));

		venda.setValor(dto.valor());
		venda.setFormaPagamento(dto.formaPagamento());
		venda.setEntrada(dto.entrada());
		venda.setAcrescimo(dto.acrescimo());
		venda.setDesconto(dto.desconto());
		venda.setComissao(dto.comissao());
		venda.setNumeroParcelas(dto.numeroParcelas());

		return venda;
	}

	@Transactional
	public void deleteById(UUID id) throws Exception {
		var vendaInBd = repository.findById(id).orElseThrow(() -> new RuntimeException("Venda nao localizado"));
		UserEntity userAuth = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!vendaInBd.getUser().getId().equals(userAuth.getId())) {
			throw new Exception("Sem permissao");
		}

		repository.deleteById(id);
	}

	public List<VendaEntity> findAll() {
		UserEntity userAuth = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<VendaEntity> vendas = repository.findByUser(userAuth);

		return vendas;
	}

	public List<VendaEntity> findByCliente(UUID idCliente) {
		PessoaEntity cliente = pessoaRepository.findById(idCliente)
				.orElseThrow(() -> new RuntimeException("Cliente nao localizado"));
		UserEntity userAuth = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<VendaEntity> vendas = repository.findByClienteAndUser(cliente, userAuth);
				
		return vendas;
	}
}
