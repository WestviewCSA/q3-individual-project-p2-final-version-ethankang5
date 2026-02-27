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
	
	public static boolean isWolverine(String[][][] map) {
		for (int l = 0; l < map.length; l++) {
			for (int r = 0; r < map[l].length; r++) {
				for (int c = 0; c < map[l][r].length; c++) {
					if (map[l][r][c].equals("W")) {
						return true;
					}
				}
			}
			
		}
		return false;
	}
	
	public static int[] wolverineHop(String[][][] map, int level) {
		int[] location = new int[2];
		for (int r = 0; r < map[level].length; r++) {
			for (int c = 0; c < map[level][r].length; c++) {
				if (map[level][r][c].equals("W")) {
					location[0] = r;
					location[1] = c;
					return location;
				}
			}
		}
		return location;
	}
	
	public static boolean[] canMove(String[][][] map, int level, int row, int col) {
		boolean[] direction = new boolean[4];
		
		boolean north = false;
		boolean west = false;
		boolean south = false;
		boolean east = false;
		//north & south
		if (row > 0 && row < map[level].length) {
			if (!map[level][row-1][col].equals("@") || !map[level][row-1][col].equals("+")) {
				north = true;
			}
			if (!map[level][row+1][col].equals("@") || !map[level][row+1][col].equals("+")) {
				south = true;
			}
		}
		//west and east
		if (col > 0 && col < map[level][row].length) {
			if (!map[level][row][col-1].equals("@") || !map[level][row][col-1].equals("+")) {
				west = true;
			}
			if (!map[level][row][col+1].equals("@") || !map[level][row][col+1].equals("+")) {
				east = true;
			}
		}
		
		direction[0] = north;
		direction[1] = west;
		direction[2] = south;
		direction[3] = east;
		
		return direction;
	}
}
