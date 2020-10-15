package com.daou.ssjd;

import com.daou.ssjd.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@EnableJpaAuditing
@SpringBootApplication
public class SsjdApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SsjdApplication.class, args);
	}

	private final JwtInterceptor jwtInterceptor;

	public SsjdApplication(JwtInterceptor jwtInterceptor) { this.jwtInterceptor = jwtInterceptor; }

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/**")
				.excludePathPatterns("/api/users")
				.excludePathPatterns("/api/users/login")
				.excludePathPatterns("/api/users/salt/**")
				.excludePathPatterns(Arrays.asList("/api/posts/**"))
				.excludePathPatterns(Arrays.asList("/api/chats/**"));
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("jwt-auth-token");
	}
}
