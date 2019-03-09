package com.digital.config;
/**
 * @author Satyam Kumar
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("javainuse").password("javainuse").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.authorizeRequests().antMatchers("/api/v0/build-info").permitAll();
		// http.authorizeRequests().antMatchers("/v2/api-docs", "/swagger-ui.html",
		// "/webjars/**", "/swagger-resources/**")
		// .permitAll();

		http.authorizeRequests().anyRequest().authenticated();
		http.httpBasic().authenticationEntryPoint(authenticationEntryPoint);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		http.csrf().disable();
	}
}
