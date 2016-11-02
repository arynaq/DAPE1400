/**
 * This class behaves in almost the same way as the immediatemode class
 * The major difference being that it will only accept additional datapoints
 * if it is not in the saved state. 
 *
 * @TODO Implement, it is not used 
 * */

public class SavedMode extends Mode{

	private boolean saved;

	public SavedMode(TCPController tcp){
		super("Saved", new Tool(), new Data(), tcp);
	}

	
	/**
	 * Set it to save state, during which new additions to data is not accepted.
	 *
	 * */

	public void save(){
		saved = true;
	}

	public void resume(){
		saved = false;
	}

	public boolean isSaved(){
		return saved;
	}

	@Override
	public void addPoint(double x, double y){
		if(saved)
			return;
		else
			super.addPoint(x,y);
	}


}
