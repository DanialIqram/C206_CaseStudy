import java.util.ArrayList;

public class C206_CaseStudy {
	public static User account;
	public static ArrayList<User> admins;
	public static ArrayList<Student> students;
	public static ArrayList<Teacher> teachers;
	public static ArrayList<Activities> activities;

	public static void main(String[] args) {
		account = null;

		activities = new ArrayList<Activities>(); // This will hold the activities.

		// Add activities to the list
		activities.add(new Activities(1, "Basketball", "Sports", 2));
		activities.add(new Activities(2, "Hockey", "Sports", 20));
		activities.add(new Activities(3, "NCC", "Uniform", 20));
		activities.add(new Activities(4, "NPCC", "Uniform", 20));
		activities.add(new Activities(5, "Dance", "Performing Arts", 30));

		admins = new ArrayList<>();
		admins.add(new User(1, "admin", "admin@example.com", "admin", "admin"));

		students = new ArrayList<>();
		students.add(new Student(1, "student", "student@example.com", "student", "1A"));

		teachers = new ArrayList<>();
		teachers.add(new Teacher(1, "teacher", "teacher@example.com", "teacher", 1));

		promptLogin();
	}

	// Misc
	public static void header(String title, boolean lineGap) {
		if (lineGap) { System.out.println(); }
		
		Helper.line(40, "=");
		System.out.println(title.toUpperCase());
		Helper.line(40, "=");
		
		if (lineGap) { System.out.println(); }
	}
	
	public static boolean isValidLoginDetails(String email, String emailInput, String password, String passwordInput) {
		return email.equalsIgnoreCase(emailInput) && password.equals(passwordInput);
	}
	
	public static void promptLogin() {
		while (account == null) {
			header("account login", false);

			String emailInput = Helper.readString("Enter email: ");
			String passwordInput = Helper.readString("Enter password: ");

			for (Student student: students) {
				String email = student.getEmail();
				String password = student.getPassword();
				
				if (isValidLoginDetails(email, emailInput, password, passwordInput)) {
					account = student;
					break;
				}
			}

			for (Teacher teacher: teachers) {
				String email = teacher.getEmail();
				String password = teacher.getPassword();
				
				if (isValidLoginDetails(email, emailInput, password, passwordInput)) {
					account = teacher;
					break;
				}
			}
			
			for (User admin: admins) {
				String email = admin.getEmail();
				String password = admin.getPassword();
				
				if (isValidLoginDetails(email, emailInput, password, passwordInput)) {
					account = admin;
					break;
				}
			}

			// Invalid email or password OR no account existing.
			if (account == null) {
				System.out.println("Invalid email/password. Try again!");
			}
		}

		header("welcome, " + account.getName(), true);

		if (account.getRole().equals("teacher")) {
			promptTeacherMenu();
		} else if (account.getRole().equals("student")) {
			promptStudentMenu(activities);
		} else if (account.getRole().equals("admin")) {
			promptAdminMenu();
		}
	}

	// @Danial
	private static void logoutUser() {
		account = null;

		header("logged out!", true);
		promptLogin();
	}

	private static boolean isStudentAtMaxActivities(Student student) {
		int numOfActivities = 0;

		for (int i = 0; i < activities.size(); i++) {
			Activities activity = activities.get(i);
			ArrayList<Student> studentsList = activity.getStudents();
			
			if (studentsList.contains(student)) {
				numOfActivities += 1;
			}
		}

		return numOfActivities == 2;
	}

	private static boolean isActivityOpen(Activities activity) {
		return activity.getStudents().size() < activity.getMaxCapacity();
	}

	private static Activities getActivity(int activityId) {
		for (Activities activity : activities) {
			if (activity.getId() == activityId) {
				return activity;
			}
		}
		return null; // Activity with the specified ID not found
	}

