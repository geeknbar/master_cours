package threads;

public class Fibonacci {
	
	public final static int FIB_1 = 1;
	public final static int FIB_2 = 1;
	
	public Fibonacci(){
		
	}
	
	public int fib(int n){
		if(n==1) return FIB_1;
		if(n==2) return FIB_2;
		return fib(n-1) + fib(n-2);
	}

}
