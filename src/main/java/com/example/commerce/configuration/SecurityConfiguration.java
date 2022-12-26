package com.example.commerce.configuration;

import com.example.commerce.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

	private final MemberService memberService;
	@Autowired
	private UserAuthenticationSuccessHandler userAuthenticationSuccessHandler;
	@Autowired
	private UserAuthenticationFailureHandler userAuthenticationFailureHandler;
	AuthenticationManager authenticationManager;


	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
		auth.userDetailsService(memberService).passwordEncoder(getPasswordEncoder());
		authenticationManager = auth.build();

		http.csrf().disable().headers().frameOptions().sameOrigin()
			.and()
			.authorizeRequests().antMatchers("/", "/member/register").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and().authenticationManager(authenticationManager)
			.formLogin().loginPage("/member/login").usernameParameter("email").passwordParameter("password")
			.successHandler(userAuthenticationSuccessHandler).failureHandler(
				userAuthenticationFailureHandler) .permitAll()
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
			.logoutSuccessUrl("/").invalidateHttpSession(true);

		return http.build();
	}




}
