package app.core.part1.jws;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtDemo5SignedJws {

	public static void main(String[] args) {

		String secret = "aaaaaaaaa1aaaaaaaaa2aaaaaaaaa3aaaaaaaaa4aaa";
		System.out.println("the secret:");
		System.out.println(secret);

		// 1. the decoded secret key
		byte[] secretKeyDecoded = Base64.getDecoder().decode(secret.getBytes());

		// 2. the desired algorithm for generating key
		String algorithm = SignatureAlgorithm.HS256.getJcaName();

		// 3. the key
		Key key = new SecretKeySpec(secretKeyDecoded, algorithm);

		Instant now = Instant.now();
		Instant expiration = now.plus(30, ChronoUnit.MINUTES);

		String token = Jwts.builder()

				.signWith(key)

				.setSubject("aaa@mail.com")

				.setIssuedAt(Date.from(now))

				.setExpiration(Date.from(expiration))

				.claim("client-type", "company")

				.claim("client-id", "101")

				.compact();

		System.out.println("the JWS:");
		System.out.println(token);

	}

}
