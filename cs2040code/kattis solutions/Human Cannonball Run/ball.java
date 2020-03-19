//form a complete graph with weighted time (time)
//perform djikstra on graph

import java.util.*;
import java.io.*;
import java.math.*;

class ball {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    	String [] firstinput = br.readLine().split(" ");
    	double sx = Double.parseDouble(firstinput[0]);
    	double sy = Double.parseDouble(firstinput[1]);
    	String [] secondinput = br.readLine().split(" ");
    	double ex = Double.parseDouble(secondinput[0]);
    	double ey = Double.parseDouble(secondinput[1]);
    	int cannons = Integer.parseInt(br.readLine());
    	double [][] matrix = new double[cannons+2][cannons+2]; //add start and end nodes
    	double [] map_x = new double[cannons];
    	double [] map_y = new double[cannons];

    	double[] times = new double[cannons+2];
		for (int i = 0; i<times.length; i++) {
			times[i] = Double.POSITIVE_INFINITY;
		}
    	
    	double n = distance(sx,ex,sy,ey);
    	matrix[0][1] = walk(n);
    	matrix[1][0] = launch(n);

    	for (int i = 0; i<cannons; i++) {
    		String [] input = br.readLine().split(" ");
    		map_x[i] = Double.parseDouble(input[0]);
    		map_y[i] = Double.parseDouble(input[1]);
    	}

    	for (int i = 0; i<cannons; i++) {
			double temp = distance(sx,map_x[i],sy,map_y[i]);
			matrix[0][i+2] = walk(temp);
			matrix[i+2][0] = launch(temp);
		}

		for (int i = 0; i<cannons; i++) {
			double temp = distance(ex,map_x[i],ey,map_y[i]);
			matrix[1][i+2] = walk(temp);
			matrix[i+2][1] = launch(temp);
		}

		for (int i = 0; i<cannons; i++) {
			for (int e = 1; e<cannons; e++) {
				double temp = distance(map_x[e],map_x[i],map_y[e],map_y[i]);
				matrix[i+2][e+2] = launch(temp);
				matrix[e+2][i+2] = launch(temp);
			}
		}

		dijkstra(matrix, times);
		System.out.println(times[1]);
    }

    static double distance (double x1, double x2, double y1, double y2) {
    	return Math.hypot(Math.abs(x1-x2), Math.abs(y1-y2));
    }
    static double walk (double dis) {
    	return dis/5;
    }
    static double launch (double dis) {
    	return (Math.abs(dis-50)/5) +2;
    }

    static class Node {
		int index;
		double t;
		public Node(int i, double t) {
			this.index = i;
			this.t = t;
		}
	}

    static void dijkstra(double[][] m, double[] time) { // dijkstra ( adj matrix | time)

    	Comparator<Node> comp = new Comparator<Node>() {
			public int compare(Node x, Node y) {
				if (x.t > y.t) {
					return 1;
				} else if (x.t < y.t) {
					return -1;
				} else {
					return 0;
				}
			}
		};

		PriorityQueue<Node> pq = new PriorityQueue<Node>(comp);
		boolean[] visited = new boolean[time.length];
		for (int i = 0; i<time.length; i++) {
			visited[i] = false;
		}

		pq.add(new Node(0, 0)); // Node ( index | time )
		while (pq.peek() != null) {
			Node curr = pq.poll();
			if (visited[curr.index]) {
				continue;
			} else {
				visited[curr.index] = true;
				for (int i = 0; i<time.length; i++) {
					double curr_time = curr.t + m[curr.index][i];
					if (i != curr.index && !visited[i] && curr_time < time[i]) {
						time[i] = curr_time; //update new fastest time for this node
						pq.add(new Node(i, time[i]));
					}
				}
			}
		}
	}
}