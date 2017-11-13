package si.best.job.ever.test;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;
 
public class SpringBootRestTestClient {
 
    public static final String REST_SERVICE_URI = "http://localhost:8080/SpringBootRestApi/student";
    
	public static final Logger logger = LoggerFactory.getLogger(SpringBootRestTestClient.class);
    
	private static String uniqueHash = "";
    
	private static void createTestData() {

    	logger.info("createTestData()");
    	logger.info("\tcreating data...");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("user", uniqueHash));
        String status = restTemplate.getForObject(REST_SERVICE_URI+"/create-test-data/", String.class);
        logger.info("\t- " + status);
    }
    
    private static String listStudents() {

    	logger.info("listStudents()");
    	
    	RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("user", uniqueHash));
        @SuppressWarnings("unchecked")
		List<LinkedHashMap<String, Object>> studentMap = restTemplate.getForObject(REST_SERVICE_URI+"/list/", List.class);
        
        if(studentMap!=null){
        	String id = "/";
        	String name = "/";
            for(LinkedHashMap<String, Object> map : studentMap){
            	id = (String)map.get("id");
            	name = (String)map.get("name");
            	logger.info("\n\tStudent: id = " + map.get("id") + ", Name = " + map.get("name"));
            }
            logger.info("Here look at lectures available for " + name);
            return id;
        }else{
        	logger.info("\n\tNo student exist!");
        	return null;
        }
    }
 
    private static List<String> listClassLectures(String studentId) {

    	logger.info("listClassLectures("+studentId+")");
    	
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("user", uniqueHash));
        @SuppressWarnings("unchecked")
		List<LinkedHashMap<String, Object>> lectureMap = restTemplate.getForObject(REST_SERVICE_URI+"/class-lectures/"+studentId, List.class);
        
        if(lectureMap!=null){
        	List<String> lectures = new ArrayList<String>();
            for(LinkedHashMap<String, Object> map : lectureMap) {
            	lectures.add((String)map.get("id"));
            	logger.info("\n\tLecture: id = " + map.get("id") + ", Name = " + map.get("name"));
            }
            return lectures;
        }else{
        	logger.info("\n\tNo lecture exist for that student!");
        	return null;
        }
    }
    
    private static void submitStudentLectures(String studentId, List<String> lecturesId) {

    	logger.info("submitStudentLectures(" + studentId + ", "  + lecturesId.size() + ")");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("user", uniqueHash));
        restTemplate.postForLocation(REST_SERVICE_URI+"/student-lectures-add/"+studentId, lecturesId, List.class);
    }
    
    private static void listStudentLectures(String studentId) {

    	logger.info("listStudentLectures("+studentId+")");
    	
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("user", uniqueHash));
        @SuppressWarnings("unchecked")
		List<LinkedHashMap<String, Object>> lectureMap = restTemplate.getForObject(REST_SERVICE_URI+"/student-lectures-list/"+studentId, List.class);
        
        if(lectureMap!=null){
            for(LinkedHashMap<String, Object> map : lectureMap) {
            	logger.info("\n\tLecture: id = " + map.get("id") + ", Name = " + map.get("name"));
            }
        }else{
        	logger.info("\n\tNo lecture exist for that student!");
        }
    }
    
    private static void removeStudentLectures(String studentId, List<String> lecturesId) {

    	logger.info("removeStudentLectures(" + studentId + ", "  + lecturesId.size() + ")");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("user", uniqueHash));
        restTemplate.postForLocation(REST_SERVICE_URI+"/student-lectures-remove/"+studentId, lecturesId, List.class);
    }
    
    public static void main(String args[]){
    	
    	uniqueHash = "bc180bf3-36c0-4ca6-b545-0f7eda360339";
    	
        createTestData();
        String studentId = listStudents();
        List<String> lectures = listClassLectures(studentId);
        submitStudentLectures(studentId, lectures);
        listStudentLectures(studentId);
        removeStudentLectures(studentId, Arrays.asList(lectures.get(0)));
        listStudentLectures(studentId);
    }
}