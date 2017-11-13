package si.best.job.ever.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import si.best.job.ever.mongo.Klass;

@Service
public class KlassServiceImpl implements KlassService {

	@Autowired(required = false)
	private KlassRepository klassRepository;
	
	@Override
	public void save(Klass klass) {
		klassRepository.save(klass);
	}

	@Override
	public void deleteAll() {
		klassRepository.deleteAll();
	}
}
