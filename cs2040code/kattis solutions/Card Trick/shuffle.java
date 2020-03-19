import java.util.*;

class shuffle {
	public static void main (String [] args) {
		Scanner sc = new Scanner (System.in);
		int cases = sc.nextInt();
		sc.nextLine();
		StringBuilder ans = new StringBuilder();

		for (int i = 0; cases>i; i++) {
			LinkedList<String> queue=new LinkedList<String>();
			int cards = sc.nextInt();
			int length = cards;
			sc.nextLine();

			while (cards!=0) {
				int shuffletime = cards;
				String card = Integer.toString(cards);
				queue.add(card);
				
				for (int k = 0;k<shuffletime; k++) {
					queue.add(queue.poll());
				}
				cards--;
			}
			for (int n =0; n <length ; n++) {
				ans.append(queue.removeLast()+" ");
			}
			ans.append("\n");
		}
		System.out.print(ans);
	}
}
