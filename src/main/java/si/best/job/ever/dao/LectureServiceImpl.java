package si.best.job.ever.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;

import si.best.job.ever.mongo.Lecture;
import si.best.job.ever.mongo.Student;

@Service
public class LectureServiceImpl implements LectureService {

	@Autowired(required = false)
	private LectureRepository lectureRepository;
	
    MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(), "best_job"));
    
	@Override
	public List<Lecture> listLectures() {
		return lectureRepository.findAll();
	}
	
	@Override
	public List<Lecture> listLectures(String studentId) {
		Student student = mongoOps.findOne(Query.query(Criteria.where("id").is(studentId)), Student.class);
		return student.getKlass().getLectureList();
	}
	
	@Override
	public void save(Lecture lecture) {
		lectureRepository.save(lecture);
	}

	@Override
	public void deleteAll() {
		lectureRepository.deleteAll();
	}
	
	@Override
	public Lecture get(String lectureId) {
		Lecture lecture = mongoOps.findOne(Query.query(Criteria.where("id").is(lectureId)), Lecture.class);
		return lecture;
	}
}
