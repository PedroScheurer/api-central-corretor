package br.edu.atitus.api_example.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.atitus.api_example.entities.UserEntity;
import br.edu.atitus.api_example.repositories.UserRepository;

// implementa metodos CRUD
@Service
public class UserService implements UserDetailsService {

	private final UserRepository repository;
	private final PasswordEncoder enconder;

	public UserService(UserRepository repository, PasswordEncoder enconder) {
		super();
		this.repository = repository;
		this.enconder = enconder;
	}

	public UserEntity save(UserEntity user) throws Exception {
		if (user == null) {
			throw new Exception("Objeto nulo");
		}
		if (user.getName() == null || user.getName().isEmpty())
			throw new Exception("Nome inválido");
		user.setName(user.getName().trim());

		if (user.getEmail() == null || user.getEmail().isEmpty() || !user.getEmail().contains("@"))
			throw new Exception("E-mail inválido");
		user.setEmail(user.getEmail().trim());

		if (user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().length() < 8)
			throw new Exception("Password inválido");
		user.setPassword(enconder.encode(user.getPassword()));

		// TODO validar permissão cadastro Admins
		
		if(repository.existsByEmail(user.getEmail())) {
			throw new Exception("Já existe um usuário cadastrado com este e-mail");
		}
		
		repository.save(user);
		
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		var user = repository.findByEmail(email)
				.orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado com este email"));
		return user;
	}
}
