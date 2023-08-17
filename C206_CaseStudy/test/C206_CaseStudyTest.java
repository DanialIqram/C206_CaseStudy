import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class C206_CaseStudyTest {
	private static Teacher teacher1;
	private static Teacher teacher2;
	private static User admin;
	private static Student student1;
	private static Student student2;

	private static Activities activity1;
	private static Activities activity2;

	private static ArrayList<Student> students;
	private static ArrayList<Teacher> teachers;
	private static ArrayList<User> admins;
	private static ArrayList<Activities> activities;

	@Before
	public void setUp() throws Exception {
		teacher1 = new Teacher(1, "Mr Lim", "mrlim@school.com", "mrlim", 1);
		teacher2 = new Teacher(2, "Miss Tan", "missTan@school.com", "MissTan", 2);
		admin = new User(1, "Admin", "admin@school.com", "admin", "admin");
		student1 = new Student(1, "Jack", "jack@school.com", "jack", "1A");
		student2 = new Student(2, "Mary", "mary@school.com", "mary", "1B");

		activity1 = new Activities(1, "Basketball", "Sports", 1);
		activity2 = new Activities(2, "Hockey", "Sports", 1);

		activities = new ArrayList<>();
		students = new ArrayList<>();
		teachers = new ArrayList<>();
		admins = new ArrayList<>();
		
		activities.add(activity1);
		activities.add(activity2);
		
		students.add(student1);
		students.add(student2);
		
		teachers.add(teacher1);
		teachers.add(teacher2);
		
		admins.add(admin);
		
		C206_CaseStudy.setActivitiesList(activities);
		C206_CaseStudy.setStudentList(students);
		C206_CaseStudy.setTeacherList(teachers);
		C206_CaseStudy.setAdminList(admins);
	}

	@After
	public void tearDown() throws Exception {
		teacher1 = null;
		admin = null;
		student1 = null;
		activity1 = null;
		activity2 = null;
		activities = null;
		students = null;
		teachers = null;
		admins = null;
	}

	@Test // @Danial
	public void testDoAddStudent() {
		// Normal Condition - Successful add
		int originalStudentsAmount = students.size();
		C206_CaseStudy.doAddStudent("Tony", "tony@example.com", "tony", "1C");
		assertEquals(students.size(), originalStudentsAmount + 1);
		
		// Boundary Condition - Same email
		C206_CaseStudy.doAddStudent("Tony", "tony@example.com", "tony", "1C");
		assertEquals(students.size(), originalStudentsAmount + 1);
		
		// Error Condition - No list
		assertNotNull("List should not be null", students);		
	}
	
	@Test // @Danial
	public void testDoAddTeacher() {
		// Normal Condition - Successful add
		int originalTeacherAmount = teachers.size();
		C206_CaseStudy.doAddTeacher("Mr Andy", "mrandy@example.com", "mrandy", 1);
		assertEquals("Test that the teachers list increased by 1", teachers.size(), originalTeacherAmount + 1);
		
		// Boundary Condition - Same email
		C206_CaseStudy.doAddTeacher("Mr Andy", "mrandy@example.com", "mrandy", 1);
		assertEquals(teachers.size(), originalTeacherAmount + 1);
		
		// Error Condition - No list
		assertNotNull("List should not be null", teachers);
		
	}

	@Test // @Danial
	public void testViewUsers() {
		// Normal Conditions 
		String output = "TEACHERS:\nMr Lim (Id: 1)\nMiss Tan (Id: 2)\n";
		assertEquals("2 teachers in the list", C206_CaseStudy.viewUsers('T'), output);
		
		// Boundary Conditions
		teachers.clear();
		String output2 = "TEACHERS:\n";
		assertEquals("No teachers in the list", C206_CaseStudy.viewUsers('T'), output2);
		
		// Error conditions
		assertNotNull("List should not be null", teachers);
		
	}
	
	@Test // @Danial
	public void testDoDeleteTeacher() {
		// Normal Condition - Successful remove
		int originalTeachersAmount = teachers.size();
		C206_CaseStudy.doDeleteTeacher(teacher1.getId());
		assertEquals("Deleting teacher1 from list", teachers.size(), originalTeachersAmount - 1);
								
		// Error Condition - Removing a teacher that does not exist.
		C206_CaseStudy.doDeleteTeacher(3);
		assertEquals("Deleting a teacher with id 3, a teacher that does not exist", teachers.size(), originalTeachersAmount - 1);	
	}
	
	@Test // @Danial
	public void testDoDeleteStudent() {
		// Normal Condition - Successful remove
		int originalStudentsAmount = students.size();
		C206_CaseStudy.doDeleteStudent(student1.getId());
		assertEquals("Deleting student1 from list", students.size(), originalStudentsAmount - 1);
						
		// Error Condition - Removing a student that does not exist.
		C206_CaseStudy.doDeleteStudent(3);
		assertEquals("Deleting a student with id 3, a student that does not exist", students.size(), originalStudentsAmount - 1);
	}

	@Test // @Jannah
	public void testDoAddActivity() {
		activities = new ArrayList<Activities>();
		// Boundary
		assertNotNull("Check for valid activities to be added to", activities);
		activities.clear();
		// Normal - Add an activity to an empty list and the size of the arraylist is 1
		C206_CaseStudy.doAddActivity(activities, activity1);
		assertEquals("Test that activity is added into activities ArrayList", 1, activities.size());
		assertSame("Test that acivity added is the same", activities.get(0), activity1);
		// Normal - Add another activity to test the size of the ArrayList is changed to
		// 2
		C206_CaseStudy.doAddActivity(activities, activity2);
		assertEquals("Test that activity is added into activities ArrayList", 2, activities.size());
		assertSame("Test that acivity added is the same", activities.get(1), activity2);
		// Error - Adding another activity with the same name, ArrayList should remain
		// as 2
		C206_CaseStudy.doAddActivity(activities, activity1);
		assertEquals("Test that activity is added into activities ArrayList", 2, activities.size());
	}

	@Test // @Jannah
	public void testViewAllActivities() {
		// Boundary - Check that the activities ArrayList is not null and can be added
		// to
		assertNotNull("Check for valid ArrayList activities to be added to", activities);
		// Boundary - When list is empty output is shown
		activities.clear();
		String allActivities = C206_CaseStudy.viewAllActivities(activities);
		String TestOutput = String.format("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |", "ID", "Name", "Category",
				"No. Of Students", "Max Capacity", "Available");
		TestOutput += "\n *** There is no activites ***";
		assertEquals("Test that message is shown when list is empty", TestOutput, allActivities);
		// Normal - ACtivity is added an is shown correctly
		C206_CaseStudy.doAddActivity(activities, activity1);
		String allActivities2 = C206_CaseStudy.viewAllActivities(activities);
		String testOutput = String.format("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |", "ID", "Name", "Category",
				"No. Of Students", "Max Capacity", "Available");
		testOutput += String.format("\n| %-20d | %-20s | %-20s | %-20d | %-20d | %-20s |", 1, "Basketball", "Sports", 0,
				1, "Yes");
		assertEquals("Test that message is shown when list is empty", testOutput, allActivities2);
		// Normal - Add another Activity is added and is shown correctly
		C206_CaseStudy.doAddActivity(activities, activity2);
		testOutput += String.format("\n| %-20d | %-20s | %-20s | %-20d | %-20d | %-20s |", 2, "Hockey", "Sports", 0, 2,
				"Yes");
	}

	@Test // @Jannah
	public void testDoDeleteActivity() {
		// Boundary
		assertNotNull("Check for valid ArrayList activities to be added to", activities);
		// Normal - Delete one activity from activities
		C206_CaseStudy.doAddActivity(activities, activity1);
		C206_CaseStudy.doDeleteActivity(activities, 1);
		assertEquals("Test that ArrayList is empty", 1, activities.size());
		// Error - Delete an activity that is not in the list
		C206_CaseStudy.doDeleteActivity(activities, 2);
		assertEquals("Test that ArrayList is still empty", 0, activities.size());

	}

	@Test // @Regan
	public void testDoSetApprovalStatus() {
		assertEquals(activity2.getPendingStudents().size(),0);
		C206_CaseStudy.doRegisterForActivity(student1.getId(), 2);
		assertEquals(activity2.getPendingStudents().size(),1);
		
		//Normal
		C206_CaseStudy.doSetApprovalStatus(teacher2.getId(), student1.getId(), 'N');
		assertEquals(activity2.getPendingStudents().size(), 0);
		assertEquals(activity2.getStudents().size(), 0);
		
		//Boundary
		C206_CaseStudy.doRegisterForActivity(student1.getId(), 2);
		C206_CaseStudy.doRegisterForActivity(student2.getId(), 2);
		assertEquals(activity2.getPendingStudents().size(), 2);
		C206_CaseStudy.doSetApprovalStatus(teacher2.getId(), student1.getId(), 'Y');
		C206_CaseStudy.doSetApprovalStatus(teacher2.getId(), student2.getId(), 'Y');
		assertEquals(activity2.getStudents().size(), 2);
		assertEquals(activity2.getPendingStudents().size(), 0);
		
		//Error
		C206_CaseStudy.doSetApprovalStatus(teacher2.getId(), student2.getId(), 'Y');
		assertEquals(activity2.getStudents().size(), 3);
		assertEquals(activity2.getPendingStudents().size(), 0);
	}

	@Test // @Regan
	public void testViewAllPending() {

	}

	@Test // @Regan
	public void testDoDeletePending() {

	}
	
	@Test // @Lleyton
	public void testDoAddAttendance() {
		// Error - Delete an activity that is not in the list
	}

	@Test // @Lleyton
	public void testViewAttendance() {

	}

	@Test // @Lleyton
	public void testDoDeleteAttendance() {
		// Error - Delete an attendance that is not in the list
	}

	@Test // @Diya
	public void testDoRegisterForActivity() {
		// Check for normal registration successful student registration
		C206_CaseStudy.doRegisterForActivity(student1.getId(), 1);
		assertEquals(activity1.getPendingStudents().size(), 1);

		// Check boundary successful registration when max capacity is 1
		activity1.getStudents().add(student1);
		C206_CaseStudy.doRegisterForActivity(student2.getId(), 1);
		assertEquals(activity1.getPendingStudents().size(), 1);

		// Check error when student registers for an invalid activity ID
		C206_CaseStudy.doRegisterForActivity(student1.getId(), 3);
		assertEquals(activity1.getPendingStudents().size(), 1);
		assertEquals(activity2.getPendingStudents().size(), 0);

	}

	@Test // @Diya
	public void testViewAllRegistrations() {
		// Check for normal view when user press
		C206_CaseStudy.doRegisterForActivity(student1.getId(), activity1.getId());
		
		String output = "MY REGISTRATIONS:\n";
		output += "Basketball (Id: 1)\n";
		
		assertEquals("View with 1 registration", output, C206_CaseStudy.viewAllRegistrations(student1.getId()));
		
		// Boundary - No registration
		C206_CaseStudy.doDeleteRegistration(student1.getId(), activity1.getId());
		String output2 = "MY REGISTRATIONS:\n";
		assertEquals("View with 0 registrations", output2, C206_CaseStudy.viewAllRegistrations(student1.getId()));		
		
		// Normal - 2 cca's
		C206_CaseStudy.doRegisterForActivity(student1.getId(), activity1.getId());
		C206_CaseStudy.doRegisterForActivity(student1.getId(), activity2.getId());
		
		String output3 = "MY REGISTRATIONS:\n";
		output3 += "Basketball (Id: 1)\n";
		output3 += "Hockey (Id: 2)\n";
		
		assertEquals("View with 2 registration", output3, C206_CaseStudy.viewAllRegistrations(student1.getId()));
	}

	@Test // @Diya
	public void testDoDeleteRegistration() {
		// Check for normal condition when deleting a registration
		C206_CaseStudy.doDeleteRegistration(student1.getId(), 1);
		assertEquals(activity1.getPendingStudents().size(),0);
		
		//Check for boundary condition for delete registration. When there isn't any registration 
		C206_CaseStudy.doDeleteRegistration(student1.getId(),3);
		assertEquals(activity1.getPendingStudents().size(),0);
		assertEquals(activity2.getPendingStudents().size(),0);
		
		//error for delete registration 
		C206_CaseStudy.doDeleteRegistration(student1.getId(),3);
		assertEquals(activity1.getPendingStudents().size(),0);
		assertEquals(activity2.getPendingStudents().size(), 0);
		
	
	}

}
