import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class normalMapper {

	public static String[][][] normMap(File input) {
		Scanner mapScan;
		try {
			mapScan = new Scanner(input);
		} catch (Exception e) {
			return null;
		}
		
		if (!mapScan.hasNextInt()) {
			throw new IncorrectMapFormatException("IncorrectMapFormatException: Incorrectly formatted maps such as not having a pair of positive numbers in the first line");
		}
			
			int rowNum = Integer.parseInt(mapScan.next());
			int colNum = Integer.parseInt(mapScan.next());
			int levelNum = Integer.parseInt(mapScan.next());
			//mapLay contains the values of all elements in the map accurately
			String[][][] mapLay = new String[levelNum][rowNum][colNum];
			
			for (int l = 0; l < mapLay.length; l++) {
				for (int r = 0; r < mapLay[l].length; r++) {
					for (int c = 0; c < mapLay[l][r].length; c++) {
						if (!mapScan.hasNext()) {
							throw new IncompleteMapException("IncompleteMapException: Incomplete map files such as not enough characters for a given row or too few rows");
						}
						String temp = mapScan.next();
						mapLay[l][r][c] = temp;
					}
				
				}
				
			}	
			mapScan.close();
			
			return mapLay;
	}
	
}
