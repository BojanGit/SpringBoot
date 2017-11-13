package si.best.job.ever.mongo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student")
public class Student {
	
	@Id
	private String id;
	
	private String name;
	
	private Integer year;
	
	private Klass klass;
	
	@DBRef(lazy = true)
	private List<Lecture> lectureList;

	public Student() {
		
	}
	
	public Student(String name, Integer year) {  
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

	public Klass getKlass() {
		return klass;
	}

	public void setKlass(Klass klass) {
		this.klass = klass;
	}

	public List<Lecture> getLectureList() {
		return lectureList;
	}

	public void setLectureList(List<Lecture> lectureList) {
		this.lectureList = lectureList;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}
	
}
