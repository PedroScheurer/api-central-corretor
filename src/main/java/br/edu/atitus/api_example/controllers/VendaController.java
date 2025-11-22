package br.edu.atitus.api_example.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.api_example.dtos.VendaDTO;
import br.edu.atitus.api_example.entities.VendaEntity;
import br.edu.atitus.api_example.service.VendaService;

@RestController
@RequestMapping(path = "/ws/vendas")
public class VendaController {

	private final VendaService service;

	public VendaController(VendaService service) {
		super();
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<VendaEntity>> findAll() {
		List<VendaEntity> vendas = service.findAll();
		return ResponseEntity.ok(vendas);
	}
	
	@GetMapping("/cliente/{idCliente}")
	public ResponseEntity<List<VendaEntity>> findByCliente(@PathVariable UUID idCliente){
		List<VendaEntity> vendas = service.findByCliente(idCliente);
		return ResponseEntity.ok(vendas);
	}

	@PostMapping
	public ResponseEntity<VendaEntity> save(@RequestBody VendaDTO dto) {
		VendaEntity venda = service.fromDTO(dto);
		service.save(venda);
		return ResponseEntity.status(201).body(venda);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable UUID id) throws Exception {
		service.deleteById(id);
		return ResponseEntity.ok("Venda deletada");
	}
}
