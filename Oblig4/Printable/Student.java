public class Student extends Person{
	private char examScore;

	public Student(int age, String name, double height, char examScore){
		super(age,name,height);
		this.examScore = examScore;

	}


	
	private String getInfo(){
		return ", examscore: "+examScore;
	}


	
	public String toString(){
		return this.getInfo();
	}
}
