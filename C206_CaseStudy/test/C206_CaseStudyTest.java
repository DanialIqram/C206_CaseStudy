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
		//Boundary
		assertNotNull("Check for valid activities to be added to", activities);
		
	}
	
	@Test //@Jannah
	public void testViewAllActivities() {
		//Boundary
		assertNotNull("Check for valid ArrayList activities to be added to", activities); 
	}
	
	@Test //@Jannah
	public void testDoDeleteActivity() {
		// Boundary
		assertNotNull("Check for valid ArrayList activities to be added to", activities);
	}
	
	@Test //@Regan
	public void testDoSetApprovalStatus() {
		
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
		
	}
	
	@Test // @Diya
	public void testViewAllRegistrations() {
		
	}
	
	@Test // @Diya
	public void testDoDeleteRegistration() {
		
	}

}
