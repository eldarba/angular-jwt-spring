package app.core.part1.jws;

import java.util.Arrays;
import java.util.Base64;

public class Demo2CreatingSecretKey {

	public static void main(String[] args) {

		// set secret key as string (as long as needed for the key algorithm)
		// 43 encoded bytes will give us 32 decoded bytes (256 bits) in the next step
		String secretKey = "aaaaaaaaa1aaaaaaaaa2aaaaaaaaa3aaaaaaaaa4aaa";
		// get the encoded bytes of the secret key
		byte[] secretKeyEncoded = secretKey.getBytes();
		System.out.println(Arrays.toString(secretKeyEncoded));

		// decode the secret key so it is platform neutral.
		// the decoded key must be at least 256 bits long four the key algorithm we are
		// going to use.
		byte[] secretKeyDecoded = Base64.getDecoder().decode(secretKeyEncoded);
		System.out.println(Arrays.toString(secretKeyDecoded));
		System.out.println(secretKeyDecoded.length + " bytes are " + (secretKeyDecoded.length * 8) + " bits");

		System.out.println(new String(secretKeyDecoded));

	}

}
