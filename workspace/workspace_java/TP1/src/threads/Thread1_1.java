package threads;

public class Thread1_1 extends Thread{

	String name;
	
	public Thread1_1(String n){
		super();
		name = n;
	}
	
	public void run(){
		Fibonacci f = new Fibonacci();
		for (int i = 1; i <= 10; i++){
			System.err.println(name+ "i" + "=" + f.fib(i));
		}
	}
}
