/**
 * @author Tihomir Mladenov, tihomir.mladenov777@gmail.com
 * Date: 22-Jun-2017
 * 
 * Created for Udacity's Android Basics Nanodegree, Project 5, Report Card
 * */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * This class is used to store students' grades for each subject.
 * The grade system is the one used in my country, Bulgaria.
 * 
 * Grade| Meaning:
 * 2	| Weak (F in US), i.e. Failing grade
 * 3	| Average (D in US)
 * 4	| Good (C in US)
 * 5	| Very good (B in US)
 * 6	| Excellent (A in US), i.e. Best possible grade
 * 
 * 4.50 and above until 5.00 is considered Very Good (B)
 * 4.00 to 4.49 is considered Good (C)
 * This rounding is true to all grades, except exact 6 (A), which is the max grade.
*/

/**
 * @param firstName		is used to store student's first name
 * @param lastName		is used to store student's last name
 * @param subject		is used to store sutent's subject, which is graded
 * @param date			is used to store the date when the grade is entered
 * @param professor		is used to store the name of the professor, who entered the grade
 * @param grade			is used to store a particular grade
 * @param average		is used to store sum of all grades, which is then used to print the average grade to the student.
 * 						^ Not using getter / setter as it is not used by the user.
 * @param data			is used to store student's grade for each subject, and info on the prof. and date
 * @param gradeCount 	is used to store the count of grades for subject. Used to get the average grade
 * @param gradeYear		is used to store the year for which the grades are entered
 * @param gradeInfo		is used to store a String object, containing all grade related information
 * 						^ Not used by the user.
*/

public class ReportCard {
	private String firstName;
	private String lastName;
	private String subject;
	private String date;
	private String professor;
	private String gradeInfo;
	private double grade, average;
	private HashMap data;
	private int gradeCount, gradeYear;
	
	// Default constructor of the class. Initiates it with all empty / zeroed variables
	ReportCard() {
		this.firstName = new String();
		this.lastName = new String();
		this.professor = new String();
		this.date = new String();
		this.subject = new String();
		this.gradeInfo = new String();
		this.grade = 0;
		this.average = 0;
		this.gradeCount = 0;
		this.gradeYear = 0;
		this.data = new HashMap();
	}
	
	// This constructor is called by the user when he creates the subject. Sets values to all variables, some user defined, some default
	ReportCard(String firstName, String lastName, int gradeYear) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gradeYear = gradeYear;
		this.grade = 0;
		this.average = 0;
		this.gradeCount = 0;
		this.date = new String();
		this.professor = new String();
		this.subject = new String();
		this.gradeInfo = new String();
		this.data = new HashMap();
	}
	
	
	/**
	 * Sets subject + grade + name of professor who graded it + date of grade for each subject
	 * 
	 * @see ReportCard comments for variable use and properties
	 */
	public void setGrade(String subject, double grade, String professor, String date) {
		this.subject = subject;
		this.grade = grade;
		this.professor = professor;
		this.date = date;
		this.gradeInfo = "grade: " + this.grade + ", graded by: " + this.professor + ", on date: " + this.date;
		average += grade;
		gradeCount++;
		data.put(subject, gradeInfo);
	}
	
	// Sets first name of the student
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	// Sets second / family name of the student
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	// Gets the first name of the student
	public String getFirstName() {
		return this.firstName;
	}
	
	// Gets the second / family name of the student
	public String getLastName() {
		return this.lastName;
	}
	
	// Gets professors' names from the last entered grade
	public String getProfessorName() {
		return this.professor;
	}
	
	// Sets professors' names from the last entered grade
	public void setProfessorName(String profName) {
		this.professor = profName;
	}
	
	// Sets the date when the last grade was obtained
	public void setDate(String newDate) {
		this.date = newDate;
	}
	
	// Returns the date when the last grade was obtained
	public String getDate() {
		return this.date;
	}
	
	// Sets the name of the last subject;
	public void setSubject(String newSubject) {
		this.subject = newSubject;
	}
	
	// Gets the name of the last entered subject;
	public String getSubject() {
		return this.subject;
	}
	
	// Returns String value with grade count for the student.
	// Not using setter method as it is not supposed to be used by the user.
	public String getGradeCount() {
		return ("Student " + this.getFirstName() + " " + this.getLastName() + " has " + Integer.toString(gradeCount) + " grades.");
	}
	
	public void setGradeYear(int newGradeYear) {
		this.gradeYear = newGradeYear;
	}
	
	// Gets the academic year for which the grades are entered
	public int getGradeYear() {
		return this.gradeYear;
	}
	
	// Removes the grade for a subject, if it exists. If not, an error is thrown.
	// Can be used if the wrong grade was entered.
	public void removeGrade(String subject, double grade) {
		if(data.get(subject) != null) {
			data.remove(subject);
			if((average > 0)  || ((average - grade) > 0)) {
				average -= grade;
			}
			gradeCount--;
			System.out.println("Grade " + grade + ", for student " + this.getFirstName() + ", for subject " + subject + " is now deleted.");
		} else {
			System.out.println("Subject " + subject + " for student " + this.getFirstName() + ", doesn't exist or has already been deleted.");
		}
	}
	
	// Gets the average grade for the student, if gradeCount is bigger than 0 of course
	public double getAverage() {
		if(gradeCount > 0) {
			return (average / gradeCount);
		} else {
			return 0;
		}
	}
	
	/**
	 * @param result	is used to append all the information for the student and then return it as a String object
	 * @param set		is used to store the set of mappings from @param data
	 * @param iter		is an Iterator object, used to iterate trough all @param set elements
	 * @param dataPrint is used to get to iterate to next element in the set
	 * 
	 * @see ReportCard comments for the use / properties of the other variables
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		Set set = data.entrySet();
		Iterator iter = set.iterator();
		
		System.out.println("Student first name: " + firstName + ", last name: " + lastName);
		System.out.println("Grade year: " + Integer.toString(gradeYear));
		
		Map.Entry dataPrint;
		while(iter.hasNext()) {
			dataPrint = (Map.Entry) iter.next();
			result.append("Subject: " + dataPrint.getKey() + ", " + dataPrint.getValue() + "\n");
		}
		
		result.append("Average final grade: " + this.getAverage());
		return result.toString();
	}
	
	// Returns the grade for a specific subject, if it exists
	public void getGrade(String subject) {
		if(data.get(subject) != null) {
			System.out.println("Subject: " + this.subject + ", grade: " + this.data.get(subject) + ".\n");
		} else {
			System.out.println("Cannot locate such subject");
		}
	}
	
	// Line separator for ease of output inspection for you, Code Inspectors ;)
	public static void lineSeparator() {
		StringBuilder lineSpacer = new StringBuilder();
		for(int i = 0; i < 90; i++) {
			lineSpacer.append("=");
		}
		System.out.println(lineSpacer.toString());
	}
}