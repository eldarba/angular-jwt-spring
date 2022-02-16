package app.core.part2.jwt;

import java.security.Key;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Demo1 {

	public static void main(String[] args) {

		// creating the key using the same secret
		String secret = "aaaaaaaaa1aaaaaaaaa2aaaaaaaaa3aaaaaaaaa4aaa";
		// 1. the decoded secret key
		byte[] secretKeyDecoded = Base64.getDecoder().decode(secret.getBytes());
		// 2. the desired algorithm for generating key
		String algorithm = SignatureAlgorithm.HS256.getJcaName();
		// 3. the key
		Key key = new SecretKeySpec(secretKeyDecoded, algorithm);

		// you need to put a valid JWS here:
		String jws = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDEiLCJpYXQiOjE2NDUwNDIzMTYsImV4cCI6MTY0NTA0NDExNiwiY2xpZW50LXR5cGUiOiJjb21wYW55IiwiZW1haWwiOiJhYWFAbWFpbC5jb20ifQ.jUD1CgeJZzomRZILzz7A1ECYrTDTyydU0psco7Zozw8";
		JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
		Jws<Claims> jwt = jwtParser.parseClaimsJws(jws);
//		System.out.println(jwt);
		System.out.println(jwt.getHeader());
		System.out.println(jwt.getBody());
		System.out.println(jwt.getSignature());

	}

}
