package hh.sof03.Climbingroutes;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				authorize -> authorize
				.requestMatchers(antMatcher("/css/**")).permitAll()
				.requestMatchers(antMatcher("/climbingroutes/**")).permitAll()
				.requestMatchers(antMatcher("/disciplinelist/**")).permitAll()
				.requestMatchers(antMatcher("/routesetterlist/**")).permitAll()
				
				.requestMatchers(antMatcher("/climbinglogs/**")).permitAll()
				.requestMatchers(antMatcher("/routes/**")).permitAll()
				.requestMatchers(antMatcher("/routesetters/**")).permitAll()
				.requestMatchers(antMatcher("/disciplines/**")).permitAll()
				
				.anyRequest().authenticated())
				.formLogin(formlogin -> formlogin.loginPage("/login").defaultSuccessUrl("/climbingroutes", true).permitAll())
				.logout(logout -> logout.permitAll());
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

}