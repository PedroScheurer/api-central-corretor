package br.edu.atitus.api_example.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.atitus.api_example.entities.ImovelEntity;
import br.edu.atitus.api_example.entities.UserEntity;

public interface ImovelRepository extends JpaRepository<ImovelEntity, UUID> {

	List<ImovelEntity> findByUser(UserEntity user);

	List<ImovelEntity> findByNomeContainsIgnoreCaseAndUser(String nome, UserEntity user);

}
