package si.best.job.ever.dao;

import si.best.job.ever.mongo.Klass;

public interface KlassService {

	void deleteAll();
	void save(Klass klass);
}
