public class A { 
	public int x;

	public static void main(String[] args){
	 	C c = new C();
		c.test();
		B b = c;
		A a = c;

		System.out.println(c.x);
		System.out.println(b.x);
		System.out.println(a.x);
	}
}
class B extends A {
       	public int x;
}
class C extends B {
	public int x;
	public void test() {
		// Det er to måter å sette x i C inne i metoden test():
		x = 10;
		this.x = 20;

		// To måter i sette x i B
		super.x = 30;
		B b = this;
		b.x = 35;

		// En måte å sette x i A
		A a = b;
		a.x = 40;
	}

		 
}
