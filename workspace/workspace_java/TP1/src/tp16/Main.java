package tp16;

public class Main {
	
	public final static int NBR_TIRE_FACTORIES = 3;
	public final static int NBR_MOTOR_FACTORIES = 3;
	public final static int NBR_BODY_FACTORIES = 1;
	
	public final static int TIME_TIRE_FACTORIES = 1; /* min */
	public final static int TIME_MOTOR_FACTORIES = 5;
	public final static int TIME_BODY_FACTORIES = 4;

	public static void main(String[] args) {
				Tire tf[] = new Tire[NBR_TIRE_FACTORIES];
				Body bf[] = new Body[NBR_BODY_FACTORIES];
				Motor mf[] = new Motor[NBR_MOTOR_FACTORIES];
				Fabrication f = new Fabrication();
				
				for(int i=0; i<tf.length;i++){
					tf[i]= new Tire(f,TIME_TIRE_FACTORIES);
				}
				for(int i=0; i<bf.length;i++){
					bf[i]= new Body(f,TIME_BODY_FACTORIES);
				}
				for(int i=0; i<mf.length;i++){
					mf[i]= new Motor(f,TIME_MOTOR_FACTORIES);
				}
				
				f.start();
				
				for( Tire t : tf ) t.start();
				for( Motor m : mf ) m.start();
				for( Body b : bf ) b.start();
				
				//utilisation de gnuplot
				//lancer gnuplot
				//plot "data.txt" using 1:2 with lines, "data.txt" using 1:3 with lines, "data.txt" using 1:4 with lines, "data.txt" using 1:5 with lines
				//data.txt de la forme
				// 1 2 0 0 0
				// 1 6 0 0 3
				// 6 2 0 5 0
				// 2 2 3 0 1
				// 4 5 0 0 0
				
				
	}

}
