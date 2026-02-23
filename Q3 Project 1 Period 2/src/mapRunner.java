import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class mapRunner {

	public static void main(String[] args) {
		File map = new File("EZmap1");
		try {
			Scanner mapScan = new Scanner(map);
			while(mapScan.hasNext()) {
				System.out.println(mapScan.next());
			}
			
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
