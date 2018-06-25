package service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import domain.Role;
import domain.User;
import repos.UserRepo;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByUsername(username);
	}
	
	public boolean addUser(User user) {
		User userFromDb = userRepo.findByUsername(user.getUsername());
			if(userFromDb != null) {
				return false;
			}
		user.setActive(true);
		user.setRoles(Collections.singleton(Role.USER));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
		
		return true;
	}

}
