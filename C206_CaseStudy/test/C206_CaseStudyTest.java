import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class C206_CaseStudyTest {
	private static Teacher teacher;
	private static User admin;
	private static Student student;
	
	private static Activities activity1;
	private static Activities activity2;
	
	private static ArrayList<Student> students;
	private static ArrayList<Teacher> teachers;
	private static ArrayList<User> admins;
	private static ArrayList<Activities> activities;

	@Before
	public void setUp() throws Exception {
		teacher = new Teacher(1, "Mr Lim", "mrlim@school.com", "mrlim", 1);
		admin = new User(1, "Admin", "admin@school.com", "admin", "admin");
		student = new Student(1, "Jack", "jack@school.com", "jack", "1A");
		
		activity1 = new Activities(1, "Basketball", "Sports", 1);
		activity2 = new Activities(2, "Hockey", "Sports", 2);
		
		activities = new ArrayList<>();
		students = new ArrayList<>();
		teachers = new ArrayList<>();
		admins = new ArrayList<>();
		
	}

	@After
	public void tearDown() throws Exception {
		teacher = null;
		admin = null;
		student = null;
		activity1 = null;
		activity2 = null;
		activities = null;
		students = null;
		teachers = null;
		admins = null;
	}

	@Test //@Danial
	public void testDoAddUser() {
		
	}
	
	@Test //@Danial
	public void testViewUsers() {
		
	}
	
	@Test //@Danial
	public void testDoRemoveUser() {
		
	}
	
	
	@Test //@Jannah
	public void testDoAddActivity() {
		activities = new ArrayList<Activities>();
		//Boundary
		assertNotNull("Check for valid activities to be added to", activities);
		activities.clear();
		// Normal - Add an activity to an empty list and the size of the arraylist is 1
		C206_CaseStudy.doAddActivity(activities, activity1);
		assertEquals("Test that activity is added into activities ArrayList",1, activities.size());
		assertSame("Test that acivity added is the same", activities.get(0), activity1);
		// Normal - Add another activity to test the size of the ArrayList is changed to 2
		C206_CaseStudy.doAddActivity(activities, activity2);
		assertEquals("Test that activity is added into activities ArrayList",2, activities.size());
		assertSame("Test that acivity added is the same", activities.get(1), activity2);
		// Error - Adding another activity with the same name, ArrayList should remain as 2
		C206_CaseStudy.doAddActivity(activities, activity1);
		assertEquals("Test that activity is added into activities ArrayList",2, activities.size());
	}
	
	@Test //@Jannah
	public void testRetrieveAllActivities() {
		//Boundary - Check that the activities ArrayList is not null and can be added to
		assertNotNull("Check for valid ArrayList activities to be added to", activities); 
		assertEquals("Test that ArrayList is empty", 0, activities.size());
		//Boundary - When list is empty output is shown
		String allActivities = C206_CaseStudy.retrieveAllActivities(activities);
		String TestOutput = String.format("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |", "ID", "Name", "Category",
				"No. Of Students", "Max Capacity", "Available");
		TestOutput += "\n *** There is no activites ***";
		assertEquals("Test that message is shown when list is empty", TestOutput, allActivities);
		// Normal - ACtivity is added an is shown correctly
		C206_CaseStudy.doAddActivity(activities, activity1);
		String allActivities2 = C206_CaseStudy.retrieveAllActivities(activities);
		String testOutput = String.format("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |", "ID", "Name", "Category",
				"No. Of Students", "Max Capacity", "Available");
		testOutput += String.format("\n| %-20d | %-20s | %-20s | %-20d | %-20d | %-20s |", 1,"Basketball" , "Sports",
				0, 1, "Yes");
		assertEquals("Test that message is shown when list is empty", testOutput, allActivities2);
		// Normal - Add another Activity is added and is shown correctly
		C206_CaseStudy.doAddActivity(activities, activity2);
		testOutput += String.format("\n| %-20d | %-20s | %-20s | %-20d | %-20d | %-20s |", 2,"Hockey" , "Sports",
				0, 2, "Yes");
	}
	
	@Test //@Jannah
	public void testDoDeleteActivity() {
		// Boundary
		assertNotNull("Check for valid ArrayList activities to be added to", activities);
		// Normal - Delete one activity from activities
		C206_CaseStudy.doAddActivity(activities, activity1);
		C206_CaseStudy.doDeleteActivity(activities, 1);
		assertEquals("Test that ArrayList is empty", 0, activities.size());
		// Error - Delete an activity that is not in the list
		C206_CaseStudy.doDeleteActivity(activities, 2);
		assertEquals("Test that ArrayList is still empty", 0, activities.size());
		
	}
	
	@Test //@Regan
	public void testDoSetApprovalStatus() {
		//Normal
		assertEquals(activity2.getPendingStudents().size(),0);
		C206_CaseStudy.doRegisterForActivity(student1.getId(), 2);
	}
	
	@Test //@Regan
	public void testViewAllPending() {
		
	}
	
	@Test // @Regan
	public void testDoDeletePending() {
		
	}
	
	
	@Test // @Lleyton
	public void testDoAddAttendance() {
		
	}
	
	@Test // @Lleyton
	public void testViewAttendance() {
		
	}
	
	@Test // @Lleyton
	public void testDoDeleteAttendance() {
		
	}
	
	@Test // @Diya
	public void testDoRegisterForActivity() {
		activities = new ArrayList<Activities>();
		//Boundary 
		assertNotNull("Check that the no. of student increments by 1", activities);
		activities.clear();
		
	}
	
	@Test // @Diya
	public void testViewAllRegistrations() {
		
	}
	
	@Test // @Diya
	public void testDoDeleteRegistration() {
		
	}

}
