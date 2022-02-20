package app.core.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import app.core.part3.util.JwtUtil;
import app.core.part3.util.JwtUtil.ClientDetails;

public class LoginFilter implements Filter {

	private JwtUtil jwtUtil;

	public LoginFilter(JwtUtil jwtUtil) {
		super();
		this.jwtUtil = jwtUtil;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String token = req.getHeader("token");
		System.out.println("===== FILTER: " + token);
		try {
			ClientDetails clientDetails = jwtUtil.extractClient(token);
			System.out.println("===== FILTER: " + clientDetails);
			chain.doFilter(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(HttpStatus.UNAUTHORIZED.value(), "not logged in");
		}

	}

}
