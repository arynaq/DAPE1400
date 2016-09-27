public class Student implements Greetable, Identifiable{

	private int studentId;
	private String name;

	public Student(int id, String name){
		this.studentId = id;
		this.name = name;
	}
	
	public String toString(){
		return "{Name: "+name+",id: "+studentId;
	}

	@Override
	public String helloMessage(){
		return "Hello, my name is: "+this.name;
	}


	@Override
	public String byeMessage(){
		return "Bye!";
	}


	@Override
	public int getId(){
		return this.studentId;
	}
}
