package app.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import app.core.filters.LoginFilter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringBootJwtLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJwtLoginApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<LoginFilter> filterRegistration(LoginFilter loginFilter) {
		FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(loginFilter);
		filterRegistrationBean.addUrlPatterns("/api/*");
		return filterRegistrationBean;
	}

}
