package com.example.demo;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
//		SpringApplication.run(ServerApplication.class, args);

		String secret = "111111111A111111111A111111111A111111111AA111111111A";
		byte[] key = Base64.getDecoder().decode(secret);
		System.out.println(Arrays.toString(key));
		String algorithm = SignatureAlgorithm.HS256.getJcaName();
		System.out.println(algorithm); // HmacSHA256
		Key hmacKey = new SecretKeySpec(key, algorithm);
		System.out.println(hmacKey.getAlgorithm());
		System.out.println(hmacKey.getFormat());
		System.out.println(Arrays.toString(hmacKey.getEncoded()));

		Instant now = Instant.now();

		String token = Jwts.builder()

				.claim("name", "eldar")

				.setSubject("eldar")

				.setId("xxxTheIdxxx")

				.setIssuedAt(Date.from(now))

				.setExpiration(Date.from(now.plus(5, ChronoUnit.MINUTES)))

				.signWith(hmacKey)

				.compact();
		System.out.println(token);
		System.out.println(now.getEpochSecond());

		// parse / validate
		Jws<Claims> expandedJwt = Jwts.parserBuilder()

				.setSigningKey(hmacKey)

				.build()

				.parseClaimsJws(token);

		System.out.println("=======================");
		System.out.println(expandedJwt);
	}

}
