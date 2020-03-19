import java.util.*;
import java.io.*;

class lost {

	static class Edge {
		int s,e,weight;
		public Edge (int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.weight = w;
		}
	}

	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		int n = Integer.parseInt(br.readLine());
		Edge [] edges = new Edge [(n*n -n)/2];
		int count = 0;

		for (int i =0; i<n; i++) {
			boolean read = false;
			String [] st = br.readLine().split(" ");
			for (int j =0; j<n; j++) {
				int curr = Integer.parseInt(st[j]);
				if (i==j) {
					read = true;
					continue;
				}
				if (read) {
					edges[count] = new Edge(i,j,curr);
					count++;
				}
			}
		}
		Comparator<Edge> comp = new Comparator<Edge>() {
			public int compare(Edge x, Edge y) {
				return x.weight - y.weight;
			}
		};
		Arrays.sort(edges, comp);

		UnionFind tree = new UnionFind(n+1);
		for (int i =0; i<(n*n -n)/2; i++) {
			Edge E = edges[i];
			int src = E.s+1;
			int des = E.e+1;
			if (!tree.isSameSet(src-1,des-1)) {
				tree.unionSet(src-1,des-1);
				pw.println(Integer.toString(src) + " " + Integer.toString(des));
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
}