package si.best.job.ever.dao;

import java.util.List;

import si.best.job.ever.mongo.Student;

public interface StudentService {

	void createTestData(List<Student> studentList);
	void deleteAll();
	List<Student> getStudentList();
	void save(Student student);
	Student get(String studentId);
}
