package br.edu.atitus.api_example.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.atitus.api_example.entities.ImovelEntity;

public interface ImovelRepository extends JpaRepository<ImovelEntity, UUID> {

	

}
