package com.bankapp.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

//import com.bankapp.model.Authority;
import com.bankapp.model.Customer;
import com.bankapp.repository.CustomerRepository;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username=authentication.getName();
		String password=authentication.getCredentials().toString();
		//List<Customer> customers = customerRepository.findByEmail(username);
		List<Customer> customers = customerRepository.findByName(username);
		if(!CollectionUtils.isEmpty(customers)) {
			Customer customer = customers.get(0);
			if(encoder.matches(password, customer.getPassword())) {
				List<GrantedAuthority> authorities=new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(customer.getRole()));
				return new UsernamePasswordAuthenticationToken(username, password, authorities);
			}else {
				throw new BadCredentialsException("invalid password!");
			}
		}else {
			throw new BadCredentialsException("No user gistered with this details");
		}
	}

//	private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> auths) {
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		for (Authority o : auths) {
//			authorities.add(new SimpleGrantedAuthority(o.getName()));
//		}
//		return authorities;
//	}

 

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
