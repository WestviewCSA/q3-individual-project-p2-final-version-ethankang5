import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class mapRunner {

	public static void main(String[] args) {
		File map = new File("MIDmap1");
		try {
			Scanner mapScan = new Scanner(map);
			
			int rowNum = Integer.parseInt(mapScan.next());
			int colNum = Integer.parseInt(mapScan.next());
			int levelNum = Integer.parseInt(mapScan.next());
			//mapLay contains the values of all elements in the map accurately
			String[][] mapLay = new String[rowNum][colNum];
			for (int r = 0; r < mapLay.length; r++) {
				
				for (int c = 0; c < mapLay[r].length; c++) {
					String temp = mapScan.next();
					mapLay[r][c] = temp;
				}
				
			}
			//prints the bases
			System.out.println(rowNum);
			System.out.println(colNum);
			System.out.println(levelNum);
			//prints out the map in its entirety
			for (int r = 0; r < mapLay.length; r++) {
				
				String res = "";
				for (int c = 0; c < mapLay[r].length; c++) {
					res += mapLay[r][c] + " ";
				}
				System.out.println(res);
				System.out.println("\n");
			}
			
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}


}
