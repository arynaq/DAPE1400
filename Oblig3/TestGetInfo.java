public class TestGetInfo{
	public static void main(String[] args){
		Person p = new Person(50, "Petter");
		Student s = new Student(20, "Kjartan", 'C');
		Lecturer l = new Lecturer(40, "Ole", 600000L);
	

		System.out.println(s.getInfo());
		System.out.println(s.getInfo());
		System.out.println(l.getInfo());
	}


}
