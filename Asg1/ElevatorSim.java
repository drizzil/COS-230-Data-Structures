import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ElevatorSim {

	public static void main(String[] args) {

		PriQue<String> pass = new ElevSim<String>();
		
		int floors = 9;
		if (args.length != 2) {
			System.err.println("Error: Please enter the correct amount of files.");
		}
		else {
			try {
			FileReader freader1 = new FileReader(args[1
			                                          ]);
			BufferedReader inputFile1 = new BufferedReader(freader1);
			
			
			String line = "";
			int id = 0;
			try {
				floors = Integer.parseInt(args[0]);
				//((ElevSim<String>) pass).runElevator(floors);
			} catch(Exception e) {
				System.err.println("Invalid input: " + args[0]);
			}
			while ((line = inputFile1.readLine()) != null) {
				
				String[] strArr = line.split(" ");
				for (int i = 0; i < strArr.length; i++) {
				}
				if (strArr[0].equals("//")) {
				}
				else {
					try {
						if (Integer.parseInt(strArr[1]) == 0) {
							System.err.println("Invalid input, desintation floor must greater than 1");
						}
						else if (Integer.parseInt(strArr[1]) > floors) {
							System.err.println("Invalid input, desintation floor must less than " + floors);
						}
						else {
							pass.insert(id, line);
							id++;
						}
					} catch(Exception e) {
						System.err.println("Invalid input.");
					}
				}
				
			}
			inputFile1.close();
			} catch(IOException e) {
				System.err.println("Error: File does not exist: " + args[1]);
			}
			
		}
		try {
			//int floors = Integer.parseInt(args[0]);
			((ElevSim<String>) pass).runElevator(floors);
		} catch(Exception e) {
			System.err.println("Invalid input: " + args[0]);
		}
		
		
		
		
	}

}
