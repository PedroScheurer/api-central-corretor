package br.edu.atitus.api_example.utils;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.edu.atitus.api_example.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

	private final UserService userService;

	public AuthTokenFilter(UserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Verifico se existe token jwt no cabeçalho da requisição
		String jwt = JwtUtil.getJwtFromRequest(request);
		// Verifico se o token é valido
		if (jwt != null) {
			// Pego o email do token e verificar se existe no BD
			String email = JwtUtil.validateToken(jwt);
			if (email != null) {
				var user = userService.loadUserByUsername(email);
				if (user != null) {
					// TODO autenticar a requisicao
					var auth = new UsernamePasswordAuthenticationToken(user, null,null);
					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}
		}

		filterChain.doFilter(request, response);
	}

}
