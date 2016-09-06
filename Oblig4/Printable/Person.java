public class Person extends Printable{
	protected int age;
	protected String name;
	protected double height;




	public Person(int age, String name, double height){
		this.age = age;
		this.name = name;
		this.height = height;
	}


	@Override
	public String getInfo(){
		return "Name,Age,Height: {"+name+","+age+","+height;
	}
}
