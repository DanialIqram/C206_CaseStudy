import java.util.ArrayList;

public class C206_CaseStudy {
	private static User account;
	private static ArrayList<Student> students;
	private static ArrayList<Teacher> teachers;
	private static ArrayList<Activities> activities;

	public static void main(String[] args) {
		account = null;
		
		students = new ArrayList<>();
		students.add(new Student(1, "student", "student@example.com", "student", "1A"));
		
		teachers = new ArrayList<>();
		teachers.add(new Teacher(1, "teacher", "teacher@example.com", "teacher", 1));
		
		promptLogin();
	}
	
	public static void promptLogin() {
		while (account == null) {
			
		}
	}

}
