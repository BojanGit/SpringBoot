package si.best.job.ever.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import si.best.job.ever.mongo.Student;

public interface StudentRepository extends MongoRepository<Student, String> {
	
}
