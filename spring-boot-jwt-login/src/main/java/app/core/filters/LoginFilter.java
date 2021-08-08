package app.core.filters;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import app.core.services.JwtUtil;
import io.jsonwebtoken.JwtException;

@Service
public class LoginFilter implements Filter {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println(">>> FILTER >>> filter started");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String token = req.getHeader("token");

		if (token != null) {
			System.out.println(">>> FILTER >>> yoohoo we found a token. lets check that it is valid...");
			try {
				Date exp = jwtUtil.extractExpiration(token);
				if (exp.before(new Date())) {
					resp.setHeader("Access-Control-Allow-Origin", "http://localhost:5500/*");
					resp.sendError(HttpStatus.UNAUTHORIZED.value(), "token expired");
					System.out.println(">>> FILTER >>> token expired");
					System.out.println();
					return;
				}
			} catch (JwtException e) {
				resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
				resp.sendError(HttpStatus.UNAUTHORIZED.value(), "invalid token: " + e.getMessage());
				System.out.println(">>> FILTER >>> invalid token");
				System.out.println();
				return;
			}

			System.out.println(">>> FILTER >>> YES token is valid. lets proceed to the endpoint");
			chain.doFilter(request, response);
			System.out.println(">>> FILTER >>> the endpoint returned a response - filter is done");
			System.out.println();
			return;
		}

		// if we are here - no token
		// check for pre-flight (comes without a token)
		System.out.println(">>> FILTER >>> no token - lets check if this is a pre-flight request");
		if (req.getMethod().equalsIgnoreCase("OPTIONS")) {
			System.out.println(">>> FILTER >>> YES - this is pre-fligt. lets proceed to the endpoint");
			chain.doFilter(request, response);
			System.out.println(">>> FILTER >>> the endpoint returned a response - filter is done");
			System.out.println();
			return;
		} else {
			resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
			resp.sendError(HttpStatus.UNAUTHORIZED.value(), "no token");
			System.out.println(">>> FILTER >>> NOT a preflight - you don't have a token");
			System.out.println();
		}

	}

}
