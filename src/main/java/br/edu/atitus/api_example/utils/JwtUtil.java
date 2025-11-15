package br.edu.atitus.api_example.utils;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

public class JwtUtil {

	private static final String SECRET_KEY = "chaveSuperSecretaParaJWTdeExemplo!@12345678901234567890";
	private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

	private static SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}

	public static String generateToken(String email) {
		return Jwts.builder().subject(email).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(getSecretKey()).compact();
	}

	public static String getJwtFromRequest(HttpServletRequest request) {
		String jwt = request.getHeader("Authorization");
		if (jwt == null || jwt.isEmpty()) {
			jwt = request.getHeader("authorization");
		}

		if (jwt != null && !jwt.isEmpty()) {
			return jwt.substring(7);
		}

		return null;
	}

	public static String validateToken(String token) {
		try {
			return Jwts.parser()
					.verifyWith(getSecretKey())
					.build()
					.parseSignedClaims(token)
					.getPayload().getSubject();
		} catch(Exception e) {
			return null;
		}

	}

}
