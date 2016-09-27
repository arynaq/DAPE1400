public class Lecturer implements Greetable,Identifiable{

	private int employeeId;
	private String name;
	private String title;


	public Lecturer(int id, String name, String title){
		this.employeeId = id;
		this.name = name;
		this.title = title;

	}

	
	public String toString(){
		return "{Name: "+name+",Title: "+title+",Id: "+employeeId+"}";
	}

	@Override
	public String helloMessage(){
		return "Hello, my name is: "+this.name;
	}


	@Override
	public String byeMessage(){
		return "Bye";
	}

	@Override
	public int getId(){
		return employeeId;
	}
}
