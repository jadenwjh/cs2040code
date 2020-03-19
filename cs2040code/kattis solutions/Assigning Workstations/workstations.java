import java.util.*;
import java.io.*;

public class workstations {
	public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] line=br.readLine().split(" ");
		int total=Integer.parseInt(line[0]);
		int time_logoff=Integer.parseInt(line[1]);
		int saved=0;

		//store in 2D array
		int [][] schedule = new int [total][2];
		for (int i = 0; i<total; i++) {
			String[] temp=br.readLine().split(" ");
			int arrive_at=Integer.parseInt(temp[0]);
			int stay_for=Integer.parseInt(temp[1]);
			schedule[i][0]= arrive_at;
			schedule[i][1]= stay_for;
		}
		//sort the array
		java.util.Arrays.sort(schedule, new java.util.Comparator<int[]>() {
    		public int compare(int[] a, int[] b) {
        		return Integer.compare(a[0], b[0]);
    		}
		});

		Rcher [] times = new Rcher [total];
		for (int i = 0; i < total; i++) {
			times [i]= new Rcher (schedule[i][0] , schedule[i][1]);
		}

		PriorityQueue<Integer> lab = new PriorityQueue<>();
        for(Rcher com: times) {
            boolean unoccupied_unlocked = false;
            int curr_time = com.currtime;
            int occupying_time = com.occupying;
            while(!unoccupied_unlocked && !lab.isEmpty() && lab.peek()-time_logoff <= curr_time) {
                int expiry_time = lab.poll();
                unoccupied_unlocked = expiry_time >= curr_time;
            }
            if(unoccupied_unlocked) {
                saved++;
            }
            lab.add(curr_time + occupying_time + time_logoff);
        }
        pw.print(saved);
        pw.flush();
	}

    static class Rcher {

        int currtime, occupying;

        public Rcher(int a, int b) {
            this.currtime = a;
            this.occupying = b;
        }

    }
}
