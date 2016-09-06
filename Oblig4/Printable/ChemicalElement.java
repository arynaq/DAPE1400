public class ChemicalElement extends Printable{
	
	private int atomicNr;
	private String symbol;
	private int group;
	private int period;



	public ChemicalElement(int atomicNr, String symbol, int group, int period){
		this.atomicNr = atomicNr;
		this.symbol = symbol;
		this.group = group;
		this.period = period;
	}


	@Override 
	public String getInfo(){
		return "{N: "+atomicNr+", Symbol: "+symbol+", Group: "+group+", Period: "+period+"}";
	}


}
