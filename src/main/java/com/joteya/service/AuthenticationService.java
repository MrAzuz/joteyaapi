/*
 * package com.joteya.service;
 * 
 * import java.util.ArrayList; import java.util.Collection;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.GrantedAuthority; import
 * org.springframework.security.core.authority.SimpleGrantedAuthority; import
 * org.springframework.security.core.userdetails.User; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service; import
 * com.joteya.dao.AccountRepository; import com.joteya.entities.Account;
 * 
 * 
 * @Service public class AuthenticationService implements UserDetailsService {
 * 
 * @Autowired private AccountRepository accountRepository;
 * 
 * @Override public User loadUserByUsername(String userName) throws
 * UsernameNotFoundException { Account u =
 * accountRepository.findOneByUsername(userName);
 * System.out.println(u.toString()); if (u == null) {
 * 
 * throw new UsernameNotFoundException("User not found : " + userName); }
 * 
 * User user = createUser(u);
 * 
 * return user; }
 * 
 * private User createUser(Account u) { return new User(u.getUsername(),
 * u.getPassword(), createAuthorities(u)); }
 * 
 * private Collection<GrantedAuthority> createAuthorities(Account u) {
 * Collection<GrantedAuthority> authorities = new ArrayList<>();
 * authorities.add(new SimpleGrantedAuthority("ROLE_" + u.getRole())); return
 * authorities; } }
 */