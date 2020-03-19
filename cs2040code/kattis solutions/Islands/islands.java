import java.util.*;
import java.io.*;

public class islands {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    	String [] firstinput = br.readLine().split(" ");
    	int r = Integer.parseInt(firstinput[0]);
    	int c = Integer.parseInt(firstinput[1]);
    	Queue<Node> run = new LinkedList<Node>();
    	String [][] grid = new String[r][c];
    	int [][] visited = new int[r][c];
    	int number = 0;

    	for (int i=0; i<r; i++) {
    		String input = br.readLine();
    		for (int j=0; j<c; j++) {
    			String a = Character.toString(input.charAt(j));

    			grid[i][j] = a;
    			visited[i][j] = 0;
    		}
    	}

    	for (int i=0; i<r; i++) {
    		for (int j=0; j<c; j++) {
    			String curr = grid[i][j];

    			if (curr.equals("L") && visited[i][j] == 0) { //new plot
    				visited[i][j] = 1;
    				run.offer(new Node(i,j));

    				while (!run.isEmpty()) { //run BFS
    					Node k = run.poll();

    					if (k.rowindex-1 >=0) {
    						String up = grid[k.rowindex-1][k.colindex];

    						if (!up.equals("W") && visited[k.rowindex-1][k.colindex] == 0) {
    							visited[k.rowindex-1][k.colindex] = 1;
    							run.offer(new Node(k.rowindex-1,k.colindex));
    						}
    					}

    					if (k.rowindex+1 < r) {
    						String down = grid [k.rowindex+1][k.colindex];

    						if (!down.equals("W") && visited[k.rowindex+1][k.colindex] == 0) {
    							visited[k.rowindex+1][k.colindex] = 1;
    							run.offer(new Node(k.rowindex+1,k.colindex));
    						}
    					}

    					if (k.colindex-1 >=0) {
    						String left = grid[k.rowindex][k.colindex-1];

    						if (!left.equals("W") && visited[k.rowindex][k.colindex-1] == 0) {
    							visited[k.rowindex][k.colindex-1] = 1;
    							run.offer(new Node(k.rowindex,k.colindex-1));
    						}
    					}

    					if (k.colindex+1 < c) {
    						String right = grid[k.rowindex][k.colindex+1];

    						if (!right.equals("W") && visited[k.rowindex][k.colindex+1] == 0) {
    							visited[k.rowindex][k.colindex+1] = 1;
    							run.offer(new Node(k.rowindex,k.colindex+1));
    						}
    					}
    				}
    				number++;
    			}
    		}
    	}
    	System.out.println(number);
	}

	static class Node {
		int rowindex;
		int colindex;
		public Node(int x,int y) {
			this.rowindex = x;
			this.colindex = y;
		}
	}
}