package tp16;

public class Body extends Thread {
	
	protected int delay;
	protected Fabrication fabrication;
	
	public Body(Fabrication f, int delay){
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
			fabrication.incrementBodies();
		}
	}
}
