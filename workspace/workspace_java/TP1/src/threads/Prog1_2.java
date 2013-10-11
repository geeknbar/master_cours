package threads;

public class Prog1_2 {

	public static void main(String[] args) {
		Thread1_2 tab[] = new Thread1_2 [20];
		int results[]= new int [20];
		for (int i = 0; i < tab.length; i++) {
			tab[i] = new Thread1_2("thread" + i, i+1);
			tab[i].start();
		}


		try {
			for (int i = 0; i < tab.length; i++) {
				tab[i].join();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < tab.length; i++) {
			results[i] = tab[i].getResult();
			System.err.println(">"  + i + "=" + results[i]);
		}

		System.err.println("Fin du programme");

	}
}
