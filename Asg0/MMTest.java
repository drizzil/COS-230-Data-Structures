import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MMTest extends MemMgr{

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("Please enter the correct amount of inputs on command line.");
		}
		else {
			try {
			FileReader freader1 = new FileReader(args[0]);
			BufferedReader inputFile1 = new BufferedReader(freader1);


			String line = "";

			MMInterface mmi = new MemMgr();
			while ((line = inputFile1.readLine()) != null) {
				String[] strArr = line.split(" ");
				for (int i = 0; i < strArr.length; i++) {
				}
				if (strArr[0] == "//") {
					break;
				}
				else if (strArr[0].equals("init")) {
					try {
						mmi.init(Integer.parseInt(strArr[1]));
					} catch(Exception e) {
						System.err.println("Invalid initalization input.");
					}
				}
				else if (strArr[0].equals("malloc")) {
					try {
					mmi.malloc(Integer.parseInt(strArr[1]), Integer.parseInt(strArr[2]));
					} catch(Exception e) {
						System.err.println("MyOutOfMemoryException: Allocation invalid");
					}
				}
				else if (strArr[0].equals("free")) {


					try {
						mmi.free(Integer.parseInt(strArr[1]));
					} catch(Exception e) {
						System.err.println("MyInvalidMemoryException: Memory invalid");
					}
				}
				else if (strArr[0].equals("print")) {
					mmi.print();
				}

			}
			inputFile1.close();
			} catch(IOException e) {
				System.err.println("Error: File does not exist: " + args[0]);
			}

		}

	}
}
