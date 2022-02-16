package app.core.part1.jws;

import java.security.Key;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.SignatureAlgorithm;

public class JwtDemo4CreatingKey {

	public static void main(String[] args) {

		String secret = "aaaaaaaaa1aaaaaaaaa2aaaaaaaaa3aaaaaaaaa4aaa";

		// 1. the decoded secret key
		byte[] secretKeyDecoded = Base64.getDecoder().decode(secret.getBytes());

		// 2. the desired algorithm for generating key
		String algorithm = SignatureAlgorithm.HS256.getJcaName();

		// 3. the key
		Key key = new SecretKeySpec(secretKeyDecoded, algorithm);

		System.out.println(key);

	}

}
