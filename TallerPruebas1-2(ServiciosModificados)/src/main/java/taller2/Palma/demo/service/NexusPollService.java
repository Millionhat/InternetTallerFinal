package taller2.Palma.demo.service;

import java.util.Optional;

import taller2.Palma.demo.DAOimp.NexusPollDAO;
import taller2.Palma.demo.model.Nexuspoll;
import taller2.Palma.demo.serviceInt.NexusPollServiceInterface;

public class NexusPollService implements NexusPollServiceInterface {
	
	private NexusPollDAO repo;
	
	public NexusPollService(NexusPollDAO repo) {
		this.repo=repo;
	}

	@Override
	public Nexuspoll addPoll(Nexuspoll poll) {
		// TODO Auto-generated method stub
		repo.save(poll);
		return poll;
	}

	@Override
	public Nexuspoll update(Nexuspoll poll) {
		// TODO Auto-generated method stub
		Nexuspoll edit= repo.findById(poll.getNexpollId());
		edit=poll;
		repo.update(edit);
		return poll;
	}

	@Override
	public Optional<Nexuspoll> getPoll(long pollId) {
		// TODO Auto-generated method stub
		return Optional.of(repo.findById(pollId));
	}

	@Override
	public void delete(Nexuspoll poll) {
		// TODO Auto-generated method stub
		repo.delete(poll);
	}

	@Override
	public Iterable<Nexuspoll> getPolls() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
