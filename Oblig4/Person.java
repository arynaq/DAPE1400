public abstract class Person{
	protected int age;
	protected String name;
	protected double height;

	public Person(int age, String name, double height){
		this.age = age;
		this.name = name;
		this.height = height;
	}

	
	public abstract String getInfo();
}
