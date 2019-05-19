package edu.handong.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysis.utils.NotEnoughArgumentException;
import edu.handong.analysis.utils.Utils;

public class HGUCoursePatternAnalyzer {

	private HashMap<String,Student> students;
	
	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 */
	public void run(String[] args) {
		
		try {
			// when there are not enough arguments from CLI, it throws the NotEnoughArgmentException which must be defined by you.
			if(args.length<2)
				throw new NotEnoughArgumentException();
		} catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		String dataPath = args[0]; // csv file to be analyzed
		String resultPath = args[1]; // the file path where the results are saved.
		ArrayList<String> lines = Utils.getLines(dataPath, true);
//		for(String s : lines) {
//			System.out.println(s);
//		}
		
		System.out.println("DEBUG>> getLines complete!");
		
		students = loadStudentCourseRecords(lines);
		System.out.println("DEBUG>> loadStudentCourseRecords complete!");
		
		// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
		Map<String, Student> sortedStudents = new TreeMap<String,Student>(students);
		System.out.println("DEBUG>> Make sortedStudents complete");
		
		// Generate result lines to be saved.
		ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
		System.out.println("DEBUG>> linesToBeSaved complete!");
		
		// Write a file (named like the value of resultPath) with linesTobeSaved.
		Utils.writeAFile(linesToBeSaved, resultPath);
		System.out.println("DEBUG>> writeAFile complete!");
	}
	
	/**
	 * This method create HashMap<String,Student> from the data csv file. Key is a student id and the corresponding object is an instance of Student.
	 * The Student instance have all the Course instances taken by the student.
	 * @param lines
	 * @return
	 */
	
	private HashMap<String,Student> loadStudentCourseRecords(ArrayList<String> lines) {
		students = new HashMap<String, Student>();
		String tempSID;
		Student student = null;
		boolean flag;
		
		for(String s : lines) {
			tempSID = s.trim().split(",")[0];
//			System.out.println("Split : " + tempSID);
			
			flag = students.containsKey(tempSID);
//			System.out.println("True or Flase : " + flag);
			
			if(flag == false) {
				student = new Student(tempSID);
//				System.out.println("Student : " + student.getStudentId());
			
				Course course = new Course(s);
//				System.out.println("Course : " + course.getStudentId());
				student.addCourse(course);
				
				students.put(tempSID, student);
			}
			else {
				Course course = new Course(s);
//				System.out.println("Course : " + course.getStudentId());
				student.addCourse(course);
				
			}
		}
		
		return students;
	}

	/**
	 * This method generate the number of courses taken by a student in each semester. The result file look like this:
	 * StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester
	 * 0001,14,1,9
     * 0001,14,2,8
	 * ....
	 * 
	 * 0001,14,1,9 => this means, 0001 student registered 14 semeters in total. In the first semeter (1), the student took 9 courses.
	 * 
	 * 
	 * @param sortedStudents
	 * @return
	 */
	private ArrayList<String> countNumberOfCoursesTakenInEachSemester(Map<String, Student> sortedStudents) {
		ArrayList<String> linesToBeSaved = new ArrayList<String>();
		Set<String> sidSet = sortedStudents.keySet();
		Object[] sidArray = sidSet.toArray();
		Student student;
		String StudentID;
		int TotalNumberOfSemestersRegistered;
		int Semester;
		int NumCoursesTakenInTheSemester;
		String Head = "StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester";
		String tempLine;
		
		linesToBeSaved.add(Head);
		
		for(int i=0; i<sidArray.length; i++) {
			StudentID = sidArray[i].toString();
			student = sortedStudents.get(StudentID);
			TotalNumberOfSemestersRegistered = student.getSemestersByYearAndSemester().size();
			for(int j=1; j<=TotalNumberOfSemestersRegistered; j++) {
				Semester = j;
				NumCoursesTakenInTheSemester = student.getNumCourseInNthSementer(Semester);
				tempLine = StudentID + ", " + Integer.toString(TotalNumberOfSemestersRegistered) + ", " + Integer.toString(Semester) + ", " + Integer.toString(NumCoursesTakenInTheSemester);
				linesToBeSaved.add(tempLine);
			}
		}
		
		return linesToBeSaved; // do not forget to return a proper variable.
	}
}
