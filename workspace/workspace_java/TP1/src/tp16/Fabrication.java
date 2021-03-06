package tp16;

public class Fabrication extends Thread {

	private int tires;
	private int motors;
	private int bodies;
	private int cars;


	public Fabrication(){
		tires = 0;
		motors = 0;
		bodies = 0;
		cars = 0;
	}
	
	public synchronized void incrementTires(){
		tires ++;
	}
	
	public synchronized void incrementBodies(){
		bodies ++;
	}
	
	public synchronized void incrementMotors(){
		motors ++;
	}
	

	public void run() {
		int times = 0;
		while(true){
			try {
				Thread.sleep(1000);
			}catch(Exception e){

			}
			synchronized (this) {
				if ((tires >=4) && (motors >=1) && (bodies >=1)){
					tires -= 4;
					motors -= 1;
					bodies -= 1;
					cars += 1;
				}
			}
			
			++times;
//			System.out.println(times + " : tires = " + tires + ", motors = " + motors + ", bodies = " + bodies + ", cars = " + cars);
			System.out.println(times + " " + tires + " " + motors + " " + bodies + " " + cars);
			
		}

	}


}
