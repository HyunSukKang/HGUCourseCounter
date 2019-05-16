package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
	private String studentId;
	private ArrayList<Course> coursesTaken;
	private HashMap<String, Integer> semestersByYearAndSemester;
	
	public Student(String studentId) {
		this.studentId = studentId;
	}
	public void addCourse(Course newRecord) {
		coursesTaken.add(newRecord);
	}
	public HashMap<String, Integer> getSemestersByYearAndSemester(){
		
		return semestersByYearAndSemester;
	}
	public int getNumCourseInNthSementer(int semester) {
		
		return 0;
	}
}
