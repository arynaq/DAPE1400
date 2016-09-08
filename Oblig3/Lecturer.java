public class Lecturer extends Person{
	private long salary;

	public Lecturer(int age, String name, long salary){
		super(age, name);
		this.salary = salary;
	}

	public int getAge(){
		return this.age;
	}
}
