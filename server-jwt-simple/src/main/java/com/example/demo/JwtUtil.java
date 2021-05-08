package com.example.demo;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.spec.SecretKeySpec;

//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.JwtUtil.UserDetails.UserType;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	private String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();
	private String encodedSecretKey = "this+is+my+key+and+it+must+be+at+least+256+bits+long";
	private Key decodedSecretKey = new SecretKeySpec(Base64.getDecoder().decode(encodedSecretKey),
			this.signatureAlgorithm);

	public static void main(String[] args) {
		JwtUtil u = new JwtUtil();
		UserDetails userDetails = new UserDetails("1245", "aaa@mail", UserType.COMPANY);
		String t = u.generateToken(userDetails);
		System.out.println(t);
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("userId", userDetails.id);
		claims.put("userType", userDetails.userType);
		return createToken(claims, userDetails.email);
	}

	public String createToken(Map<String, Object> claims, String subject) {
		Instant now = Instant.now();
		return Jwts.builder().setClaims(claims)

				.setSubject(subject)

				.setIssuedAt(Date.from(now))

				.setExpiration(Date.from(now.plus(10, ChronoUnit.HOURS))).signWith(this.decodedSecretKey)

				.signWith(decodedSecretKey)

				.compact();
	}

	// ==========================
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(encodedSecretKey).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

//	public Boolean validateToken(String token, UserDetails userDetails) {
//		final String username = extractUsername(token);
//		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//	}

	// ===============================
	public static class UserDetails {
		public String id;
		public String email;
		public UserType userType;

		public UserDetails(String id, String email, UserType userType) {
			super();
			this.id = id;
			this.email = email;
			this.userType = userType;
		}

		public enum UserType {
			ADMIN, COMPANY, CUSTOMER
		}

	}
}
