import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

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
		Activities activity1 = new Activities(1, "Basketball", "Sports", 10);
		Activities activity2 = new Activities(2, "Hockey", "Sports", 20);
		Activities activity3 = new Activities(3, "NCC", "Uniform", 20);
		Activities activity4 = new Activities(4, "NPCC", "Uniform", 20);
		Activities activity5 = new Activities(5, "Dance", "Performing Arts", 30);

		activities.add(activity1);
		activities.add(activity2);
		activities.add(activity3);
		activities.add(activity4);
		activities.add(activity5);

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
			promptStudentMenu(activities);
		} else if (account.getRole())
	}

	// @Danial
	private static void logoutUser() {
		account = null;

		System.out.println();
		Helper.line(40, "=");
		System.out.println("LOGGED OUT!");
		Helper.line(40, "=");
		System.out.println();

		promptLogin();
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
	
	private static Activities getActivity() {
		Teacher teacher = (Teacher) account;

		for (int i = 0; i < activities.size(); i++) {
			if (activities.get(i).getId() == teacher.getActivitesId()) {
				return activities.get(i);
			}
		}

		return activities.get(0);
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
				viewAllPending();
			} else if (option == 3) {
				inputDeletePending();
			} else if (option == 4) {
				Activities activity = inputAddActivity();
				doAddActivity(activities,activity);
			} else if (option == 5) {
				viewAllActivities(activities);
			} else if (option == 6) {
				int id = doDeleteActivity(activities); 
				inputDeleteActivity(activities, id);
			} else if (option == 7) {
				inputAddAttendance();
			} else if (option == 8) {
				viewAttendance();
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
	public static void doSetApprovalStatus(int studentIndex, char approvalChoice) {
		Activities activity = getActivity();
		ArrayList<Student> pendingStudents = activity.getPendingStudents();
		
		if (studentIndex >= 0 && studentIndex < pendingStudents.size()) {
			Student selectedStudent = pendingStudents.get(studentIndex);
		
		if (approvalChoice == 'Y' || approvalChoice == 'y') {
			pendingStudents.remove(selectedStudent);
			activity.getStudents().add(selectedStudent);
			System.out.println(selectedStudent.getName() + " has been approved.");
		} else if (approvalChoice == 'N' || approvalChoice == 'n') {
			pendingStudents.remove(selectedStudent);
			System.out.println(selectedStudent.getName() + " has not been approved.");
		} else {
			System.out.println("Invalid choice.");
		}
	} else {
		System.out.println("Invalid student index.");
	}

		//
	}
	
	public static void inputSetApprovalStatus() {
		Activities activity = getActivity();
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
		doSetApprovalStatus(studentIndex, approvalChoice);
		
	}
	

	// @Regan
	private static void viewAllPending() {
		Activities activity = getActivity();
		ArrayList<Student> pendingStudents = Activities.getPendingStudents();

		if (pendingStudents.isEmpty()) {
			System.out.println("No pending students for this activity.");
			return;
		}

		System.out.println("Pending Students:");
		for (int i = 0; i < pendingStudents.size(); i++) {
			System.out.println(i + 1 + ") " + pendingStudents.get(i).getName());
		}

	}

	// @Regan
	public static void doDeletePending() {
		
	}
	
	public static void inputDeletePending() {
		
	}
	private static void deletePending() {
		ArrayList<Student> pendingStudents = ((Teacher) account).getPendingStudents();

		if (pendingStudents.isEmpty()) {
			System.out.println("No pending students for this activity.");
			return;
		}

		System.out.println("Pending Students:");
		for (int i = 0; i < pendingStudents.size(); i++) {
			System.out.println(i + 1 + ") " + pendingStudents.get(i).getName());
		}

		int studentIndex = Helper.readInt("Enter the index of the student you want to delete: ") - 1;

		if (studentIndex >= 0 && studentIndex < pendingStudents.size()) {
			Student selectedStudent = pendingStudents.get(studentIndex);

			char deleteChoice = Helper.readChar("Delete this student from pending? (Y/N): ");
			if (deleteChoice == 'Y' || deleteChoice == 'y') {
				((Teacher) account).getPendingStudents().remove(selectedStudent);
				System.out.println(selectedStudent.getName() + " has been removed from pending.");
			} else if (deleteChoice == 'N' || deleteChoice == 'n') {
				System.out.println("No action taken.");
			} else {
				System.out.println("Invalid choice.");
			}
		} else {
			System.out.println("Invalid student index.");
		}

	}

	// @Jannah
	public static void doAddActivity(ArrayList<Activities> activitiesList, Activities activity) {
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
	private static void viewAllActivities(ArrayList<Activities> activitiesList) {

		System.out.println(String.format("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |", "ID", "Name", "Category",
				"No. Of Students", "Max Capacity", "Available"));

		for (int i = 0; i < activitiesList.size(); i++) {
			System.out.println(activitiesList.get(i).toString());
		}
		if (activitiesList.isEmpty()) {
			System.out.println("\n *** There is no activites ***");
		}

	}

	// @Jannah
	public static int doDeleteActivity(ArrayList<Activities> activities) {
		viewAllActivities(activities);
		int searchActivity = Helper.readInt("Enter Activity ID > ");
		return searchActivity;
		
	}
	
	public static void inputDeleteActivity(ArrayList<Activities> activities, int id) {
		boolean found = false;
		for (int i = 0; i < activities.size(); i++) {
			if (id == activities.get(i).getId()) {
				found = true;
				System.out.println(String.format("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |", "ID", "Name",
						"Category", "No. Of Students", "Max Capacity", "Available"));
				System.out.println(activities.get(i).toString());
				char confirm = Helper.readChar("Confirm deletion of Activity (y/n)> ");
				if (confirm == 'y') {
					activities.remove(i);
					System.out.println("\n*** Activity has been deleted ***");
				} else {
					System.out.println("\n*** You have not deleted anything ***");
				}
			} 
		}
		if (found == false) {
				System.out.println("\n*** Invalid Activity ID. Try Again ***");
			}
		
	}
	private static void deleteActivity(ArrayList<Activities> activitiesList) {

		viewAllActivities(activitiesList);

		boolean found = false;

		int searchActivity = Helper.readInt("Enter Activity ID > ");
		for (int i = 0; i < activitiesList.size(); i++) {
			if (searchActivity == activitiesList.get(i).getId()) {
				found = true;
				System.out.println(String.format("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |", "ID", "Name",
						"Category", "No. Of Students", "Max Capacity", "Available"));
				System.out.println(activitiesList.get(i).toString());
				char confirm = Helper.readChar("Confirm deletion of Activity (y/n)> ");
				if (confirm == 'y') {
					activitiesList.remove(i);
					System.out.println("\n*** Activity has been deleted ***");
				} else {
					System.out.println("\n*** You have not deleted anything ***");
				}

			}
		}
		if (found == false) {
			System.out.println("\n*** Invalid Activity ID. Try Again ***");
		}

	}


   
    
	// @Lleyton
	public static void doAddAttendance() {
	
	}
	
	public static void inputAddAttendance() {
       
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

	// @Lleyton
	public static void viewAttendance() {
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

	// @Lleyton
	public static void doDeleteAttendance() {
		
	}
	
	public static void inputDeleteAttendance() {
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
	                    char confirmation = Helper.readChar("Confirm deletion? (Y/N): ");
	                    if (confirmation == 'Y') {
	                        attendance = false;
	                    }
	                    else if (confirmation == 'N') {
	                        attendance = true;
	                    }
	                    else {
	                        System.out.println("Invalid input.");
	                    }
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
				viewAllRegistrations();
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
	public static void doRegisterForActivity() {
		
	}
	
	public static void inputRegisterForActivity() {
		
	}
	
	private static void registerForActivity(ArrayList<Activities> activitiesList, Student student) {
		// viewing of activities
		viewAllActivities(activitiesList);

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
	public static void viewAllRegistrations() {
		System.out.println("Registered Activities:");
		
		Student student = (Student) account;

		for (Activities activity : activities) {
			if (activity.getStudents().contains(student) || activity.getPendingStudents().contains(student)) {
				System.out.println(activity.getName());
			}
		}
	}

	// @Diya
	public static void doDeleteRegistration() {
		
	}
	
	public static void inputDeleteRegistration() {
		
	}
	
	private static void deleteRegistrations(Student student, ArrayList<Activities> activitiesList) {
		List<Activities> registeredActivities = new ArrayList<>();

		// showing the list of registered activities and including them in the list
		System.out.println("Registered Activities:");
		for (Activities activity : activitiesList) {
			if (activity.getStudents().contains(student)) {
				registeredActivities.add(activity);
				System.out.println("ID: " + activity.getId() + " - " + activity.getName());
			}
		}
		if (registeredActivities.isEmpty()) {
			System.out.println("You have not registered for any activities.");
			return;
		}

		// deletion part
		int activityIDToDelete = Helper.readInt("Enter the ID of the activity you want to unregister from: ");
		boolean found = false;

		for (Activities activity : registeredActivities) {
			if (activity.getId() == activityIDToDelete) {
				found = true;
				char confirm = Helper.readChar("Are you sure you want to unregister from this activity? (Y/N): ");
				if (confirm == 'Y' || confirm == 'y') {
					activity.getStudents().remove(student);
					System.out.println("You have been unregistered from the activity.");
				} else {
					System.out.println("Unregistration canceled.");
				}
				break;
			}
		}

		if (!found) {
			System.out.println("Invalid activity ID.");
		}
	}

	// ADMIN MENU
	private static void showAdminOptions() {
		Helper.line(40, "=");
		System.out.println("ADMIN MENU");
		Helper.line(40, "=");
		System.out.println("1) Add User");
//		System.out.println("2) View All Registrations");
//		System.out.println("3) Delete Registration");
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
				inputRegisterForActivity();
			} else if (option == 2) {
				viewAllRegistrations();
			} else if (option == 3) {
				inputDeleteRegistration();
			} else if (option == 4) {
				logoutUser();
			} else if (option == 5) {
				break;
			}
		}
	}
	
	public static void doAddUser() {
		
	}
	
	public static void inputAddUser() {
		
	}
	
	public static void viewAllUsers() {
		
	}
	
	public static void doDeleteUser() {
		
	}
	
	public static void inputDeleteUser() {
		
	}
}
