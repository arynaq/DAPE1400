public class Person{
	protected int age;
	public String name;
	private double height;


	public Person(int a, String n){
		this.age = a;
		this.name = n;
	}


	public String getInfo(){
		return "Name: "+this.name+",Age: "+this.age;
	}
}
