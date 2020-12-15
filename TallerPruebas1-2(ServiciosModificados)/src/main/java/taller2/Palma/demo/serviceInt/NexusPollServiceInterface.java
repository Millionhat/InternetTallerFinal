package taller2.Palma.demo.serviceInt;

import java.util.Optional;

import taller2.Palma.demo.model.Nexuspoll;

public interface NexusPollServiceInterface {

		public Nexuspoll addPoll(Nexuspoll poll);
		public Nexuspoll update(Nexuspoll poll);
		public Optional<Nexuspoll> getPoll(long pollId);
		public void delete(Nexuspoll poll);
		public Iterable<Nexuspoll> getPolls();
}