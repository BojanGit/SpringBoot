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

import si.best.job.ever.mongo.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired(required = false)
	private StudentRepository studentRepository;
	
    MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(), "best_job"));
	
	@Override
	public void createTestData(List<Student> studentList) {
		studentRepository.save(studentList);
	}
	
	@Override
	public List<Student> getStudentList() {
		return studentRepository.findAll();
	}

	@Override
	public void save(Student student) {
		studentRepository.save(student);
	}

	@Override
	public void deleteAll() {
		studentRepository.deleteAll();
	}

	@Override
	public Student get(String studentId) {
		Student student = mongoOps.findOne(Query.query(Criteria.where("id").is(studentId)), Student.class);
		return student;
	}
}
