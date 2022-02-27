package hu.oktatas.transport.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import hu.oktatas.transport.security.JwtAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	JwtAuthFilter jwtAuthFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
//		auth.inMemoryAuthentication()
//				.passwordEncoder(passwordEncoder())
//				.withUser("addMan").authorities("AddressManager").password(passwordEncoder().encode("pass"))
//				.and()
//				.withUser("tranMan").authorities("TransportManager").password(passwordEncoder().encode("pass"));

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http./*httpBasic().and().*/csrf().disable().authorizeRequests().antMatchers("/api/transportPlans/**")
				.hasAuthority("TransportManager").antMatchers(HttpMethod.POST, "/api/addresses/search**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/addresses/**").hasAuthority("AddressManager")
				.antMatchers(HttpMethod.PUT, "/api/addresses/**").hasAuthority("AddressManager")
				.antMatchers(HttpMethod.DELETE, "/api/addresses/**").hasAuthority("AddressManager");
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return daoAuthenticationProvider;
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
