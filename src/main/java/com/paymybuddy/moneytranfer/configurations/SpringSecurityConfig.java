package com.paymybuddy.moneytranfer.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.paymybuddy.moneytranfer.servicesImpl.UserProfilDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserProfilDetailsServiceImpl userProfilDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userProfilDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
       .authorizeRequests()
       .antMatchers("/").permitAll()
       .antMatchers("/login**").permitAll()
       .antMatchers("/perform_login").permitAll()
       .antMatchers("/register").permitAll()
       .antMatchers("/admin**").hasRole("Admin")
       .antMatchers("/user**").hasAnyRole("Admin", "User")
       .and().csrf().disable()
       .httpBasic()
       .and()
       .formLogin()
       .loginPage("/login")
       .loginProcessingUrl("/perform_login")
       .usernameParameter("email")
       .passwordParameter("password")
       .defaultSuccessUrl("/user/home", true)
       .failureUrl("/login?error=true")
       .and()
       .logout()
       .logoutSuccessUrl("/login?logout=true")
       .and()
       .exceptionHandling().accessDeniedPage("/403");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**",
		// "/images/**");
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
