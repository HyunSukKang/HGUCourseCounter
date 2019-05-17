package edu.handong.analysis.datamodel;

public class Course {
	private String studentId;
	private String yearMonthGraduated;
	private String firstMajor;
	private String secondMajor;
	private String courseCode;
	private String courseName;
	private String courseCredit;
	private int yearTaken;
	private int semesterCourseTaken;
	
	public Course(String line) {
		String[] tempString;
		tempString = line.trim().split(",");
		
		studentId = tempString[0];
		yearMonthGraduated = tempString[1];
		firstMajor = tempString[2];
		secondMajor = tempString[3];
		courseCode = tempString[4];
		courseName = tempString[5];
		courseCredit = tempString[6];
		yearTaken = Integer.parseInt(tempString[7]);
		semesterCourseTaken = Integer.parseInt(tempString[8]);		
	}
	
	public int getYearTaken() {
		return yearTaken;
	}
	
	public int getSemesterCourseTaken() {
		return semesterCourseTaken;
	}
}
