import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class normalMapper {

	public static String[][][] normMap(File input) {
		try {
			Scanner mapScan = new Scanner(input);
			
			int rowNum = Integer.parseInt(mapScan.next());
			int colNum = Integer.parseInt(mapScan.next());
			int levelNum = Integer.parseInt(mapScan.next());
			//mapLay contains the values of all elements in the map accurately
			String[][][] mapLay = new String[levelNum][rowNum][colNum];
			
			for (int l = 0; l < mapLay.length; l++) {
				for (int r = 0; r < mapLay[l].length; r++) {
					for (int c = 0; c < mapLay[l][r].length; c++) {
						String temp = mapScan.next();
						mapLay[l][r][c] = temp;
					}
				
				}
				if (levelNum != 1) {
					mapScan.next();
				}
			}
			
			mapScan.close();
			//prints the bases
			System.out.println(rowNum);
			System.out.println(colNum);
			System.out.println(levelNum);
			
			
			
			return mapLay;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
