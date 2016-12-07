public class Person {

	private int age;
	private String name;

	public Person(int a, String n){
		this.age = a;
		this.name = n;
	}

	public void printInfo(){
		System.out.println("{name: "+name+", age: "+age+"}");
	}

	public int ageDifference(Person other) {
		return this.age - other.age;
	}

}
