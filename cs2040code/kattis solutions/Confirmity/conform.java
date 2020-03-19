import java.util.*;
import java.io.*;

class conform {
	public static void main (String [] args) throws IOException{
		
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int n = Integer.parseInt(br.readLine());
        boolean alldiff = true;
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (int i =0;i<n;i++) {
        	StringTokenizer tk = new StringTokenizer(br.readLine());
        	int [] input = new int[5];
        	for (int e =0;e<5;e++) {
        		input[e]= Integer.parseInt(tk.nextToken());
        	}
        	quickSort(input); //implement quicksort
        	String x = Arrays.toString(input);
        	if (map.containsKey(x)) {
        		map.put(x, map.get(x) + 1);
        		alldiff = false;
        	} else {
        		map.put(x , 1);
        	}
        }

        if (alldiff) {
        	pw.print(n);
        } else {
        	//find highest value of 'map' and print
        	Map.Entry<String,Integer> firstEntry = map.entrySet().iterator().next();
        	int largestKeyValue = firstEntry.getValue();
                int mul = 1;
                String largestKey = firstEntry.getKey();

        	for (Map.Entry<String,Integer> map1 : map.entrySet()) {
        		int value = map1.getValue();
                        String key = map1.getKey();
        		if (value>largestKeyValue) {
        			largestKeyValue = value;
                                largestKey =  key;
        		}
        	}
                for (Map.Entry<String,Integer> map1 : map.entrySet()) { //more than 1 most pop
                        int value = map1.getValue();
                        String key = map1.getKey();
                        if (value==largestKeyValue && key!=largestKey){ //same value but diff keys
                                mul++;
                        }
                }
        	pw.print(largestKeyValue*mul);
        }
        pw.flush();
	}

	public static void quickSort(int[] a) {
		quickSort(a, 0, a.length-1);
	}

	public static void quickSort(int[] a, int i, int j) {
		if (i < j) {
			int pivotIdx= partition(a, i, j);
			quickSort(a, i, pivotIdx-1);
			quickSort(a, pivotIdx+1, j);
		}
	}

	public static void swap(int[] a, int pos1, int pos2) {
		int temp = a[pos1];
		a[pos1] = a[pos2];
		a[pos2] = temp;
	}

	public static int partition(int[] a, int i, int j) {
		// partition data items in a[i..j]
		int p = a[i]; // p is the pivot, the i-th item
		int m = i;    // Initially S1 and S2 are empty

		for (int k=i+1; k<=j; k++) { // process unknown region
			if (a[k] < p) { // case 2: put a[k] to S1
				m++;
				swap(a,k,m);
			}
		}

		swap(a,i,m); // put the pivot at the right place
		return m;    // m is the pivot's final position
	}
}
