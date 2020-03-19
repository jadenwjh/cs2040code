import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class sorted {
	public static void main (String [] args) {
		Scanner sc = new Scanner (System.in);
		String name="";
		int numbernames=0;
		boolean iszero = false;
		ArrayList<String> finalnamelist = new ArrayList<>();

		Comparator<String> sortname = new Comparator<String>() {
			public int compare(String x,String y){
				if (x.charAt(0) - y.charAt(0) ==0) { //if char is infront
					return x.charAt(1) - y.charAt(1);
				} else {
					return x.charAt(0) - y.charAt(0);
				}
			}
		};
		while (!iszero) {
			numbernames = sc.nextInt();
			if (numbernames == 0){
				iszero=true;
				finalnamelist.remove(finalnamelist.size()-1);
			} else {
				sc.nextLine();
				ArrayList<String> namelist = new ArrayList<>();
				for (int i =0;i<numbernames;i++){
					name=sc.next();
					namelist.add(name);
					sc.nextLine();
				}
				Collections.sort(namelist,sortname);

				for (int k =0; k<namelist.size();k++){
					finalnamelist.add(namelist.get(k));
				}
				finalnamelist.add(" ");
			}
		}
		for (int w = 0; w<finalnamelist.size();w++){
			if (finalnamelist.get(w)!=" "){
				System.out.println(finalnamelist.get(w));
			} else{
				System.out.println();
			}
		}
	}
}