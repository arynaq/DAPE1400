public class Lecturer extends Person implements Printable{	
	private long salary;

	public Lecturer(int age, String name, double height, long salary){
		super(age,name,height);
		this.salary=salary;
	}

	
	@Override
	public String getInfo(){
		return  " salary: "+salary;
	}


	
	public String toString(){
		return this.getInfo();
	}
}
