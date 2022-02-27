package hu.oktatas.transport.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hu.oktatas.transport.model.UserType;
import hu.oktatas.transport.service.StaticUsers;

@Service
public class TransportPlanUserDetailsService implements UserDetailsService {
	


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserType userType = findByUserName(username);
		if (userType == null) {
			throw new UsernameNotFoundException(username);
		}

		return new User(userType.getUsername(), userType.getPassword(),
				userType.getRole().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
	}

	public UserType findByUserName(String name) {
		
		for (UserType userDetail : StaticUsers.users) {
			if (userDetail.getUsername().equals(name)) {
				return userDetail;
			}
		}
		return null;
	}

}
