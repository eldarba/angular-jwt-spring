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

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String token = req.getHeader("token");

		try {
			Date exp = jwtUtil.extractExpiration(token);
			if (exp.before(new Date())) {
				// add cors headers for browser user agent
				resp.sendError(HttpStatus.UNAUTHORIZED.value(), "token expired");
				return;
			}
		} catch (JwtException e) {
			// add cors headers for browser user agent
			resp.sendError(HttpStatus.UNAUTHORIZED.value(), "invalid token");
			return;
		}

		chain.doFilter(request, response);

	}

}
