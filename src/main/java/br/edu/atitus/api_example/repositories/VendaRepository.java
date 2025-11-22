package br.edu.atitus.api_example.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.atitus.api_example.entities.UserEntity;
import br.edu.atitus.api_example.entities.VendaEntity;
import br.edu.atitus.api_example.entities.PessoaEntity;


public interface VendaRepository extends JpaRepository<VendaEntity, UUID> {
	
	List<VendaEntity> findByUser(UserEntity user);
	
	List<VendaEntity> findByClienteAndUser(PessoaEntity cliente, UserEntity user);
}
