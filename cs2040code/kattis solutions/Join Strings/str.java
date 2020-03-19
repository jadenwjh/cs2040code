import java.util.*;
import java.io.*;

class Node {  // [ msg | pointer space]
    String msg;
    Node next; //reference

    public Node(String msg) {
        this.msg = msg;
    }
}

class Point {
    Node head, tail;

    public Point(Node first) {
        this.head = first;
        this.tail = first;
    }

    public void nextPoint(Point currentnode) {
        this.tail.next = currentnode.head; //updates reference
        this.tail = currentnode.tail;
    }
}

public class str {
    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //takes up more space but uses less time
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int pre = 0, second = 0;
        int n = Integer.parseInt(br.readLine());

        List<Point> seg = new ArrayList<>(n); //store words in nodes
        for (int i = 0; i < n; i++) {
            seg.add(new Point(new Node(br.readLine()))); // [ msg | pointer space] -->
        }
        for (int i = n; i > 1; i--) { //points nodes

            String [] temp = br.readLine().split(" ");

            pre = Integer.parseInt(temp[0]) - 1;
            second = Integer.parseInt(temp[1]) - 1;

            seg.get(pre).nextPoint(seg.get(second));
        }
        Node first = seg.get(pre).head; //print all the messages in linked nodes
        while (first.next != null) {
            pw.print(first.msg);
            first = first.next;
        }
        pw.print(first.msg); //prints the final message
        pw.flush();
    }
}