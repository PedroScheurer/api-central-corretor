package br.edu.atitus.api_example.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfigSecurity {

	@Bean
	SecurityFilterChain getSecurityFilter(HttpSecurity http) throws Exception {

		http.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //desabilita seções
				.csrf(csrf -> csrf.disable()) //desabilita protecao CSRF
				.authorizeHttpRequests(auth -> auth.requestMatchers("/ws**", "/ws/**").authenticated()
						.anyRequest().permitAll());

		return http.build();
	}

	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
