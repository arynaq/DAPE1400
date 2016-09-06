public class Student extends Person implements Printable{
	private char examScore;

	public Student(int age, String name, double height, char examScore){
		super(age,name,height);
		this.examScore = examScore;

	}


	@Override
	public String getInfo(){
		return ", examscore: "+examScore;
	}


	
	public String toString(){
		return this.getInfo();
	}
}
