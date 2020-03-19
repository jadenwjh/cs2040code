import java.util.*;
import java.io.*;

public class quest {
	private static TreeMap<Long,PriorityQueue<Long>> quests =new TreeMap<Long,PriorityQueue<Long>>(Collections.reverseOrder());
	private static long gold = 0;

	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        long n = Long.parseLong(br.readLine());

        for (int i=0;i<n;i++) {
        	String[] input = br.readLine().split(" "); //[function | energy | gold]
        	switch (input[0]) {
        		case "add":
        			addquest(Long.parseLong(input[1]),Long.parseLong(input[2]));
        			continue;
        		case "query":
        			killquest(Long.parseLong(input[1]));
        			pw.print(gold + "\n");
        			gold = 0;
        			continue;
        	}
        }
        pw.flush();
	}

	//implement methods
	private static void addquest(long e , long g) {
		if (quests.containsKey(e)) {
			quests.get(e).add(g);
		} else {
			PriorityQueue<Long> golds = new PriorityQueue<>(Collections.reverseOrder());
			golds.add(g);
			quests.put(e,golds);
		}
	}

	private static void killquest(long e) {

		if (quests.isEmpty()) {
			//System.out.print("case no more quests"+"\n");
			return;
		}

		long maxE = e;
		long smallestE = quests.lastKey();
		//System.out.print("case 1 " + Long.toString(smallestE) +"\n");

		if (maxE<smallestE) {
			//System.out.print("case too little energy " + Long.toString(maxE) + "\n");
			return;
		} else {
			long currE = quests.ceilingEntry(e).getKey();
			//System.out.print("case quest found, energy needed " + Long.toString(currE)+"\n");
			maxE-=currE;
			gold+=quests.get(currE).poll();

			if (quests.get(currE).isEmpty()) {
				quests.remove(currE);
				//System.out.print("case finish quest of energy " + Long.toString(currE)+"\n");
			}
			//System.out.print("case loop " + Long.toString(maxE)+"\n");
			killquest(maxE);
		}
	}
}