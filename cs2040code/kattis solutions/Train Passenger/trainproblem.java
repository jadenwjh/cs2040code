import java.util.Scanner;

// at every time the number of people in the train did not exceed the capacity nor was below 0
// no passenger waited in vain (i.e., waited on the station when there was room in the train)
// train should start and finish the journey empty
// in particular passengers should not wait for the train at the last station.

//first line has 2 integers x y. x = capacity of train. y = number of stations
//subsequent line should = y
//each line has 3 integers a b c. a = number leaving train. b = number entering train. c = number waiting at station

class trainproblem {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		int traincap = sc.nextInt();
		int stations = sc.nextInt();
		sc.nextLine();
		int leaving = 0, entering = 0, waiting = 0;
		boolean notnegative = true, notexceedcap = true, notwaitinginvain = true, emptylaststation = false, pass = true, failed = false, emptytrain=false;

		int train = 0;

		for (int i=0 ; i<stations; i++){
			leaving = sc.nextInt();
			entering = sc.nextInt();
			waiting = sc.nextInt();

			train-=leaving;
			if (train<0) {
				notnegative = false;
			}
			train+=entering;
			if (train>traincap) {
				notexceedcap = false;
			}
			if (train!=traincap && waiting!=0) {
				notwaitinginvain = false;
			}
			if (!failed) {
				if (!notnegative || !notexceedcap|| !notwaitinginvain) {
					pass = false;
					failed = true;
				}
			}
			sc.nextLine();
		}

		if (waiting==0) {
			emptylaststation=true;
		}
		if (train==0) {
			emptytrain=true;
		}
		if (emptylaststation && pass &&emptytrain){
			System.out.println("possible");
		} else {
			System.out.println("impossible");
		}
	}
}