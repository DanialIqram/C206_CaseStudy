import java.util.ArrayList;
public class Activities {
private int id;
private String name;
private String category;
private int maxCapacity;
private ArrayList <Student> students;
private ArrayList <Student> pendingStudents;
private ArrayList<Student> attendance;

public Activities (int id, String name, String category, int maxCapacity) {
	this.id=id;
	this.name=name;
	this.category=category;
	this.maxCapacity=maxCapacity;
	students = new ArrayList<>();
	pendingStudents = new ArrayList<>();
	attendance = new ArrayList<>();
	
}

public int getId() {
	return id;
}
public String getName() {
	return name;
}
public String getCategory() {
	return category;
}
public int getMaxCapacity() {
	return maxCapacity;
}
public ArrayList<Student> getPendingStudents() {
	return pendingStudents;
}
public ArrayList<Student> getStudents() {
	return students;
}

public ArrayList<Student> getAttendance() {
	return attendance;
}

@Override
public String toString() {
	String isOpen = "No";
	if (maxCapacity != students.size()) {
		isOpen = "Yes";
	} else {
		isOpen = "No";
	}

	String output = String.format("| %-20d | %-20s | %-20s | %-20d | %-20d | %-20s |", id, name, category, students.size(), maxCapacity, isOpen);
	return output;
}





}
