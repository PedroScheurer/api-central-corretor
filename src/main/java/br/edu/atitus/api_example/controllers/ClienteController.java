package br.edu.atitus.api_example.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.api_example.dtos.PessoaDTO;
import br.edu.atitus.api_example.entities.PessoaEntity;
import br.edu.atitus.api_example.service.PessoaService;

@RestController
@RequestMapping(path = "/ws/clientes")
public class ClienteController {

	private final PessoaService service;

	public ClienteController(PessoaService service) {
		super();
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<PessoaEntity>> findAll() {
		var lista = service.findAll();
		return ResponseEntity.ok(lista);
	}

	@GetMapping(params = "nome")
	public ResponseEntity<List<PessoaEntity>> findByName(@RequestParam String nome) {
		var lista = service.findByName(nome);
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	public ResponseEntity<PessoaEntity> save(@RequestBody PessoaDTO dto) throws Exception {
		PessoaEntity cliente = new PessoaEntity();
		BeanUtils.copyProperties(dto, cliente);
		service.save(cliente);
		return ResponseEntity.status(201).body(cliente);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable UUID id) throws Exception {
		service.deleteById(id);
		return ResponseEntity.ok("Cliente deletado");
	}
}
