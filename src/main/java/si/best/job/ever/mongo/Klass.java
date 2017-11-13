package si.best.job.ever.mongo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "klass")
public class Klass {

	@Id
	private String id;
	
	private String name;
	
	private Integer year;

	@DBRef
	private List<Student> studentList;
	
	@DBRef
	private List<Lecture> lectureList;
	
	public Klass(String name, Integer year) {
		this.name = name;
		this.year = year;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public List<Lecture> getLectureList() {
		return lectureList;
	}

	public void setLectureList(List<Lecture> lectureList) {
		this.lectureList = lectureList;
	}
	
}
