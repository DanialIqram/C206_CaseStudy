import java.util.ArrayList;

public class Teacher extends User {
	private int activitiesId;
	
	private ArrayList<Student> students; 
    private ArrayList<Student> pendingStudents;


public Teacher(int id, String name, String email, String password, int activitiesId) {
	super(id, name, email, password, "teacher");
	
	this.activitiesId = activitiesId;
}

public int getActivitesId() {return activitiesId;}

public ArrayList<Student> getStudents() {
    return students;
}

public ArrayList<Student> getPendingStudents() {
    return pendingStudents;
}

public String toString() {
	return"";
}
}
