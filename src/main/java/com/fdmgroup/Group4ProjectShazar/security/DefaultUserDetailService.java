package com.fdmgroup.Group4ProjectShazar.security;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.repository.UserRepository;

@Service
public class DefaultUserDetailService implements UserDetailsService {

	private UserRepository userRepository;
	
	@Autowired
	public DefaultUserDetailService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		return new UserPrincipal(user);
	}
	
	public User findByUsername(String username) {
		
		Optional<User> optionalUser = userRepository.findByUsername(username);
		User user = optionalUser.orElse(new User("default username"));
		
		return user;
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	public List<User> listAllUsers() {
		List<User> allUsers = userRepository.findAll();
		
		return allUsers;
	}

}
