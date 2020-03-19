import java.util.*;
import java.io.*;

public class teque {

    //push_front, push_middle and push_back pointers
    private static HashMap<Integer, Integer> front = new HashMap<>();
    private static HashMap<Integer, Integer> back = new HashMap<>();
    static int hf,tf,hb,tb = 0; //head of front,tail of front,head of back,tail of back

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //takes up more space but uses less time
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" "); //[function | number]
            if (input[0].equals("get"))
                pw.write(Integer.toString(getteque(Integer.parseInt(input[1]))) + '\n');
            else {
                switch(input[0]) {
                    case "push_front":
                        push_front(Integer.parseInt(input[1]));
                        continue;
                    case "push_back":
                        push_back(Integer.parseInt(input[1]));
                        continue;
                    case "push_middle":
                        push_middle(Integer.parseInt(input[1]));
                        continue;
                }
            }
        }
        pw.flush();
    }

    // push_front function
    private static void push_front(int x) {
        hf -= 1;
        front.put(hf, x);
        if (front.size() > back.size()) {
            hb -= 1;
            tf -= 1;
            back.put(hb, front.get(tf));
            front.remove(tf);
            return;
        }
        if (back.size() > front.size()+1) {
            front.put(tf, back.get(hb));
            hb += 1;
            tf += 1;
            back.remove(hb-1);
            return;
        }
    }

    // push_back function
    private static void push_back(int x) {
        back.put(tb, x);
        tb += 1;
        if (front.size() > back.size()) {
            hb -= 1;
            tf -= 1;
            back.put(hb, front.get(tf));
            front.remove(tf);
            return;
        }
        if (back.size() > front.size()+1) {
            front.put(tf, back.get(hb));
            hb += 1;
            tf += 1;
            back.remove(hb-1);
            return;
        }
    }

    // push_middle function
    private static void push_middle(int x) {
        if (front.size() == back.size()) {
            hb -= 1;
            back.put(hb, x);
        }
        else {
            front.put(tf, back.get(hb));
            tf += 1;
            back.put(hb, x);
        }
    }

    // get (teque) function, uses native get function
    private static int getteque(int index) {
        if (index < tf - hf)
            return front.get(hf + index);
        index -= (tf - hf);
        return back.get(hb + index);
    }
}