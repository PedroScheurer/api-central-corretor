package br.edu.atitus.api_example.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.atitus.api_example.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
	
	//select * from
	boolean existsByEmail(String email);
	
	boolean existsByEmailAndName(String email, String name);
	
	Optional<UserEntity> findByEmail(String email);
}
