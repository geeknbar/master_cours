package tp16;

public class Tire extends Thread {
	
	protected int delay;
	protected Fabrication fabrication;
	
	public Tire(Fabrication f, int delay){
		this.delay = delay;
		fabrication = f;
		
	}
	
	public void run(){
		while(true){
			try {
				Thread.sleep(delay * 1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			fabrication.incrementTires();
		}
	}

}
