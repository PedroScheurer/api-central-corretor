package br.edu.atitus.api_example.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.api_example.dtos.ImovelDTO;
import br.edu.atitus.api_example.entities.ImovelEntity;
import br.edu.atitus.api_example.service.ImovelService;

@RestController
@RequestMapping("/ws/imovel")
public class ImovelController {

	private final ImovelService service;

	public ImovelController(ImovelService service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<ImovelEntity>> findAll(){
		var lista = service.findAll();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(params = "nome")
	public ResponseEntity<List<ImovelEntity>> findByName(@RequestParam String nome){
		var lista = service.findByName(nome);
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	public ResponseEntity<ImovelEntity> save(@RequestBody ImovelDTO dto) throws Exception{
		ImovelEntity imovel = service.fromDTO(dto);
		service.save(imovel);
		return ResponseEntity.status(201).body(imovel);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete (@PathVariable UUID id) throws Exception{
		service.deleteById(id);
		return ResponseEntity.ok("Ponto deletado");
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> exceptionHandler(Exception e) {
		String message = e.getMessage().replaceAll("\r\n", "");
		return ResponseEntity.status(400).body(message);
	}
	
}
