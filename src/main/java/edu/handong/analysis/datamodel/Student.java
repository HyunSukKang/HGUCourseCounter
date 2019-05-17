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
		int semester = 1;
		for(int i=0; i<coursesTaken.size(); i++) {
			String temp1 = Integer.toString(coursesTaken.get(i).getYearTaken());
			String temp2 = Integer.toString(coursesTaken.get(i).getSemesterCourseTaken());
			String temp3 = temp1 + "-" + temp2;
			
			if(!semestersByYearAndSemester.containsKey(temp3)) {
				semestersByYearAndSemester.put(temp3, semester);
				semester++;
			}
		}
		
		
		return semestersByYearAndSemester;
	}
	public int getNumCourseInNthSementer(int semester) {
		int count = 0;
		for(int i=0; i<coursesTaken.size(); i++) {
			String temp1 = Integer.toString(coursesTaken.get(i).getYearTaken());
			String temp2 = Integer.toString(coursesTaken.get(i).getSemesterCourseTaken());
			String temp3 = temp1 + "-" + temp2;
			
			int tempSemester = semestersByYearAndSemester.get(temp3);
			if(semester == tempSemester) {
				count++;
			}
			
		}
		
		return count;
	}
}
