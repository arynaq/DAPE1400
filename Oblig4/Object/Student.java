public class Student extends Person{
	private char examScore;

	public Student(int age, String name, double height, char examScore){
		super(age,name,height);
		this.examScore = examScore;

	}


	@Override
	public String getInfo(){
		return super.getInfo()+", examscore: "+examScore;
	}

}
