public class Student extends Person{
	private char examScore;

	public Student(int age, String name, char examScore){
		super(age, name);
		this.examScore = examScore;
	}


	public void setAge(int age){
		this.age = age;
	}

	@Override
	public String getInfo(){
		return super.getInfo() + ","+"Grade: "+this.examScore;
	}


}
