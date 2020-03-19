import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class Apaxians {
	public static void main (String[] args){
		Scanner sc = new Scanner(System.in);
		String fullname = sc.nextLine();
		String name = "";
		String alphabet = "";
		String prevAlphabet = "";
		int size = fullname.length();

		name = name.concat(Character.toString(fullname.charAt(0)));

		for (int index = 0 ; index < size-1; index++){
			prevAlphabet = Character.toString(fullname.charAt(index));
			alphabet = Character.toString(fullname.charAt(index+1));
			if (alphabet.equals(prevAlphabet)){
				continue;
			} else{
				name = name.concat(alphabet);
			}
		}

		System.out.println(name);
	}
}

