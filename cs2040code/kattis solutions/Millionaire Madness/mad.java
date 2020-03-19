import java.util.*;
import java.io.*;

public class mad {

	static class Node {
		int row, col, weight;
        Edge edge;
		public Node (int row, int col, int weight) {
			this.row = row;
			this.col = col;
			this.weight = weight;
		}
	}

    static class Edge {
        Node s, d;
        int cost;
        public Edge (Node s, Node d, int cost) {
            this.s=s;
            this.d=d;
            this.cost=cost;
        }
    }

	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    	String [] firstinput = br.readLine().split(" ");
    	int r = Integer.parseInt(firstinput[0]);
    	int c = Integer.parseInt(firstinput[1]);
    	Node [][] grid = new Node[r][c];
    	

    	for (int i=0; i<r; i++) {
    		String [] input = br.readLine().split(" ");
    		for (int j=0; j<c; j++) {
    			int w = Integer.parseInt(input[j]);
    			grid[i][j] = new Node(i,j,w);
    		}
    	}

        Comparator<Edge> comp = new Comparator<Edge>() {
            public int compare(Edge x, Edge y) {
                return x.cost - y.cost;
            }
        };

    	PriorityQueue<Edge> ladders = new PriorityQueue<Edge>(comp);
        int ans = 0;
        boolean [][] visited = new boolean [r][c];
        for (int a = 0; a<r; a++) {
            for (int b = 0; b<c; b++) {
                visited[a][b] = false;
            }
        }
    	Node start = grid[0][0];
    	Node end = grid[r-1][c-1];
    	boolean found = false;

        visited[0][0] = true;

        if (r>1) {
            Node tamp = grid[start.row+1][start.col];
            int tamp_t = tamp.weight - start.weight;
            if (tamp_t<0) {
                tamp_t = 0;
            }
            ladders.add(new Edge(start,tamp,tamp_t));
        }

        if (c>1) {
            Node temp = grid[start.row][start.col+1];
            int temp_t = temp.weight - start.weight;
            if (temp_t<0) {
                temp_t = 0;
            }
            ladders.add(new Edge(start,temp,temp_t));
        }


		while (ladders.peek()!=null && !found) {
			
            Edge x = ladders.poll();
            Node k = x.d;
            if (!visited[k.row][k.col]) {
                if (x.cost>ans) {
                    ans = x.cost;
                }
                visited[k.row][k.col] = true;
            } else {
                continue;
            }

    		if (k.row == end.row && k.col == end.col) {
    			found = true;
    			break;
    		}

    		if (k.row-1 >=0) {
    			Node up = grid[k.row-1][k.col];
    			int t = up.weight - k.weight;
    			if (t<0) {
    				t = 0;
    			}
                if (!visited[up.row][up.col]) {
                    ladders.add(new Edge(k,up,t));
                }
    		}

    		if (k.row+1 < r) {
    			Node down = grid [k.row+1][k.col];
    			int t = down.weight - k.weight;
    			if (t<0) {
    				t = 0;
    			}
                if (!visited[down.row][down.col]) {
                    ladders.add(new Edge(k,down,t));
                }
    		}

    		if (k.col-1 >=0) {
    			Node left = grid[k.row][k.col-1];
    			int t = left.weight - k.weight;
    			if (t<0) {
    				t = 0;
    			}
                if (!visited[left.row][left.col]) {
                    ladders.add(new Edge(k,left,t));
                }
    		}

    		if (k.col+1 < c) {
    			Node right = grid[k.row][k.col+1];
    			int t = right.weight - k.weight;
    			if (t<0) {
        			t = 0;
    			}
                if (!visited[right.row][right.col]) {
                    ladders.add(new Edge(k,right,t));
                }
    		}
    	}
        System.out.println(ans);
    }
}

