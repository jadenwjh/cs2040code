import java.util.*;
import java.io.*;

// just compare if adjacent nodes are adjacent to each other,
// if at least 1 pair of adjacent neighbors --> a triangle is formed == node is not weak

class weak {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		while (true) {
			int matrix = Integer.parseInt(br.readLine());
			if (matrix==-1) {
				break;
			}

			Node [] graph = new Node [matrix];

			for (int i_entry =0; i_entry<matrix; i_entry++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				if (graph[i_entry] == null) { //simultaneously form the array
					graph[i_entry] = new Node(i_entry);
				}

				for (int j_entry=0; j_entry<matrix; j_entry++) {

					if (graph[j_entry] == null) {
						graph[j_entry] = new Node(j_entry);
					}
					if (Integer.parseInt(st.nextToken()) == 1) { //link
						graph[j_entry].adj.add(graph[i_entry]);
						graph[i_entry].adj.add(graph[j_entry]);
					}
				}
			}

			for (Node key : graph) {
				if (!not_Weak(key)) { //all disjointed
					String k = Integer.toString(key.n);
					pw.print(k+" ");
				}
			}
			pw.print("\n");
		}
		pw.flush();
	}

	public static class Node {
		ArrayList<Node> adj;
		int n;

		public Node (int n) {
			this.n = n;
			this.adj = new ArrayList<Node>();
		}
	}

	public static boolean not_Weak(Node x) {
		ArrayList<Node> linked_to_node = x.adj;

		for (Node y : linked_to_node) {
			if (!Collections.disjoint(y.adj,linked_to_node)) {
				return true; //at least 2 neighbors are joint --> not weak
			}
		}
		return false; // all disjointed
	}
}