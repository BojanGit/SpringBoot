package si.best.job.ever.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import si.best.job.ever.mongo.Klass;

public interface KlassRepository extends MongoRepository<Klass, String> {
	
}
