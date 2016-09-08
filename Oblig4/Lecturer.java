public class Lecturer extends Person{
	private long salary;

	public Lecturer(int age, String name, double height, long salary){
		super(age, name, height);
		this.salary = salary;
	}

	@Override
	public String getInfo(){
		return "Name: "+this.name+", Age: "+this.age+", Height: "+this.height+", Salary: "+salary;
	}


}
