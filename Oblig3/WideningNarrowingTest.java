public class WideningNarrowingTest{
	public static void main(String[] args){
		//Create direct objects
		Person p = new Person(50, "Petter");
		Lecturer l = new Lecturer(60, "Ole", 600000L);
		Student s = new Student(20, "Kjartan", 'C');
		
		//Widening
		Person p2 = l;
		Person p3 = s;
		Object o = p;



	}

	
}
