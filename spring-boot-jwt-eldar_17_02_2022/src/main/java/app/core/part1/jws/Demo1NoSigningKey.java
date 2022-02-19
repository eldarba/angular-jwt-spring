package app.core.part1.jws;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import io.jsonwebtoken.Jwts;

public class Demo1NoSigningKey {

	public static void main(String[] args) {

		// Instant: This class models a single instantaneous point on the time-line.
		Instant now = Instant.now();
		Instant expiration = now.plus(30, ChronoUnit.SECONDS);

		String token = Jwts.builder()

				.setSubject("aaa@mail.com")

				.setIssuedAt(Date.from(now))

				.setExpiration(Date.from(expiration))

				.claim("client-type", "company")

				.claim("client-id", "101")

				.compact(); // returns a compact URL-safe JWT string.

		System.out.println(token);

	}

}
