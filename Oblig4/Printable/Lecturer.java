public class Lecturer extends Person{
	
	private long salary;

	public Lecturer(int age, String name, double height, long salary){
		super(age,name,height);
		this.salary=salary;
	}

	
	
	private String getInfo(){
		return  " salary: "+salary;
	}


	
	public String toString(){
		return this.getInfo();
	}
}
