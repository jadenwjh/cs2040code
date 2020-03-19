// 3 inputs X Y N
// X is number divisible by fizz
// Y is number divisible by buzz
// N is limit of numbers from 1
// if both divisible then use fizzbuzz
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class fizzbuzz {
	public static void main(String args[]) {
		Scanner sc = new Scanner (System.in);
		int fizz = sc.nextInt();
		int buzz = sc.nextInt();
		int numbers = sc.nextInt();
		String listofnumbers = "";

		for  (int i=0; i<numbers; i++){
			int num = i+1;
			if (num%fizz==0 && num%buzz==0){
				listofnumbers = listofnumbers.concat("Fizzbuzz");
			} else if (num%fizz==0){
				listofnumbers = listofnumbers.concat("Fizz");
			} else if (num%buzz==0){
				listofnumbers = listofnumbers.concat("Buzz");
			} else {
				String x = Integer.toString(num);
				listofnumbers = listofnumbers.concat(x);
			}
			listofnumbers = listofnumbers.concat("\n");
		}
		System.out.print(listofnumbers);
	}
}

2 3 1 2
x.x
.x.