public class Student extends User{
	private String classlevel;
	public Student(int id,String name, String email,String password, String classlevel) {
		super(id, name, email, password, "Student");
		this.classlevel = classlevel;
	}
}
