package taller2.Palma.demo.serviceInt;

import java.util.Optional;

import taller2.Palma.demo.model.Userr;

public interface UserServiceInterface {

	public Userr save(Userr user);
	public Optional<Userr> getUser(long id);
}
