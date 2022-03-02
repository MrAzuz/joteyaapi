/*
 * package com.joteya.config;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder;
 * 
 * import com.joteya.service.AuthenticationService;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class SecurityConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Autowired private AuthenticationService authenticationService;
 * 
 * 
 * 
 * @Bean public PasswordEncoder passwordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * 
 * 
 * 
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * http.authorizeRequests().antMatchers("/joteya/**").hasAnyRole("1",
 * "0").and().authorizeRequests() .antMatchers("/console/**").permitAll()// pour
 * continuer à avoir accès à la console de la bdd .and().httpBasic();
 * http.csrf().disable().cors().disable();
 * http.headers().frameOptions().disable();
 * 
 * }
 * 
 * @Override protected void configure(final AuthenticationManagerBuilder auth)
 * throws Exception {
 * auth.eraseCredentials(true).userDetailsService(authenticationService).
 * passwordEncoder(passwordEncoder()); } }
 */