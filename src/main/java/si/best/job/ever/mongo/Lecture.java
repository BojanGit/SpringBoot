package si.best.job.ever.mongo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lecture")
public class Lecture {
	
	@Id
	private String id;
	
	private String name;
	
	private String lecturerName;
	
	@DBRef
	private List<Student> studentList;
	
	@DBRef
	private List<Klass> klassList;
	
	public Lecture(String name, String lecturerName) {
		this.name = name;
		this.lecturerName = lecturerName;
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

	public String getLecturerName() {
		return lecturerName;
	}

	public void setLecturerName(String lecturerName) {
		this.lecturerName = lecturerName;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public List<Klass> getKlassList() {
		return klassList;
	}

	public void setKlassList(List<Klass> klassList) {
		this.klassList = klassList;
	}
}
