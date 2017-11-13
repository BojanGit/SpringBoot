package si.best.job.ever.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import si.best.job.ever.mongo.Lecture;

public interface LectureRepository extends MongoRepository<Lecture, String> {
	
}
