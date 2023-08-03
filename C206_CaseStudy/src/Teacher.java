import java.util.ArrayList;

public class Teacher extends User {
	private int activitiesId;


public Teacher(int id, String name, String email, String password, int activitiesId) {
	super(id, name, email, password, "teacher");
	
	this.activitiesId = activitiesId;
}

public int getActivitesId() {return activitiesId;}

public String toString() {
	return"";
}
}
