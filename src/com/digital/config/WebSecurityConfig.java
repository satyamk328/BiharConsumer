package com.digital.config;
/**
 * @author Satyam Kumar
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${ui.username}")
	private String username;

	@Value("${ui.password}")
	private String password;

	@Autowired
	private HttpAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(username).password(password).authorities("ROLE_USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/api/v0/**").permitAll();
		http.authorizeRequests().antMatchers("/v2/api-docs", "/swagger-ui.html", "/webjars/**", "/swagger-resources/**")
				.permitAll();
		http.authorizeRequests().antMatchers("/images/**","/configuration/**").permitAll();
		http.headers().frameOptions().deny();
		http.authorizeRequests().anyRequest().authenticated();
		http.httpBasic().authenticationEntryPoint(authenticationEntryPoint);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		http.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
}
