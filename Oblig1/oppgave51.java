public class oppgave51{
	
	public static void main(String[] args){
		// Oppgave 1
		int[] tab = {9,8,7,6,5,4,3,2,1,0};

		for(int i=0; i<tab.length; i++)
			System.out.print(tab[i]+",");
		
		System.out.println("\n");
		// Oppgave 2
		for(int i=tab.length-1; i>=0; i--)
			System.out.print(tab[i]+",");
		
		System.out.println("\n");
		// Oppgave 3
		for(int i=0; i<tab.length; i+=2)
			System.out.print(tab[i]+",");

	}
}

