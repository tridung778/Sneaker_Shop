package com.example.Java6_ASM;

import com.example.Java6_ASM.models.Account;
import com.example.Java6_ASM.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.NoSuchElementException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private AccountService accountService;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> {
			try {
				Account user = accountService.findByUsername(username);
				String pass = pe.encode(user.getPassword());
//				String[] role = user.getAuthorities().stream().map(er -> er.getRole().getId())
//						.collect(Collectors.toList()).toArray(new String[0]);
				return User.withUsername(username).password(pass).roles("DIRE").build();
//				return User.withUsername(username).password(pass).roles(role).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username + "Not Found");
			}
		});
	}

//	public void loginFromOauth2(OAuth2AuthenticationToken oauth2) {
//		String email = oauth2.getPrincipal().getAttribute("email");
//		String pass = Long.toHexString(System.currentTimeMillis());
//
//		UserDetails user = User.withUsername(email).password(pe.encode(pass)).roles("GUEST").build();
//		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null,
//				user.getAuthorities());
//		SecurityContextHolder.getContext().setAuthentication(auth);
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable();

		http.authorizeRequests().antMatchers("/order/**").authenticated().antMatchers("/admin/**")
				.hasAnyRole("STAF", "DIRE").antMatchers("/rest/authorities").hasRole("DIRE").anyRequest().permitAll();

		http.formLogin().loginPage("/security/login/form").loginProcessingUrl("/security/login")
				.defaultSuccessUrl("/security/login/success", false).failureUrl("/security/login/error");

		http.rememberMe().tokenValiditySeconds(86400);

		http.exceptionHandling().accessDeniedPage("/security/unauthoried");
		http.logout().logoutUrl("/security/logoff").logoutSuccessUrl("/security/logoff/success");

//		http.oauth2Login().loginPage("/security/login/form").defaultSuccessUrl("/oauth2/login/success", true)
//				.failureUrl("/security/login/error").authorizationEndpoint().baseUri("/oauth2/authorization");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS);
	}
}
