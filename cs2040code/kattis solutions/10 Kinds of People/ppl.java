import java.util.*;
import java.io.*;

public class ppl {

	private static int MAX = 1000, MAX2 = 2045952;
    private static String [][] grid = new String [MAX][MAX];
    private static boolean [][] visited = new boolean[MAX][MAX];
    private static int r, c;
    private static UnionFind dir = new UnionFind(MAX2);

	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    	String [] firstinput = br.readLine().split(" ");
    	r = Integer.parseInt(firstinput[0]);
    	c = Integer.parseInt(firstinput[1]);

    	for (int i=0; i<r; i++) { //form grid
    		String input = br.readLine();
    		for (int j=0; j<c; j++) {
    			String a = Character.toString(input.charAt(j));

    			grid[i][j] = a;
    			visited[i][j] = false;
    		}
    	}
        for (int ii=0; ii<r; ii++) {
            for (int jj=0; jj<c; jj++) {
                if (!visited[ii][jj]) {
                    bfs(ii,jj);
                }
            }
        }

    	int queries = Integer.parseInt(br.readLine());
    	for (int i=0; i<queries; i++) {
    		String [] currQ = br.readLine().split(" ");
    		int q_startR = Integer.parseInt(currQ[0])-1;
    		int q_startC = Integer.parseInt(currQ[1])-1;
    		int q_endR = Integer.parseInt(currQ[2])-1;
    		int q_endC = Integer.parseInt(currQ[3])-1;
            int source = hash(q_startR,q_startC);
            int end = hash(q_endR,q_endC);
            
            if (dir.isSameSet(source,end)) {
                if (grid[q_startR][q_startC].equals("0")) {
                    pw.print("binary\n");
                } else {
                    pw.print("decimal\n");
                }
            } else {
                pw.print("neither\n");
            }
    	}
    	pw.flush();
	}

    static void bfs(int row,int col) {
    	Queue<Node> qf = new LinkedList<Node>();

        if (grid[row][col].equals("1")) { //decimal
        	visited[row][col] = true;
            int root = hash(row,col);
        	qf.offer(new Node(row,col));

        	while (!qf.isEmpty()) {
        		Node k =qf.poll();

        		if (k.rowindex-1 >=0) {
    				String up = grid[k.rowindex-1][k.colindex];

    				if (!up.equals("0") && !visited[k.rowindex-1][k.colindex]) {
    					visited[k.rowindex-1][k.colindex] = true;
                        int d1 = hash(k.rowindex-1,k.colindex);
                        dir.unionSet(root,d1);
    					qf.offer(new Node(k.rowindex-1,k.colindex));
    				}
    			}

    			if (k.rowindex+1 < r) {
    				String down = grid [k.rowindex+1][k.colindex];

    				if (!down.equals("0") && !visited[k.rowindex+1][k.colindex]) {
    					visited[k.rowindex+1][k.colindex] = true;
                        int d2 = hash(k.rowindex+1,k.colindex);
                        dir.unionSet(root,d2);
    					qf.offer(new Node(k.rowindex+1,k.colindex));
    				}
    			}

    			if (k.colindex-1 >=0) {
    				String left = grid[k.rowindex][k.colindex-1];

    				if (!left.equals("0") && !visited[k.rowindex][k.colindex-1]) {
    					visited[k.rowindex][k.colindex-1] = true;
                        int d3 = hash(k.rowindex,k.colindex-1);
                        dir.unionSet(root,d3);
    					qf.offer(new Node(k.rowindex,k.colindex-1));
    				}
    			}

    			if (k.colindex+1 < c) {
    				String right = grid[k.rowindex][k.colindex+1];

    				if (!right.equals("0") && !visited[k.rowindex][k.colindex+1]) {
    					visited[k.rowindex][k.colindex+1] = true;
                        int d4 = hash(k.rowindex,k.colindex+1);
                        dir.unionSet(root,d4);
    					qf.offer(new Node(k.rowindex,k.colindex+1));
    				}
    			}
        	}
        }

        if (grid[row][col].equals("0")) { //binary
        	visited[row][col] = true;
            int bin = hash(row,col);
        	qf.offer(new Node(row,col));

        	while (!qf.isEmpty()) {
        		Node k =qf.poll();

        		if (k.rowindex-1 >=0) {
    				String up = grid[k.rowindex-1][k.colindex];

    				if (!up.equals("1") && !visited[k.rowindex-1][k.colindex]) {
    					visited[k.rowindex-1][k.colindex] = true;
                        int b1 = hash(k.rowindex-1,k.colindex);
                        dir.unionSet(bin,b1);
    					qf.offer(new Node(k.rowindex-1,k.colindex));
    				}
    			}

    			if (k.rowindex+1 < r) {
    				String down = grid [k.rowindex+1][k.colindex];

    				if (!down.equals("1") && !visited[k.rowindex+1][k.colindex]) {
    					visited[k.rowindex+1][k.colindex] = true;
                        int b2 = hash(k.rowindex+1,k.colindex);
                        dir.unionSet(bin,b2);
    					qf.offer(new Node(k.rowindex+1,k.colindex));
    				}
    			}

    			if (k.colindex-1 >=0) {
    				String left = grid[k.rowindex][k.colindex-1];

    				if (!left.equals("1") && !visited[k.rowindex][k.colindex-1]) {
    					visited[k.rowindex][k.colindex-1] = true;
                        int b3 = hash(k.rowindex,k.colindex-1);
                        dir.unionSet(bin,b3);
    					qf.offer(new Node(k.rowindex,k.colindex-1));
    				}
    			}

    			if (k.colindex+1 < c) {
    				String right = grid[k.rowindex][k.colindex+1];

    				if (!right.equals("1") && !visited[k.rowindex][k.colindex+1]) {
    					visited[k.rowindex][k.colindex+1] = true;
                        int b4 = hash(k.rowindex,k.colindex+1);
                        dir.unionSet(bin,b4);
    					qf.offer(new Node(k.rowindex,k.colindex+1));
    				}
    			}
        	}
        }        
    }

	static class Node {
		int rowindex;
		int colindex;
		public Node(int x,int y) {
			this.rowindex = x;
			this.colindex = y;
		}
	}

    private static int hash(int a, int b) { //hash for matrix position
        int res = a * ((1 << 11) - 1);
        return res ^ b;
    }
}
//----------------------------------------------------------------
class UnionFind {                             //from lecture notes                 
  public int[] p;
  public int[] rank;
  public int numSets;
  public int[] size;
  public int[] used;

  public UnionFind(int N) {
    p = new int[N];
    rank = new int[N];
    size = new int[N];
    used = new int[N];

    for (int i = 0; i < N; i++) {
      p[i] = i;
      rank[i] = 0;
      size[i] = 1;
      used[i] = 0;
    }
  }

  public int findSet(int i) { 
    if (p[i] == i) return i;
    else {
      p[i] = findSet(p[i]);
      return p[i]; 
    } 
  }

  public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

  public void unionSet(int i, int j) { 
    if (!isSameSet(i, j)) {  
      int map = findSet(i), y = findSet(j);
      // rank is used to keep the tree short
      if (rank[map] > rank[y]) {
        p[y] = map;
        size[map] += size[y];
        used[map] += used[y];
      } else { 
        p[map] = y;
        size[y] += size[map];
        used[y] +=used[map];
        if (rank[map] == rank[y]) 
          rank[y] = rank[y]+1; 
      }
    } 
  }
}