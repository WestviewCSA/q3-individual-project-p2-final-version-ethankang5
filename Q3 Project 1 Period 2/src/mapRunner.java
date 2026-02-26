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
		File map = new File("HARDmap4");
		File coor = new File("MIDmapCOOR");
		//movement key
		HashMap<String, Integer> mapKey = new HashMap<String, Integer>();
		//can still move
		mapKey.put(".", 5);
		mapKey.put("|", 4);
		//blocked way
		mapKey.put("@", 3);
		mapKey.put("W", 2);
		mapKey.put("+", 1);
		//finish map
		mapKey.put("$", 0);
		
		String[][][] myNormMap = normalMapper.normMap(map);
	    if (myNormMap != null && normalMapper.isWolverine(myNormMap)) {
	        printer(myNormMap);
	    } else {
	        System.out.println("There is no Wolverine!");
	    }

	    String[][][] myCoorMap = coordinateMapper.coorMap(coor);
	    if (myCoorMap != null && coordinateMapper.isWolverine(myCoorMap)) {
	        printer(myCoorMap);
	    } else {
	        System.out.println("There is no Wolverine!");
	    }
			
			
		
		
		

	}
	
	public void stackTraverse(HashMap<String, Integer> key, String[][][] map) {
		
	}
	
	public void queueTraverse(HashMap<String, Integer> key, String[][][] map) {
		
	}
	
	


}
