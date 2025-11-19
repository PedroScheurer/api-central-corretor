package br.edu.atitus.api_example.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.atitus.api_example.entities.PessoaEntity;
import br.edu.atitus.api_example.entities.UserEntity;

public interface PessoaRepository extends JpaRepository<PessoaEntity, UUID> {

	List<PessoaEntity> findByUser(UserEntity user);
	
	List<PessoaEntity> findByNomeContainsIgnoreCaseAndUser(String nome, UserEntity user);
}
