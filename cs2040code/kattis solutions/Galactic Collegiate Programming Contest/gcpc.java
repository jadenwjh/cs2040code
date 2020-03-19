import java.util.*;
import java.io.*;

class gcpc {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    HashMap<Integer,Point> pointers = new HashMap<Integer,Point>();

    String [] firstinput = br.readLine().split(" ");
    int teams = Integer.parseInt(firstinput[0]);
    int events = Integer.parseInt(firstinput[1]);

    HashMap<Integer,int []> list = new HashMap<Integer,int []>();
    HashMap<Integer,AVLTree> trees = new HashMap<Integer,AVLTree>();
    int finalrank=0;
    int maxSolved=1;

    trees.put(1, new AVLTree()); //root tree

    //every time a AVLVertex reappears, it would have solved +1, need to store in new tier, delete from old tier
    //if not, add to new tier only, no need delete

    for (int i=0;i<events;i++) {
      String [] input = br.readLine().split(" ");
      int t = Integer.parseInt(input[0]);
      int p = Integer.parseInt(input[1]);
      int [] curr = list.get(t);

      if (list.containsKey(t)) { //REAPPEARING; solved a qn, update pen

      	if (curr[0] == maxSolved) {
      		maxSolved++; //update highscore
      		trees.put(maxSolved, new AVLTree());
      	}

        curr[0]++;
        curr[1]+=p;
        list.replace(t,curr);
        int new_tier = curr[0];
        AVLNode updated_node = new AVLNode(curr[0]);

        Point pointer = new Point(updated_node);
        pointers.replace(t,pointer);

        trees.get(new_tier).insert(updated_node); //insert to new tier
        trees.get(new_tier).increase(); //increase size of new tier

        Point current = pointers.get(t);
        AVLNode del = current.node;

        trees.get(new_tier-1).delete(del); //delete from previous tier RE-implement
        trees.get(new_tier-1).decrease(); //decrease size of prev tier
      } else { //first time

        int [] solved_pen = new int[2];
        solved_pen[0] = 1;
        solved_pen[1] = p;

        AVLNode new_node = new AVLNode(p);

        list.put(t, solved_pen);
        Point new_pointer = new Point(new_node);
        pointers.put(t,new_pointer);

        trees.get(1).insert(new_node);
        trees.get(1).increase();
      }

      //calculate rank of AVLNode 1
      int [] scoreofone = list.get(1);

      if (scoreofone == null) {
      	pw.print(1+list.size());
      	pw.print("\n");
      	continue;
      }

      int tier = scoreofone[0];

      Point s = pointers.get(1);
      AVLNode one = s.node;

      int rank_in_tier= trees.get(tier).rank(one); //rank
      if (tier==maxSolved) {
        pw.print(rank_in_tier+1);
        pw.print("\n");
      } else {
        for (int k=1; k<=tier-1;k++) {
          int nodes = trees.get(k).getsize();
          finalrank+=nodes;
        }

        finalrank+=rank_in_tier;
        pw.print(finalrank+1);
        pw.print("\n");
        finalrank=0;
      }
    }
    pw.flush();
  }
}

class Point {

    AVLNode node;

    public Point(AVLNode p) {
        this.node = p;
    }
}

class AVLNode {
     
    AVLNode left, right;
    int data;
    int height;
    int atrank;
    int atsize;
 
    public AVLNode() {
    
        left = null;
        right = null;
        data = 0;
        height = 0;
        atsize = 0;
        atrank = 0;
    }
    
    public AVLNode(int n) {
    
        left = null;
        right = null;
        data = n;
        height = 0;
        atsize = 0;
        atrank=0;
    }     
}
 
class AVLTree {

 	int size=0;

 	public void increase() {
    	this.size++;
  	}

  	public void decrease() {
    	this.size--;
  	}

  	public int getsize() {
    	return this.size;
  	}
 
    private AVLNode root;     
 
    public AVLTree() {
        root = null;
    }
    
    public void insert(AVLNode data) {
    
        root = insert(data,root);
    }
    
    private int height(AVLNode t) {
    
        return t == null ? -1 : t.height;
    }
    
    private int max(int at_left,int at_right) {
    
        return at_left > at_right ? at_left : at_right;
    }
    
    private AVLNode insert(AVLNode x,AVLNode t) {
    
        if (t==null)
            t = x;
        else if (x.data<t.data) {
        
            t.left = insert(x,t.left);
            if (height(t.left) - height(t.right)==2)
                if(x.data<t.left.data) {
                    t=rotateWithLeftChild(t);
                } else {
                    t=doubleWithLeftChild(t);
                }
        	} else if(x.data>t.data) {
        
            	t.right =insert(x,t.right);
             	if(height(t.right) - height(t.left) ==2) {
                 	if(x.data>t.right.data) {
                     	t =rotateWithRightChild(t);
                 	} else {
                     	t =doubleWithRightChild(t);
                 	}
                }
        	} else {
           ;}  // Duplicate; do nothing

        t.height = max(height(t.left), height(t.right)) +1;
        t.atsize = size(t);
        t.atrank = rank(t);

        return t;
    }

    public void delete (AVLNode del) { 
      del = (del.left != null) ? del.left :del.right; //delete line
    }
   
    private AVLNode rotateWithLeftChild(AVLNode key_2) {
    
        AVLNode key_1 =key_2.left;
        key_2.left =key_1.right;
        key_1.right =key_2;
        key_2.height =max( height( key_2.left ),height(key_2.right)) +1;
        key_1.height =max( height( key_1.left ),key_2.height) +1;
        return key_1;
    }
 
    private AVLNode rotateWithRightChild(AVLNode key_1) {
    
        AVLNode key_2 =key_1.right;
        key_1.right =key_2.left;
        key_2.left =key_1;
        key_1.height =max(height(key_1.left),height(key_1.right)) +1;
        key_2.height =max(height(key_2.right),key_1.height) +1;
        return key_2;
    }

    private AVLNode doubleWithLeftChild(AVLNode key_3) {
    
        key_3.left =rotateWithRightChild(key_3.left);
        return rotateWithLeftChild(key_3);
    }
    
    private AVLNode doubleWithRightChild(AVLNode key_1) {
    
        key_1.right =rotateWithLeftChild(key_1.right);
        return rotateWithRightChild(key_1);
    }

    public int rank(AVLNode key) {
    	return rank(key,root);
  	}

  	private int rank(AVLNode key, AVLNode root) {
    	if (root == null) {
      		return 0;
    	}
    	if (root.data>key.data) {
        if (root.left == null) {
          return 0;
        } else {
          return root.left.atrank;
        }
    	} else if (root.data<key.data) {
        if (root.right == null) {
          return 0;
        } else {
          if (root.left == null) {
            return 1 + root.right.atrank;
          } else {
            return root.left.atsize + 1 + root.right.atrank;
          }
        }
    	} else {
        if (root.left == null) {
          return 0;
        } else {
          return root.left.atsize - 1;
        }
    	}
  	}

  	public int size() {
    	return size(root);
  	}

  	private int size(AVLNode x) {
    	if (x == null) {
      		return 0;
    	} else {
          if (x.left == null && x.right == null) {
            return 1;
          } else {
            if (x.left == null) {
              return 1 + x.right.atsize;
            } else if (x.right == null) {
              return 1+ x.left.atsize;
            } else {
              return x.left.atsize + 1 + x.right.atsize;
            }
          }
        }
    	}
  	}  

