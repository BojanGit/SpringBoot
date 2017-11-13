package si.best.job.ever.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import si.best.job.ever.CreateTestData;
import si.best.job.ever.dao.KlassService;
import si.best.job.ever.dao.LectureService;
import si.best.job.ever.dao.StudentService;
import si.best.job.ever.mongo.Lecture;
import si.best.job.ever.mongo.Student;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	public static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	public StudentService studentService;
	@Autowired
	KlassService klassService;
	@Autowired
	LectureService lectureService;
	
	@RequestMapping(value = "/create-test-data/", method = RequestMethod.GET) 
	public ResponseEntity<String> createTestData() {
		
		logger.info("createTestData()");
				
		CreateTestData.generate(studentService, klassService, lectureService);
		
		return new ResponseEntity<String>("Test data created", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/", method = RequestMethod.GET) 
	public ResponseEntity<List<Student>> listStudents() {
		
		logger.info("listStudents()");
		
		return new ResponseEntity<List<Student>>(studentService.getStudentList(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/class-lectures/{studentId}", method = RequestMethod.GET) 
	public ResponseEntity<List<Lecture>> listClassLectures(@PathVariable String studentId) {
		
		logger.info("listClassLectures("+studentId+")");
		
		return new ResponseEntity<List<Lecture>>(lectureService.listLectures(studentId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/student-lectures-add/{studentId}", method = RequestMethod.POST) 
	public ResponseEntity<String> submitStudentLectures(@PathVariable String studentId, @RequestBody List<String> lectures) {
		
		logger.info("submitStudentLectures("+studentId+")");
		
		Student student = studentService.get(studentId);
		List<Lecture> studentLectures = student.getLectureList() != null ? student.getLectureList() : new ArrayList<Lecture>();
		
		for (String s : lectures) {
			logger.info("\t-" + s);
			Lecture lecture = lectureService.get(s);
			if (!studentLectures.contains(lecture)) {
				studentLectures.add(lecture);
			}	
		}
		
		student.setLectureList(studentLectures);
		studentService.save(student);
		
		return new ResponseEntity<String>("Lectures added to this student", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/student-lectures-list/{studentId}", method = RequestMethod.GET) 
	public ResponseEntity<List<Lecture>> listStudentLectures(@PathVariable String studentId) {
		
		logger.info("listStudentLectures("+studentId+")");
		
		return new ResponseEntity<List<Lecture>>(studentService.get(studentId).getLectureList(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/student-lectures-remove/{studentId}", method = RequestMethod.POST) 
	public ResponseEntity<String> removeStudentLectures(@PathVariable String studentId, @RequestBody List<String> lectures) {
		
		logger.info("removeStudentLectures("+studentId+")");
		
		Student student = studentService.get(studentId);
		List<Lecture> studentLectures = student.getLectureList() != null ? student.getLectureList() : new ArrayList<Lecture>();
		
		List<Lecture> removeList = new ArrayList<Lecture>();
		
		for (Lecture temp : studentLectures)
			for (String id : lectures) {
			logger.info("\t-" + id);
			if (temp.getId().equals(id)) {
				removeList.add(temp);
			}	
		}
		studentLectures.removeAll(removeList);
		student.setLectureList(studentLectures);
		studentService.save(student);
		
		return new ResponseEntity<String>("Lectures removed for this student", HttpStatus.OK);
	}
}
