public class TestGreetableIdentifiable{


	public static void main(String[] args){
		Greetable g = new Student(236378,"Aryan");
		Identifiable i = new Lecturer(12, "Ole Pettersen", "Phd");


		System.out.println(g.helloMessage());
		System.out.println("ID: "+i.getId());

	}

}
