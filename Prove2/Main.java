public class Main{

	public static void main(String... args){
		Person p = new Person(28,"Aryan");
		Person p2 = new Person(28, "Aryan");

		assert(p.ageDifference(p2) == p2.ageDifference(p));

		p.printInfo();
		p2.printInfo();
	}

}
