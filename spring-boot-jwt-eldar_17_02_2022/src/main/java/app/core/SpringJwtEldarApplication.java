package app.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import app.core.part3.util.JwtUtil;
import app.core.part3.util.JwtUtil.ClientDetails;
import app.core.part3.util.JwtUtil.ClientDetails.ClientType;

@SpringBootApplication
public class SpringJwtEldarApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(SpringJwtEldarApplication.class, args);

		JwtUtil util = ctx.getBean(JwtUtil.class);
		// generate JWS
		ClientDetails clientDetails = new ClientDetails("aaa@mail", ClientType.COMPANY, 102);
		String token = util.generateToken(clientDetails);
		System.out.println(token);

		// extract data:
		System.out.println("token expired? " + util.isTokenExpired(token));
		System.out.println("email: " + util.extractSubject(token));
		System.out.println("token expiration" + util.extractExpiration(token));
		System.out.println(util.extractClient(token));
		System.out.println("=====================");

	}

}
