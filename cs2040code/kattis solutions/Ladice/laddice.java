import java.util.*;
import java.io.*;

public class laddice {

	public static void main (String [] args) throws IOException {
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        String [] firstinput = br.readLine().split(" ");
        int items = Integer.parseInt(firstinput[0]);
        int n = Integer.parseInt(firstinput[1]);
        int n_dwrs = 400000;
        UnionFind drawers = new UnionFind(n_dwrs);

        for (int i=0; i<items; i++) {
        	String [] input = br.readLine().split(" ");
        	int a = Integer.parseInt(input[0]);
        	int b = Integer.parseInt(input[1]);

          drawers.unionSet(a,b);

          if (drawers.increaseUsed(a)) {
            pw.print("LADICA\n");
          } else {
            pw.print("SMECE\n");
          }
        }
        pw.flush();
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
  public Boolean increaseUsed(int i) {
    int x = findSet(i);
    used[x] = used[x]+1;
    if (used[x]<=size[x]) {
      return true;
    } else {
      used[x] = used[x]-1;
      return false;
    }
  }
}