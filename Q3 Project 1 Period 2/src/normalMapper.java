import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class normalMapper {

	public static String[][][] normMap(File input) {
		try (Scanner mapScan = new Scanner(input)) {
		
		if (!mapScan.hasNextInt()) {
			throw new IncorrectMapFormatException("IncorrectMapFormatException: Incorrectly formatted maps such as not having a pair of positive numbers in the first line");
		}
			
			int rowNum = Integer.parseInt(mapScan.next());
			int colNum = Integer.parseInt(mapScan.next());
			int levelNum = Integer.parseInt(mapScan.next());
			//mapLay contains the values of all elements in the map accurately
			String[][][] mapLay = new String[levelNum][rowNum][colNum];
			
			for (int l = 0; l < levelNum; l++) {
			    for (int r = 0; r < rowNum; r++) {
			        if (!mapScan.hasNext()) {
			            throw new IncompleteMapException("IncompleteMapException: Incomplete map files such as not enough characters for a given row or too few rows");
			        }
			        String rowString = mapScan.next(); 	   
			        for (int c = 0; c < colNum; c++) {
			            mapLay[l][r][c] = String.valueOf(rowString.charAt(c));
			        }
			    }
			}
			return mapLay;
		} catch (FileNotFoundException e) {
			return null; 
		}
	}
	
}
