package threads;

public class Thread1_2 extends Thread{
	String name;
	int result, param;
	
	public Thread1_2(String n, int k){
		super();
		name = n;
		param = k;
		result = -1;
	}
	
	public void run(){
		Fibonacci f = new Fibonacci();
		result = f.fib(param);
	}
	
	int getResult(){
		return result;
	}

}
