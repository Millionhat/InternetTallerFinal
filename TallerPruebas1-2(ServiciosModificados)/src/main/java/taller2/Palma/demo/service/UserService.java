package taller2.Palma.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import taller2.Palma.demo.DAOimp.UserDAO;
import taller2.Palma.demo.model.Userr;
import taller2.Palma.demo.repository.UserRepository;
import taller2.Palma.demo.serviceInt.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface{
	
	private UserDAO repo;
	
	public UserService(UserDAO repo) {
		this.repo=repo;
	}
	@Override
	public Userr save(Userr user) {
		repo.save(user);
		return user;
	}

	@Override
	public Optional<Userr> getUser(long id) {
		return Optional.of(repo.findById(id));
	}

}
