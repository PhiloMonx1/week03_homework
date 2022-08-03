package com.example.week03_homework.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig{

	@Bean
	public BCryptPasswordEncoder encodePassword() {
		return new BCryptPasswordEncoder(); // BCryptPasswordEncoder는 PasswordEncoder를 구현함 (인플리먼츠)
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring() // (이건 csrf 뿐만 아니라 FramOptions를 무시해준다.)
				.antMatchers("/h2-console/**")
				.antMatchers("/api/user/**"); // 이상하게 csrf를 열어도 포스트맨에서 회원가입이 안된다 왜일까?
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.csrf().ignoringAntMatchers("/api/user/**"); // csrf는 포스트를 막는다. 그래서 유저 url은 일단 풀어주자
		http.csrf().disable();

		http
				.authorizeHttpRequests((authz) -> authz
						.antMatchers("/**").permitAll()
						// 어떤 요청이든 '인증'
						.anyRequest().authenticated()
				)
				// 로그인 기능 허용
				.formLogin()
				.loginProcessingUrl("/api/user/signup")
				.permitAll()
				.and()
				//로그아웃 기능 허용
				.logout()
				.permitAll();

		return http.build();
	}
}
