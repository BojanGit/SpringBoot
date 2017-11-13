package si.best.job.ever;


import java.util.ArrayList;
import java.util.Arrays;

import si.best.job.ever.dao.KlassService;
import si.best.job.ever.dao.LectureService;
import si.best.job.ever.dao.StudentService;
import si.best.job.ever.mongo.Klass;
import si.best.job.ever.mongo.Lecture;
import si.best.job.ever.mongo.Student;

public class CreateTestData {
	
	public static void generate(StudentService studentService, KlassService klassService, LectureService lectureService) {
		
		studentService.deleteAll();
		klassService.deleteAll();
		lectureService.deleteAll();
		
		Lecture computerScience = 			new Lecture("Computer science", 			"Janez Demšar");
		Lecture biology = 					new Lecture("Biology", 						"Janez Novak");
		Lecture spaceScience = 				new Lecture("Space science", 				"E.T.");
		Lecture legoTechnicConstruction = 	new Lecture("Lego Technic construction", 	"Captain Technic");
		
		lectureService.save(computerScience);
		lectureService.save(biology);
		lectureService.save(spaceScience);
		lectureService.save(legoTechnicConstruction);
		
		Klass tempKlass = new Klass("Prvi letnik", 2017);
		tempKlass.setLectureList(new ArrayList<Lecture>(Arrays.asList(computerScience, legoTechnicConstruction, biology)));
		klassService.save(tempKlass);
				
		Student bojan = new Student("Bojan", 2017);
		bojan.setKlass(tempKlass);
		//bojan.setLectureList(new ArrayList<Lecture>(Arrays.asList(computerScience, legoTechnicConstruction, biology)));
		
		Student uros = new Student("Uroš", 2017);
		uros.setKlass(tempKlass);
		//uros.setLectureList(new ArrayList<Lecture>(Arrays.asList(spaceScience, legoTechnicConstruction)));

		studentService.save(bojan);
		studentService.save(uros);
	}
}