	private static Student getStudentById(int id) {
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getId() == id) {
				return students.get(i);
			}
		}

		return null;
	}
	
	private static Teacher getTeacherById(int id) {
		for (int i = 0; i < teachers.size(); i++) {
			if (teachers.get(i).getId() == id) {
				return teachers.get(i);
			}
		}

		return null;
	}
	
	// Used for JUnit
	public static void setAdminList(ArrayList<User> adminList) {
		admins = adminList;
	}
	
	public static void setStudentList(ArrayList<Student> studentList) {
		students = studentList;
	}
	
	public static void setTeacherList(ArrayList<Teacher> teacherList) {
		teachers = teacherList;
	}
	
	public static void setActivitiesList(ArrayList<Activities> activitiesList) {
		activities = activitiesList;
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
				inputSetApprovalStatus();
			} else if (option == 2) {
				viewAllPending(account.getId());
			} else if (option == 3) {
				inputDeletePending();
			} else if (option == 4) {
				Activities activity = inputAddActivity();
				doAddActivity(activities, activity);
			} else if (option == 5) {
				viewAllActivities(activities);
			} else if (option == 6) {
				int id = inputDeleteActivity(activities);
				doDeleteActivity(activities, id);
			} else if (option == 7) {
				inputAddAttendance();
			} else if (option == 8) { 
				viewAttendances(account.getId(), 'A');
			} else if (option == 9) {
				inputDeleteAttendance();
			} else if (option == 10) {
				logoutUser();
			} else if (option == 11) {
				break;
			}
		}
	}

	// @Regan
	public static void doSetApprovalStatus(int teacherId, int studentId, char approvalChoice) {
		Teacher teacher = getTeacherById(teacherId);
		
		if (teacher == null) {
			System.out.println("Invalid teacherId.");
			return;
		}
		
		Activities activity = getActivity(teacher.getActivitesId());
		ArrayList<Student> pendingStudents = activity.getPendingStudents();
		ArrayList<Student> studentList = activity.getStudents();

		Student selectedStudent = getStudentById(studentId);
		
		if (selectedStudent == null) {
			System.out.println("Invalid studentId.");
			return;
		}
		
		approvalChoice = Character.toLowerCase(approvalChoice);
		
		switch(approvalChoice) {
			case 'y':
				pendingStudents.remove(selectedStudent);
				studentList.add(selectedStudent);
				System.out.println(selectedStudent.getName() + " has been approved.");
				break;
			case 'n':
				pendingStudents.remove(selectedStudent);
				System.out.println(selectedStudent.getName() + " has not been approved.");
				break;
			default:
				System.out.println("Invalid choice.");
		}
	}

	public static void inputSetApprovalStatus() {
		Teacher teacher = (Teacher) account;
		Activities activity = getActivity(teacher.getActivitesId());
		ArrayList<Student> pendingStudents = activity.getPendingStudents();

		if (pendingStudents.isEmpty()) {
			System.out.println("No pending students for this activity.");
			return;
		}

		System.out.println("Pending Students:");
		for (int i = 0; i < pendingStudents.size(); i++) {
			System.out.println(i + 1 + ") " + pendingStudents.get(i).getName());
		}
		
		int studentIndex = Helper.readInt("Enter the index of the student you want to approve: ");
		char approvalChoice = Helper.readChar("Approve this student? (Y/N): ");
		doSetApprovalStatus(teacher.getId(), studentIndex, approvalChoice);
	}

	// @Regan
	public static String viewAllPending(int teacherId) {
		Teacher teacher = getTeacherById(teacherId);
		Activities activity = getActivity(teacher.getActivitesId());
		ArrayList<Student> pendingStudents = activity.getPendingStudents();

		if (pendingStudents.isEmpty()) {
			System.out.println("No pending students for this activity.");
			return "No pending students for this activity.";
		}
		
		String output = "Pending Students:\n";

		System.out.println("Pending Students:");
		for (int i = 0; i < pendingStudents.size(); i++) {
			output += pendingStudents.get(i).getId() + ") " + pendingStudents.get(i).getName() + "\n";
		}
		
		System.out.println(output);
		
		return output;

	}

	// @Regan
	public static void doDeletePending(int teacherId, int studentId, char deleteChoice) {
		Teacher teacher = getTeacherById(teacherId);
		Activities activity = getActivity(teacher.getActivitesId());
		ArrayList<Student> pendingStudents = activity.getPendingStudents();
		
		Student selectedStudent = getStudentById(studentId);
		
		if (selectedStudent == null) {
			System.out.println("Invalid student index.");
			return;
		}
		
		deleteChoice = Character.toLowerCase(deleteChoice);
		if (deleteChoice == 'y') {
			pendingStudents.remove(selectedStudent);
			System.out.println(selectedStudent.getName() + " has been removed from pending.");
		} else if (deleteChoice == 'n') {
			System.out.println("No action taken.");
		} else {
			System.out.println("Invalid choice.");
		}
	}

	public static void inputDeletePending() {
		int studentId = Helper.readInt("Enter the index of the student you want to delete: ");
		char deleteChoice = Helper.readChar("Confirm Delete?:");
		doDeletePending(account.getId(), studentId, deleteChoice);
	}

	// @Jannah
	public static void doAddActivity(ArrayList<Activities> activities, Activities activity) {
		for (Activities aty: activities) {
			String name = aty.getName();
			if (name.equalsIgnoreCase(activity.getName())) {
				System.out.println("\n*** Activity has the same name as another activity ***");
				return;
			}
		}
		
		activities.add(activity);
		System.out.println("\n*** Activity has been added ***");
	}

	// @Jannah
	private static Activities inputAddActivity() {
		String name = Helper.readString("Enter Activity Name > ");
		String category = Helper.readString("Enter the Category > ");
		int maxCap = Helper.readInt("Enter the max capacity > ");
		Activities activity = new Activities(activities.size() + 1, name, category, maxCap);
		return activity;
	}

	// @Jannah
	public static String viewAllActivities(ArrayList<Activities> activitiesList) {
		String output = "";

		output += String.format("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |", "ID", "Name", "Category",
				"No. Of Students", "Max Capacity", "Available");

		for (int i = 0; i < activitiesList.size(); i++) {
			output += "\n" + activitiesList.get(i).toString();
		}
		if (activitiesList.isEmpty()) {
			output += "\n *** There is no activites ***";
		}
		
		System.out.println(output);

		return output;

	}

	// @Jannah
	public static int inputDeleteActivity(ArrayList<Activities> activities) {
		viewAllActivities(activities);
		int searchActivity = Helper.readInt("Enter Activity ID > ");
		return searchActivity;

	}

	// @Jannah
	public static void doDeleteActivity(ArrayList<Activities> activities, int id) {
		Activities activity = getActivity(id);
		if (activity == null) {
			System.out.println("\n*** Invalid Activity ID. Try Again ***");
		}
		
		activities.remove(activity);
		System.out.println("\n*** Activity has been deleted ***");
	}
	
	// @Lleyton
	public static void doAddAttendance(int activityId, int studentId) {
		// Check if user exists inside student's list of activities
		Student student = getStudentById(studentId);
		Activities activities = getActivity(activityId);
		
		ArrayList<Student> studentList = activities.getStudents();
		if (!studentList.contains(student)) {
			System.out.println("Student is not in this CCA.");
			return;
		}
		
		// Check if user is not currently present 
		ArrayList<Student> attendance = activities.getAttendance();
		if (attendance.contains(student)) {
			System.out.println("Student has already been marked present.");
			return;
		}
		
		attendance.add(student);
	}
	
	// @Lleyton
	public static void inputAddAttendance() {
		Teacher teacher = getTeacherById(account.getId());
		
		viewAttendances(teacher.getId(), 'O');
		
		int studentId = Helper.readInt("Enter studentId of student to add attendance: ");
		doAddAttendance(teacher.getActivitesId(), studentId);
	}
	
	// @Danial
	public static String retrieveAttendance(int teacherId, char attendanceType) {
		Teacher teacher = getTeacherById(teacherId);
		if (teacher == null) {
			return "";
		}
		
		Activities activity = getActivity(teacher.getActivitesId());
		ArrayList<Student> studentList = activity.getStudents();
		ArrayList<Student> attendance = activity.getAttendance();
		
		String output = "";
		
		if (attendanceType == 'X') {
			for (Student student: attendance) {
				output += student.getName() + " (Id: " + student.getId() + ")" + "\n";
			}
		} else if (attendanceType == 'O') {
			for (Student student: studentList) {
				if (!attendance.contains(student)) {					
					output += student.getName() + " (Id: " + student.getId() + ")" + "\n";
				}
			}
		}
		
		return output;
	}
	
	// @Lleyton
	public static String viewAttendances(int teacherId, char attendanceType) {
		String output = "";
		
		if (attendanceType == 'A') {
			output += "PRESENT:\n";
			output += retrieveAttendance(teacherId, 'X');
			output += "\nABSENT:";
			output += "\n" + retrieveAttendance(teacherId, 'O');
		} else if (attendanceType == 'X') {
			output += "PRESENT:\n";
			output += retrieveAttendance(teacherId, 'X');
		} else if (attendanceType == 'O') {
			output += "ABSENT:\n";
			output += retrieveAttendance(teacherId, 'O');
		}
		
		System.out.println(output);
		
		return output;
	}

	// @Lleyton
	public static void doDeleteAttendance(int activityId, int studentId) {
		// Check if user exists inside student's list of activities
		Student student = getStudentById(studentId);
		Activities activities = getActivity(activityId);
				
		ArrayList<Student> studentList = activities.getStudents();
		if (!studentList.contains(student)) {
			System.out.println("Student is not in this CCA.");
			return;
		}
				
		// Check if user is currently present. 
		ArrayList<Student> attendance = activities.getAttendance();
		if (!attendance.contains(student)) {
			System.out.println("Student has not been marked present.");
			return;
		}
				
		attendance.remove(student);		
	}
	
	// @Lleyton
	public static void inputDeleteAttendance() {
		Teacher teacher = getTeacherById(account.getId());
		
		viewAttendances(teacher.getId(), 'X');
		
		int studentId = Helper.readInt("Enter studentId of student to delete attendance: ");
		doAddAttendance(teacher.getActivitesId(), studentId);
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

	private static void promptStudentMenu(ArrayList<Activities> activitiesList) {
		int option = -1;

		while (option != 5) {
			showStudentOptions();
			option = Helper.readInt("Enter option: ");

			if (option == 1) {
				inputRegisterForActivity();
			} else if (option == 2) {
				viewAllRegistrations(account.getId());
			} else if (option == 3) {
				inputDeleteRegistration();
			} else if (option == 4) {
				logoutUser();
			} else if (option == 5) {
				break;

			}
		}
	}

	// @Diya
	public static void doRegisterForActivity(int studentId, int activityId) {
		Student student = getStudentById(studentId);
		Activities activity = getActivity(activityId);
		
		// check if activity exists.
		if (activity == null) {
			System.out.println("This activity does not exist.");
			return;
		}
		
		// Check if activity is open
		if (!isActivityOpen(activity)) {
			System.out.println("Activity is at full capacity.");
			return;
		}
		
		// Check if they aren't registering yet.
		ArrayList<Student> pendingStudents = activity.getPendingStudents();
		if (pendingStudents.contains(student)) {
			System.out.println("Unable to register as you are already registering for this activity.");
			return;
		}
		
		// Add student to activity.
		activity.getPendingStudents().add(student);
		System.out.println("You have succesfully registered for the activity.");
	}
	
	public static void inputRegisterForActivity() {
		viewAllActivities(activities);
		
		Student student = getStudentById(account.getId());
		
		if (isStudentAtMaxActivities(student)) {
			System.out.println("Unable to register as you have the max activities of 2.");
			return;
		}
		
		int activityId = Helper.readInt("Enter activityId of activity you want to register for: ");
		doRegisterForActivity(student.getId(), activityId);
	}

	// @Diya
	public static String viewAllRegistrations(int studentId) {
		Student student = getStudentById(studentId);
		
		String output = "MY REGISTRATIONS:\n";
		
		for (Activities activity: activities) {
			ArrayList<Student> pendingStudents = activity.getPendingStudents();
			if (pendingStudents.contains(student)) {
				output += activity.getName() + " (Id: " + activity.getId() + ")\n";
			}
		}
		
		System.out.println(output);
		return output;
	}
	
	// @Diya
	public static void doDeleteRegistration(int studentId, int activityId) {
		// Check if student, activity exists
		Student student = getStudentById(studentId);
		Activities activity = getActivity(activityId);
		
		if (student == null) {
			System.out.println("This student does not exist.");
			return;
		}
		
		// check if activity exists.
		if (activity == null) {
			System.out.println("This activity does not exist.");
			return;
		}
		
		// check if student has already registered for the activity
		ArrayList<Student> pendingStudents = activity.getPendingStudents();
		if (!pendingStudents.contains(student)) {
			System.out.println("Student is not registering for this activity.");
			return;
		}
		
		pendingStudents.remove(student);
	}
	
	// @Diya
	public static void inputDeleteRegistration() {
		Student student = getStudentById(account.getId());
		
		viewAllRegistrations(student.getId());
		
		int activityId = Helper.readInt("Enter activityId of activity you want to un-register from: ");
		doDeleteRegistration(student.getId(), activityId);
	}

	// ADMIN MENU
	private static void showAdminOptions() {
		Helper.line(40, "=");
		System.out.println("ADMIN MENU");
		Helper.line(40, "=");
		System.out.println("1) Add User");
		System.out.println("2) View All Users");
		System.out.println("3) Delete User");
		System.out.println("4) Logout");
		System.out.println("5) Quit");

		Helper.line(40, "=");
	}

	private static void promptAdminMenu() {
		int option = -1;

		while (option != 5) {
			showAdminOptions();
			option = Helper.readInt("Enter option: ");

			if (option == 1) {
				inputAddUser();
			} else if (option == 2) {
				viewUsers('A');
			} else if (option == 3) {
				inputDeleteUser();
			} else if (option == 4) {
				logoutUser();
			} else if (option == 5) {
				break;
			}
		}
	}

	// @Danial
	public static void doAddStudent(String name, String email, String password, String classLevel) {
		// Check for duplicate email
		for (Student student: students) {
			if (student.getEmail().equalsIgnoreCase(email)) {
				System.out.println("Unable to create account as this email already exists.");
				return;
			}
		}
		
		students.add(new Student(students.size() + 1, name, email, password, classLevel));
		System.out.println("New student account added!");
	}
	
	// @Danial
	public static void doAddTeacher(String name, String email, String password, int activityId) {
		for (Teacher teacher: teachers) {
			if (teacher.getEmail().equalsIgnoreCase(email)) {
				System.out.println("Unable to create account as this email already exists.");
				return;
			}
		}
		
		teachers.add(new Teacher(teachers.size() + 1, name, email, password, activityId));
		System.out.println("New teacher account added!");
	}

	// @Danial
	public static void inputAddUser() {
		int addType = Helper.readInt("Enter 1 to add a teacher and 2 to add a student: ");

		String name = Helper.readString("Enter user's name: ");
		String email = Helper.readString("Enter user's email: ");
		String password = Helper.readString("Enter user's password: ");
		
		if (addType == 1) {
			viewAllActivities(activities);
			int activityId = Helper.readInt("Enter user's activityId: ");
			doAddTeacher(name, email, password, activityId);
		} else if (addType == 2) {
			String classLevel = Helper.readString("Enter user's class: ");
			doAddStudent(name, email, password, classLevel);
		}		
	}

	// @Danial
	public static String retrieveUsers(char viewType) {
		String output = "";
		
		if (viewType == 'T') {
			for (Teacher teacher: teachers) {
				output += teacher.getName() + " (Id: " + teacher.getId() + ")\n";
			}
		} else if (viewType == 'S') {
			for (Student student: students) {
				output += student.getName() + " (Id: " + student.getId() + ")\n";
			}
		}
		
		return output;
	}
	
	public static String viewUsers(char viewType) {
		String output = "";
		
		if (viewType == 'T') {
			output = "TEACHERS:\n";
			output += retrieveUsers('T');
		} else if (viewType == 'S') {
			output = "STUDENTS:\n";
			output += retrieveUsers('S');
		} else if (viewType == 'A') {
			output = "TEACHERS:\n";
			output += retrieveUsers('T');
			output += "\nSTUDENTS:\n";
			output += retrieveUsers('S');
		}
		
		System.out.println(output);
		
		return output;
	}

	// @Danial
	public static void doDeleteTeacher(int teacherId) {
		Teacher teacher = getTeacherById(teacherId);
		
		if (teacher == null) {
			System.out.println("Teacher does not exist.");
			return;
		}
		
		teachers.remove(teacher);
	}
	
	// @Danial
	public static void doDeleteStudent(int studentId) {
		Student student = getStudentById(studentId);
		
		if (student == null) {
			System.out.println("Student does not exist.");
			return;
		}
		
		students.remove(student);
	}

	// @Danial
	public static void inputDeleteUser() {
		int deleteType = Helper.readInt("Enter 1 to delete a teacher and 2 to delete a student: ");
		
		if (deleteType == 1) {
			viewUsers('T');
			int teacherId = Helper.readInt("Enter the teacher's id to delete: ");
			doDeleteTeacher(teacherId);
		} else if (deleteType == 2) {
			viewUsers('S');
			int studentId = Helper.readInt("Enter the student's id to delete: ");
			doDeleteStudent(studentId);
		}
	}
}
