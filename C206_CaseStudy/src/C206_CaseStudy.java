import java.util.ArrayList;

public class C206_CaseStudy {
	private static User account;
	private static boolean attendance;
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
			Helper.line(40, "=");
			System.out.println("ACCOUNT LOGIN");
			Helper.line(40, "=");
			
			String emailInput = Helper.readString("Enter email: ");
			String passwordInput = Helper.readString("Enter password: ");
			
			for (int i = 0; i < students.size(); i++) {
				Student student = students.get(i);
				if (student.getEmail().equalsIgnoreCase(emailInput) && student.getPassword().equals(passwordInput)) {
					account = student;
					break;
				}
			}
			
			for (int i = 0; i < teachers.size(); i++) {
				Teacher teacher = teachers.get(i);
				if (teacher.getEmail().equalsIgnoreCase(emailInput) && teacher.getPassword().equals(passwordInput)) {
					account = teacher;
					break;
				}
			}
			
			// Invalid email or password OR no account existing.
			if (account == null) {
				System.out.println("Invalid email/password. Try again!");
			}
		}
		
		System.out.println();
		Helper.line(40, "=");
		System.out.println("WELCOME, " + account.getName().toUpperCase());
		Helper.line(40, "=");
		System.out.println();
		
		if (account.getRole().equals("teacher")) {
			System.out.println("Logged in Teacher account");
		} else if (account.getRole().equals("student")) {
			System.out.println("Logged in Student account");
		}
	}
	
	public static void markAttendance() {
		while (attendance == false && account.getRole().equals("teacher")) {
			Helper.line(40, "=");
			System.out.println("ATTENDANCE MARKING");
			Helper.line(40, "=");
			
			int studentIDInput = Helper.readInt("Enter student ID: ");
			
			for (int i = 0; i < students.size(); i++) {
				Student student = students.get(i);
				int studentID = student.getId();
				if (studentID == (studentIDInput)) {
					attendance = true;
					break;
				}
			}
			
			if (attendance == false) {
				System.out.println("Invalid student ID. Try again!");
			}
			
			System.out.println();
			Helper.line(40, "=");
			System.out.println("Attendance marked for " + account.getName().toUpperCase());
			Helper.line(40, "=");
			System.out.println();
			
		}
	}
	
	public static void deleteAttendance() {
		while (attendance == true && account.getRole().equals("teacher")) {
			Helper.line(40, "=");
			System.out.println("ATTENDANCE DELETION");
			Helper.line(40, "=");
			
			int studentIDInput = Helper.readInt("Enter student ID: ");
			
			for (int i = 0; i < students.size(); i++) {
				Student student = students.get(i);
				int studentID = student.getId();
				if (studentID == (studentIDInput)) {
					attendance = false;
					break;
				}
			}
			
			if (attendance == false) {
				System.out.println("Invalid student ID. Try again!");
			}
			
			System.out.println();
			Helper.line(40, "=");
			System.out.println("Attendance deleted for " + account.getName().toUpperCase());
			Helper.line(40, "=");
			System.out.println();
			
		}
	}
	
	public static void showAttendance() {
		while (account.getRole().equals("teacher")) {
			for (int i = 0; i < students.size(); i++) {
				Student student = students.get(i);
				String studentClass = student.getClasslevel();
				String studentInfo = student.toString();
				Helper.line(40, "=");
				String.format("%-10s |", studentInfo, studentClass);
			}
		}
		
	}
}
