package taller2.Palma.demo.serviceInt;

import java.util.Optional;

import taller2.Palma.demo.model.Nexuspoll;
import taller2.Palma.demo.model.Vulnerabilitypoll;

public interface VulnerabilitPollServiceInterface {

		public Vulnerabilitypoll addPoll(Vulnerabilitypoll poll);
		public Vulnerabilitypoll update(Vulnerabilitypoll poll);
		public Optional<Vulnerabilitypoll> getPoll(long pollId);
		public void delete(Vulnerabilitypoll poll);
		public Iterable<Vulnerabilitypoll> getPolls();
}
