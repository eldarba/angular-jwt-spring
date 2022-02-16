package app.core.part1.jws;

import io.jsonwebtoken.SignatureAlgorithm;

public class JwtDemo3SignatureAlgorithm {

	public static void main(String[] args) {

		/*
		 * JCA - Java Cryptography Architecture
		 * 
		 * a Java framework for working with cryptography
		 */

		/*
		 * MAC – message authentication code : a short piece of information used to
		 * authenticate a message
		 * 
		 * HMAC – hash-based message authentication code: is a specific type of message
		 * authentication code (MAC) involving a cryptographic hash function and a
		 * secret cryptographic key.
		 */

		/*
		 * JSON Web Algorithms (JWA)
		 * 
		 * The JSON Web Algorithms (JWA) specification enumerates cryptographic
		 * algorithms and identifiers to be used with the JSON Web Signature (JWS), JSON
		 * Web Encryption (JWE), and JSON Web Key (JWK) specifications.
		 */

		/*
		 * SHA - Secure Hash Algorithms
		 * 
		 * The Secure Hash Algorithms are a family of cryptographic hash functions
		 * published by the National Institute of Standards and Technology (NIST) as a
		 * U.S. Federal Information Processing Standard
		 * 
		 * https://en.wikipedia.org/wiki/Secure_Hash_Algorithms
		 */

		/*
		 * SignatureAlgorithm
		 * 
		 * enum for standard JWT signature algorithm names as defined in the JSON Web
		 * Algorithms (JWA) specification.
		 */
		System.out.println(SignatureAlgorithm.HS256);
		System.out.println("value: " + SignatureAlgorithm.HS256.getValue());
		System.out.println("description: " + SignatureAlgorithm.HS256.getDescription());
		System.out.println("family name: " + SignatureAlgorithm.HS256.getFamilyName());
		System.out.println("JCA name: " + SignatureAlgorithm.HS256.getJcaName());
		System.out.println("JDK standard?: " + SignatureAlgorithm.HS256.isJdkStandard());
		System.out.println("MinKeyLength: " + SignatureAlgorithm.HS256.getMinKeyLength());

	}

}
