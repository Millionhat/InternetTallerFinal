package taller2.Palma.demo.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import taller2.Palma.demo.model.Userr;
import taller2.Palma.demo.repository.RoleRepository;
import taller2.Palma.demo.repository.UserRepository;


@Service
public class MyCustomUserDetailsService implements UserDetailsService {
	
	private UserRepository repo;
	private RoleRepository rep;
	
	public MyCustomUserDetailsService(UserRepository repo, RoleRepository rep) {
		this.repo=repo;
		this.rep=rep;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Userr usuario = repo.findByUserName(username);
		if (usuario != null) {
			//String role=rep.findById(usuario.getPerson().getPersonRoles().get(0).getId().getRoleRoleId()).get().getRoleName();
			User.UserBuilder builder = User.withUsername(username)
					.password(usuario.getUserPassword())
					.roles("");
			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}