package com.example.week03_homework.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
public class WebSecurityConfig {

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring() // post막아주는 친구인데 밑에서 모든 api를 일단 열어주었다.
				.antMatchers("/**");
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((authz) -> authz
						.antMatchers("/**").permitAll()
						// 어떤 요청이든 '인증'
						.anyRequest().authenticated()
				)
				// 로그인 기능 허용
				.formLogin()
				.defaultSuccessUrl("/")
				.permitAll()
				.and()
				//로그아웃 기능 허용
				.logout()
				.permitAll();

		return http.build();
	}
}
