package com.niraaz.roomfinder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/register", "/", "/about", "/login", "/css/**", "/webjars/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin().loginPage( "/login" ).permitAll()
				.defaultSuccessUrl( "/roomList" )
				.and()
				.logout()
				.logoutSuccessUrl( "/login" );
	}

	@Override
	protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
		auth.jdbcAuthentication().dataSource( dataSource )
				.usersByUsernameQuery( "select user_name as principal, password as credentials, true from user where user_name=?" )
				.authoritiesByUsernameQuery( "select u.user_name, r.name from user u, role r, user_role ur where u.id=ur.user_id and r.name=ur.role_name and u.user_name=?" )
				.passwordEncoder(passwordEncoder()).rolePrefix( "ROLE_" );
	}

	@Bean
	public PasswordEncoder passwordEncoder( ) {
		return new BCryptPasswordEncoder(  );
	}
}
