package com.example.demo;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Demo2 {

	public static void main(String[] args) {

		// define signature algorithm
		String algorithm = "HmacSHA256AA";
		algorithm = SignatureAlgorithm.HS256.getJcaName();

		// set the encoded secret key
		byte[] secretKeyEncoded = "this+is+my+key+and+it+must+be+at+least+256+bits+long".getBytes();
		// decode the key to base 64 so it is platform neutral
		byte[] secretKeyDecoded = Base64.getDecoder().decode(secretKeyEncoded);
		// preparing the key for the token signature (for authentication)
		// SecretKeySpec - used to construct a SecretKey from a byte array
		Key key = new SecretKeySpec(secretKeyDecoded, algorithm);
		// set an instant to
		Instant now = Instant.now();

		// create a JWT builder
		String token = Jwts.builder()

				.signWith(key)

				.setIssuedAt(Date.from(now))

				.setExpiration(Date.from(now.plus(3, ChronoUnit.HOURS)))

				.setId("101")

				.setSubject("email@mai")

				.claim("clientType", "admin")

				.claim("clientPassword", "xyzPass")

				////////////////////////////////

				.claim("email", "admin@mail")

				.claim("address", "Tel Aviv")

				.compact();
		System.out.println(token);

		// ===============================================================
		System.out.println("\npars: =======================");
		// parse / validate
		JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(key).build();

		Jws<Claims> expandedJwt = jwtParser.parseClaimsJws(token);

		System.out.println(expandedJwt);

		System.out.println("header");
		System.out.println(expandedJwt.getHeader());
		System.out.println("body");
		System.out.println(expandedJwt.getBody());
		System.out.println("signature");
		System.out.println(expandedJwt.getSignature());

	}

}
