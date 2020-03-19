import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class t9spelling {
  public static void main (String args[]) {
    Scanner sc = new Scanner(System.in);
    int howmany = sc.nextInt();
    sc.nextLine();
    String [] keys = {"2","22","222","3","33","333","4","44","444","5","55","555","6","66","666","7","77","777","7777","8","88","888","9","99","999","9999"};
    String ans = "";
    String reading = "";
    String prevkey = "";
    String currentA = "";
    String currentkey = "";
    int index = 0;

    for (int x=0; x<howmany;x++){
      reading = sc.nextLine();
      currentkey = "";
      prevkey = "";
      ans=ans.concat("Case #" + Integer.toString(x+1) + ": ");
      for (int n = 0; n<reading.length();n++){ 
        currentA = Character.toString(reading.charAt(n));
        if (currentA.equals(" ")){
          currentkey = " ";
        } else{
          index = reading.charAt(n) - 'a';
          currentkey = keys[index];
        }

        if (prevkey.contains(Character.toString(currentkey.charAt(0))) && prevkey!=""){ 
          ans=ans.concat(" ");
        }

        if (currentkey!=" "){
          ans=ans.concat(currentkey);
        } else {
          ans=ans.concat("0");
        }

        prevkey = currentkey;
      }
      ans=ans.concat("\n");
    }
    System.out.print(ans);
  }
}