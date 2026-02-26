import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class mapRunner {

	
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
	
	public static void printer(String[][][] map) {
		//prints out the map in its entirety
		for (int l = 0; l < map.length; l++) {
			for (int r = 0; r < map[l].length; r++) {
			
				String res = "";
				for (int c = 0; c < map[l][r].length; c++) {
					res += map[l][r][c] + " ";
				}
				System.out.println(res);
			}
			System.out.println("\n");
			
		}
	}
	
	public static String[][][] coorMap(File input) {
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
						mapLay[l][r][c] = ".";
					}
				
				}
				if (levelNum != 1) {
					mapScan.next();
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
	
	
	public static void main(String[] args) {
		File map = new File("MIDmap1");
		File coor = new File("MIDmapCOOR");
		String[][][] myNormMap = normMap(map);
	    if (myNormMap != null && myNormMap[0][0][0].equals("W")) {
	        printer(myNormMap);
	    } else {
	        System.out.println("There is no Wolverine!");
	    }

	    String[][][] myCoorMap = coorMap(coor);
	    if (myCoorMap != null && myCoorMap[0][0][0].equals("W")) {
	        printer(myCoorMap);
	    } else {
	        System.out.println("There is no Wolverine!");
	    }
			
			
		
		
		

	}
	
	


}
