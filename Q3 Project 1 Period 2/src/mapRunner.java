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
			String[][][] mapLay = new String[levelNum][rowNum][colNum];
			for (int l = 0; l < mapLay.length; l++) {
				for (int r = 0; r < mapLay[l].length; r++) {
					for (int c = 0; c < mapLay[l][r].length; c++) {
						String temp = mapScan.next();
						if (temp == "S") {

						}
						mapLay[l][r][c] = temp;
					}
				
				}
			}
			//prints the bases
			System.out.println(rowNum);
			System.out.println(colNum);
			System.out.println(levelNum);
			//prints out the map in its entirety
			for (int l = 0; l < mapLay.length; l++) {
				for (int r = 0; r < mapLay[l].length; r++) {
				
					String res = "";
					for (int c = 0; c < mapLay[l][r].length; c++) {
						res += mapLay[l][r][c] + " ";
					}
					System.out.println(res);
				}
				System.out.println("\n");
				
			}
			
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}


}
