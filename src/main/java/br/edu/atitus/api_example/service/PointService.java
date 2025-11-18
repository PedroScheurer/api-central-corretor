package br.edu.atitus.api_example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.atitus.api_example.entities.PointEntity;
import br.edu.atitus.api_example.repositories.PointRepository;
import jakarta.transaction.Transactional;

@Service
public class PointService {
	private final PointRepository repository;

	public PointService(PointRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Transactional
	public PointEntity save(PointEntity point) throws Exception {
		if(point == null) {
			throw new Exception("Objeto nulo");
		}
		
		if(point.getLongitude() == null || !(point.getLongitude() >= -180 && point.getLongitude() <= 180)) {
			throw new Exception("Longitude invalida");
		}
			
		if(point.getLatitude() == null || !(point.getLatitude() >= -90 && point.getLatitude() <= 90)) {
			throw new Exception("Latitude invalida");
		}
		
		if(point.getDescription() == null || point.getDescription().isEmpty()) {
			throw new Exception("Descricao invalida");
		}
		
		return repository.save(point);
	}

	
	public List<PointEntity> findAll(){
		return repository.findAll();
	}
}
