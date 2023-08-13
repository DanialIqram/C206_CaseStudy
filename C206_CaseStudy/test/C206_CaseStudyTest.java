import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class C206_CaseStudyTest {
	private static Activities activity1;
	private static Activities activity2;
	private static User account;
	private static ArrayList<Student> students;
	private static ArrayList<Teacher> teachers;
	private static ArrayList<Activities> activities;

	@Before
	public void setUp() throws Exception {
		activity1 = new Activities(1, "Basketball", "Sports", 10);
		activity2 = new Activities(2, "Hockey", "Sports", 20);
		Activities activity3 = new Activities(3, "NCC", "Uniform", 20);
		Activities activity4 = new Activities(4, "NPCC", "Uniform", 20);
		Activities activity5 = new Activities(5, "Dance", "Performing Arts", 30);

		activities = new ArrayList<Activities>();
		students = new ArrayList<>();
		teachers = new ArrayList<>();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void c206_test() {
		//fail("Not yet implemented"); 
		assertTrue("C206_CaseStudy_SampleTest ",true);
	}
	
	@Test
	public void testAddActivity() {
		//Boundary
		assertNotNull("Check for valid activities to be added to", activities);
		// Normal
		C206_CaseStudy.addActivity(activities);
		assertEquals("Check that it was added into the ArrayList",activities.size(), 1);
		//Normal
		C206_CaseStudy.addActivity(activities);
		assertEquals("Check that it was added into the ArrayList",activities.size(), 2);
		
	}
	
	@Test
	public void testViewAllActivities() {
		//Boundary
		assertNotNull("Check for valid ArrayList activities to be added to", activities); 
	}
	
	@Test
	public void testDeleteActivity() {
		// Boundary
		assertNotNull("Check for valid ArrayList activities to be added to", activities);
	}

}
