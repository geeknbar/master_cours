package threads;

public class Prog1_1 {

	public static void main(String[] args) {
		Thread1_1 tab[] = new Thread1_1 [5];
		
		for (int i = 0; i < tab.length; i++) {
			tab[i] = new Thread1_1("thread" + i);
			tab[i].start();
		}
		System.err.println("Fin du programme");

	}

}
