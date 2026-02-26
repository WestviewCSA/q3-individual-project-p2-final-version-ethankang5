import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class mapRunner {

	
	
	
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
	
	
	
	
	public static void main(String[] args) {
		File map = new File("MIDmap1");
		File coor = new File("MIDmapCOOR");
		//movement key
		HashMap<String, String> mapKey = new HashMap<String, String>();
		mapKey.put(".", "visit");
		mapKey.put("@", "stop");
		mapKey.put("W", "you");
		mapKey.put("$", "win");
		mapKey.put("|", "hop");
		mapKey.put("+", "visited");
		
		String[][][] myNormMap = normalMapper.normMap(map);
	    if (myNormMap != null && myNormMap[0][0][0].equals("W")) {
	        printer(myNormMap);
	    } else {
	        System.out.println("There is no Wolverine!");
	    }

	    String[][][] myCoorMap = coordinateMapper.coorMap(coor);
	    if (myCoorMap != null && myCoorMap[0][0][0].equals("W")) {
	        printer(myCoorMap);
	    } else {
	        System.out.println("There is no Wolverine!");
	    }
			
			
		
		
		

	}
	
	public void stackTraverse(HashMap<String, String> key, String[][][] map) {
		
	}
	
	public void queueTraverse(HashMap<String, String> key, String[][][] map) {
		
	}
	
	


}
