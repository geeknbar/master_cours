package threads;

import java.util.ArrayList;

public class Prog1_3 {

	public static void main(String[] args) {
		Thread1_2 tab[] = new Thread1_2 [20];

		ArrayList<Thread1_2>l = new ArrayList<Thread1_2>();

		for (int i = 0; i < 20; i++) {
			Thread1_2 t = new Thread1_2("thread"+ i, i+1);
			l.add(t);
			t.start();
		}


		int counter = 0;
		int sum = 0;
		while (counter != 20) {
			int i = 0;
			while (i < l.size()) {
				if (l.get(i).getState() == Thread.State.TERMINATED){
				//if (!l.get(i).isAlive()){
					System.err.println("obtain result for" + l.get(i).getName());
					sum += l.get(i).getResult();
					l.remove(i);
					counter ++;
				}else{
					++i;
				}

			}
		}
		System.err.println("sum ="+sum);

	}
}
