import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class coordinateMapper {
	
	public static String[][][] coorMap(File input) {
		try (Scanner mapScan = new Scanner(input)) {
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
						mapLay[l][r][c] = ".";
					}
				
				}
			}
			Scanner temp = new Scanner(input);
			//skip the guiding numbers
			temp.next();
			temp.next();
			temp.next();
			//get amount of coordinate pairs
			ArrayList<String> numCoor = new ArrayList<String>();
			while (temp.hasNext()) {
				numCoor.add(temp.next());
			}
			temp.close();
			
			String[][] coords = new String[numCoor.size()/4][4];
			for (int i = 0; i < coords.length; i++) {
				for (int j = 0; j < coords[i].length; j++) {
					coords[i][j] = mapScan.next();
				}
			}
			
			for (int r = 0; r < coords.length; r++) {
				int depth = Integer.parseInt(coords[r][3]);
				int row = Integer.parseInt(coords[r][1]);
				int col = Integer.parseInt(coords[r][2]);
					mapLay[depth][row][col] = coords[r][0];
			}
			
			return mapLay;
			
		} catch (FileNotFoundException e) {
	        return null; 
	    }
	}
		
}
