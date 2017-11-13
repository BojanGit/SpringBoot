package si.best.job.ever.dao;

import java.util.List;

import si.best.job.ever.mongo.Lecture;

public interface LectureService {

	void deleteAll();
	List<Lecture> listLectures();
	List<Lecture> listLectures(String studentId);
	void save(Lecture lecture);
	Lecture get(String lectureId);
}
