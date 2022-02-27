package hu.oktatas.transport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.passwordEncoder(passwordEncoder())
				.withUser("addMan").authorities("AddressManager").password(passwordEncoder().encode("pass"))
				.and()
				.withUser("tranMan").authorities("TransportManager").password(passwordEncoder().encode("pass"));

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http	.httpBasic()
				.and()
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/api/transportPlans/**").hasAuthority("TransportManager")
				.antMatchers(HttpMethod.POST, "/api/addresses/search**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/addresses/**").hasAuthority("AddressManager")
				.antMatchers(HttpMethod.PUT, "/api/addresses/**").hasAuthority("AddressManager")
				.antMatchers(HttpMethod.DELETE, "/api/addresses/**").hasAuthority("AddressManager");
	}
	
	

}
