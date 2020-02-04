package com.bridgeLabz.fundoonotes.utility;

import java.time.Instant;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class Jwt
{
private static final String SECRET_KEY = "secret";
	
	public String createToken(long l)
	{
		Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
	
		return JWT.create()
				.withClaim("email",l)
				.sign(algorithm);
	}
	
	public long parseJwtToken(String token)
	{
		Claim claim = JWT.require(Algorithm.HMAC256(SECRET_KEY))
				.build()
				.verify(token)
				.getClaim("email");
		return claim.asLong();
	}
	
	/*
	 * public String parse(String token) { boolean isValid = false; String email =
	 * null; if (token != null) { Claims claims =
	 * Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	 * email = claims.getSubject(); isValid =
	 * claims.getExpiration().after(Date.from(Instant.now())); } if (isValid) {
	 * return email; } else { return null; } }
	 */
}
