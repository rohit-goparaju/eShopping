package com.projects.eShopping.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	private final SecretKey key;
	private final long expiration = 1 * 60 * 60 * 1000; // 1 Hour
	
	public JWTService(@Value("${jwt.secret}") String secret) {
		super();
		this.key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secret));
	}
	
	public String generateToken(String username) {
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(key)
				.compact();
	}
	
	public String extractUsername(String token) {
		try {			
			return Jwts.parser()
					.verifyWith(key)
					.build()
					.parseSignedClaims(token)
					.getPayload()
					.getSubject();
		}catch(Exception e) {
			return null;
		}
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser()
			.verifyWith(key)
			.build()
			.parseSignedClaims(token);
			
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
}
