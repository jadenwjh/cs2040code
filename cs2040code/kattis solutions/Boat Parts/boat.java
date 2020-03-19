import java.util.*;
import java.io.*;

class boat {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		String[] line=br.readLine().split(" ");
		int parts = Integer.parseInt(line[0]);
		int days = Integer.parseInt(line[1]);
		int newpart = 0;
		boolean noparadox = true;
		boolean found = false;
		HashMap<String, Integer> boat = new HashMap<>();

		for (int i = 0;i<days; i++) {
			String curr_part = br.readLine();
			if (boat.containsKey(curr_part)) {
				continue;
			} else {
				boat.put(curr_part, 0);
				newpart++;
			}

			if (newpart == parts && !found) {
				pw.print(i+1);
				noparadox=false;
				found=true;
			}
		}
		if (noparadox) {
			pw.print("paradox avoided");
		}
		pw.flush();
	}
}