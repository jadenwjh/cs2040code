import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class relayteam {
	public static void main (String [] args) {
		Scanner sc = new Scanner(System.in);
		int runners = sc.nextInt();
		sc.nextLine();
		List<runner> sorted2ndleg = new ArrayList<>();
		String fname="";
		double one = 0, two = 0;

		for (int i =0; i<runners;i++){             //form the arraylist of runner objs
			fname = sc.next();
			one = Double.parseDouble(sc.next());
			two = Double.parseDouble(sc.next());
			sorted2ndleg.add(new runner(fname,one,two));
			sc.nextLine();
		}

		Comparator<runner> fastest2nd = new Comparator<runner>() {         //sort by fastest 2nd leg
			public int compare(runner x,runner y){
				if (x.gettime2() < y.gettime2()) {
					return -1;
				} else if (x.gettime2() > y.gettime2()){
					return 1;
				} else {
					return 0;
				}
			}
		};

		Collections.sort(sorted2ndleg,fastest2nd);

		String firstrunner = "", current1st ="";
		List<runner> roster2nd = new ArrayList<>();
		int secondrunners = 0, n=0;
		double temp = 0, currentbesttime = Double.parseDouble("1000.00"), currenttime = 0;
		for (int k = 0; k<runners;k++) {
			current1st=sorted2ndleg.get(k).getname();
			currenttime+=sorted2ndleg.get(k).gettime1();
			while (secondrunners!=3) {             //once we find 3 fastest 2nd runners, while loop stops
				if (!sorted2ndleg.get(n).getname().equals(current1st)){    //filter out 1strunner, while loop will reiterate
					currenttime+=sorted2ndleg.get(n).gettime2();
					roster2nd.add(sorted2ndleg.get(n));
					secondrunners++;
				}
				n++;
			}
			secondrunners = 0; // comparing which team has the fastest time
			n = 0;
			roster2nd.clear();
			if (currentbesttime>currenttime){
				currentbesttime = currenttime;
				firstrunner = current1st;
			}
			currenttime=0;
		}
		System.out.println((Double.toString(currentbesttime)));
		System.out.println(firstrunner);

		int rostersize = 0;
		for (int q = 0; q<sorted2ndleg.size(); q++){
			if (!sorted2ndleg.get(q).getname().equals(firstrunner)) {
				System.out.println(sorted2ndleg.get(q).getname());
				rostersize+=1;
			}
			if (rostersize==3){
				break;
			}
		}
	}
}

class runner {
	String name;
	double time1;
	double time2;
	public runner(){
	}
	public double gettime1(){
		return time1;
	}
	public double gettime2(){
		return time2;
	}
	public String getname(){
		return name;
	}
	public runner(String name, double time1, double time2){
		this.name=name;
		this.time1=time1;
		this.time2=time2;
	}
}
