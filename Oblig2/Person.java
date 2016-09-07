public class Person{
	private int age;
	private String name;
	
	public Person(int a, String n){
		this.age = a;
		this.name = n;
	}


	public void printInfo(){
		System.out.println("Name: "+this.name+", Age: "+age);
	}
	
	public int ageDifference(Person other){
		if(other == null)
			throw new IllegalArgumentException("The other person is null, come on dude..");
		return this.age - other.age;
	}
}
