import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class C206_CaseStudy {
	private static User account;
	private static ArrayList<Student> students;
	private static ArrayList<Teacher> teachers;
	private static ArrayList<Activities> activities;

	public static void main(String[] args) {
		account = null;

		List<Activities> activitiesList = new ArrayList<>(); // This will hold the activities. DIYA

		// Add activities to the list
		Activities activity1 = new Activities(1, "Basketball", "Sports", 10);
		Activities activity2 = new Activities(2, "Hockey", "Sports", 20);
		Activities activity3 = new Activities(3, "NCC", "Uniform", 20);
		Activities activity4 = new Activities(4, "NPCC", "Uniform", 20);
		Activities activity5 = new Activities(5, "Dance", "Performing Arts", 30);

		activitiesList.add(activity1);
		activitiesList.add(activity2);
		activitiesList.add(activity3);
		activitiesList.add(activity4);
		activitiesList.add(activity5);

		students = new ArrayList<>();
		students.add(new Student(1, "student", "student@example.com", "student", "1A"));

		teachers = new ArrayList<>();
		teachers.add(new Teacher(1, "teacher", "teacher@example.com", "teacher", 1));

		promptLogin();
	}

	// Misc
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
			promptTeacherMenu();
		} else if (account.getRole().equals("student")) {
			System.out.println("Logged in Student account");
			promptStudentMenu();
		} // TODO [Danial] Add admin system for Add user, View user, delete user.
	}

	// @Danial
	private static void logoutUser() {

	}

	private static boolean isStudentAtMaxActivities(Student student) {
		int numOfActivities = 0;

		for (int i = 0; i < activities.size(); i++) {
			Activities activity = activities.get(i);
			ArrayList<Student> students = activity.getStudents();

			for (int s = 0; s < students.size(); s++) {
				if (students.get(s) == student) {
					numOfActivities += 1;
				}
			}
		}

		return numOfActivities < 2;
	}

	private static boolean isInActivity(Student student, Activities activity) {
		ArrayList<Student> students = activity.getStudents();

		for (int i = 0; i < students.size(); i++) {
			if (students.get(i) == student) {
				return true;
			}
		}

		return false;
	}

	private static boolean isActivityOpen(Activities activity) {
		return activity.getStudents().size() < activity.getMaxCapacity();
	}

	// Teacher Options
	private static void showTeacherOptions() {
		Helper.line(40, "=");
		System.out.println("TEACHER MENU");
		Helper.line(40, "=");
		System.out.println("1) Set Approval Status");
		System.out.println("2) View All Pending");
		System.out.println("3) Delete Pending");
		System.out.println("4) Add Activity");
		System.out.println("5) View All Activities");
		System.out.println("6) Delete Activity");
		System.out.println("7) Add Attendance");
		System.out.println("8) View Attendance");
		System.out.println("9) Delete Attendance");
		System.out.println("10) Logout");
		System.out.println("11) Quit");
		Helper.line(40, "=");
	}

	private static void promptTeacherMenu() {
		int option = -1;

		while (option != 11) {
			showTeacherOptions();
			option = Helper.readInt("Enter option: ");

			if (option == 1) {
				setApprovalStatus();
			} else if (option == 2) {
				viewAllPending();
			} else if (option == 3) {
				deletePending();
			} else if (option == 4) {
				addActivity();
			} else if (option == 5) {
				viewAllActivities();
			} else if (option == 6) {
				deleteActivity();
			} else if (option == 7) {
				addAttendance();
			} else if (option == 8) {
				viewAttendance();
			} else if (option == 9) {
				deleteAttendance();
			} else if (option == 10) {
				logoutUser();
			} else if (option == 11) {
				break;
			}
		}
	}

	// @Regan
	private static void setApprovalStatus() {

	}

	// @Regan
	private static void viewAllPending() {

	}

	// @Regan
	private static void deletePending() {

	}

	// @Jannah
	private static void addActivity() {

	}

	// @Jannah
	private static void viewAllActivities() {

	}

	// @Jannah
	private static void deleteActivity() {

	}

	// @Lleyton
	private static void addAttendance() {

	}

	// @Lleyton
	private static void viewAttendance() {

	}

	// @Lleyton
	private static void deleteAttendance() {

	}

	// STUDENT MENU
	private static void showStudentOptions() {
		Helper.line(40, "=");
		System.out.println("STUDENT MENU");
		Helper.line(40, "=");
		System.out.println("1) Register for Activity");
		System.out.println("2) View All Registrations");
		System.out.println("3) Delete Registration");
		System.out.println("4) Logout");
		System.out.println("5) Quit");
		Helper.line(40, "=");
	}

	private static void promptStudentMenu(List<Activities> activitiesList) {
		int option = -1;

		while (option != 5) {
			showStudentOptions();
			option = Helper.readInt("Enter option: ");

			if (option == 1) {
				if(account.getRole() == "Student") {
					Student student = (Student) account; // Assuming account is already logged in as a student
					registerForActivity(activitiesList, student);
				}
				else {
					System.out.println("Activities registration for students only");
				}
			} else if (option == 2) {
				viewAllRegistrations();
			} else if (option == 3) {
				deleteRegistrations();
			} else if (option == 4) {
				logoutUser();
			} else if (option == 5) {
				break;
			}
		}
	}

	// @Diya
	private static void registerForActivity(List<Activities> activitiesList, Student student) {
		// viewing of activities
		viewAllActivities();

		// scanning the user input
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your preferred activity: ");

		String selectedActivity = scanner.nextLine();
		scanner.close();

		// checking if activity exists
		Activities selected = null;
		for (Activities activity : activitiesList) {
			if (activity.getName().equals(selectedActivity)) {
				selected = activity;
				break;
			}
		}

		if (selected == null) {
			System.out.println("Activity not found.");
			return;
		}

		// checking if activity is open
		if (!isActivityOpen(selected)) {
			System.out.println("Activity is at full capacity.");
			return;
		}

		// checking if student is already enrolled
		if (isInActivity(student, selected)) {
			System.out.println("You are already registered for this activity.");
			return;
		}

		// checking if student status is pending in the first place
		if (selected.getPendingStudents().contains(student)) {
			System.out.println("You already have a pending membership for this activity.");
			return;
		}

		// checking if the student already has a pending status in 2 or more activities
		// already
		int pendingActivitiesCount = 0;
		for (Activities activity : activitiesList) {
			if (activity.getPendingStudents().contains(student)) {
				pendingActivitiesCount++;
			}
		}

		if (pendingActivitiesCount >= 2) {
			System.out.println("You have reached the maximum allowed pending activities.");
			return;
		}

		selected.getPendingStudents().add(student);
		System.out.println("Student registered for the activity and is pending approval.");
	}

	// @Diya
	private static void viewAllRegistrations() {

	}

	// @Diya
	private static void deleteRegistrations() {

	}
}
