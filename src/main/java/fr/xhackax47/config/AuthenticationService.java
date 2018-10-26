package fr.xhackax47.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import fr.xhackax47.dao.RoleRepository;
import fr.xhackax47.dao.UserRepository;
import fr.xhackax47.model.Role;
import fr.xhackax47.model.User;

@Component
public class AuthenticationService implements UserDetailsService{
	
	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private RoleRepository rRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> u = uRepo.findOneByLogin(username);
		if(u.isPresent()) {
			User user = u.get();
			Collection<Role> roles = this.getUserCredentials(user);
			org.springframework.security.core.userdetails.User fullUsr = new org.springframework.security.core.userdetails.User(user.getLogin(),user.getPassword(), (Collection<? extends GrantedAuthority>) roles);
			return fullUsr;
		}
		UsernameNotFoundException e = new UsernameNotFoundException("username.non.trouve");
		throw e;
	}

	private Collection<Role> getUserCredentials(User user) {
		Collection<Role> authorities = new ArrayList<Role>();
		authorities.addAll(rRepo.findAll());
		return authorities;
	}

}
