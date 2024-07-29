package com.example.Java6_ASM;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder().encode("123")).roles("GUEST").and()
				.withUser("user2").password(passwordEncoder().encode("123")).roles("USER", "GUEST").and()
				.withUser("user3").password(passwordEncoder().encode("123")).roles("ADMIN", "USER", "GUEST");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable() // Consider adding CORS configuration if needed
				.authorizeRequests().anyRequest().permitAll().and().exceptionHandling()
				.accessDeniedPage("/auth/access/denied").and().formLogin().loginPage("/auth/login/form")
				.loginProcessingUrl("/auth/login").defaultSuccessUrl("/auth/login/success", false)
				.failureUrl("/auth/login/error").usernameParameter("username").passwordParameter("password").and()
				.rememberMe().rememberMeParameter("remember").and().logout().logoutUrl("/auth/logoff")
				.logoutSuccessUrl("/auth/logoff/success");
	}
}
