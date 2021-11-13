package com.joteya.config;
/*
 * package com.joteya.security;
 * 
 * import javax.sql.DataSource;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class SecurityConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Autowired private DataSource dataSource;
 * 
 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
 * Exception {
 * 
 * auth.jdbcAuthentication().dataSource(dataSource)
 * .usersByUsernameQuery("select username as principal, password as credentials from user where username=?"
 * )
 * .authoritiesByUsernameQuery("select username as principal, role as role from user_with_role where username=?"
 * ) .rolePrefix("ROLE_");
 * 
 * }
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * 
 * http.formLogin();
 * 
 * 
 * http.authorizeRequests().antMatchers("/products").hasRole("USER");
 * http.authorizeRequests().antMatchers("/product").hasRole("ADMIN");
 * 
 * 
 * http.csrf().disable().authorizeRequests().antMatchers("/login", "/home",
 * "/failure").permitAll() .antMatchers(HttpMethod.POST,
 * "/admin/**").hasRole("ADMIN").antMatchers(HttpMethod.PUT, "/admin/**")
 * .hasRole("ADMIN").antMatchers(HttpMethod.GET, "/admin/**").hasRole("ADMIN")
 * .antMatchers(HttpMethod.GET, "/user/**").hasAnyRole("ADMIN", "USER")
 * .antMatchers(HttpMethod.POST, "/user/**").hasAnyRole("ADMIN",
 * "USER").anyRequest().authenticated();
 * 
 * }
 * 
 * }
 */