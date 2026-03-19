import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
public class mapRunner {
	
	
	
	public static void normPrinter(String[][][] map) {
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
	
	public static void coorPrinter(String[][][] map) {
		//prints unique characters back into the code
		for (int l = 0; l < map.length; l++) {
			for (int r = 0; r < map[l].length; r++) {
				
				for (int c = 0; c < map[l][r].length; c++) {
					String res = "";
					String lev = "" + l;
					String row = "" + r;
					String col = "" + c;
					res += map[l][r][c] + " " + row + " " + col + " " + lev + " ";
					System.out.println(res);
				}
				
			}
			
		}
	}
	
	
	
	
	public static boolean goalWasFound = false;

    public static void main(String[] args) {
        String mode = ""; 
        boolean showTime = false;
        boolean inCoord = false;
        boolean outCoord = false;
        String fileName = "";
        
        //arguments for program

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--Stack")) {
            	mode = "stack";
            }
            else if (args[i].equals("--Queue")) {
            	mode = "queue";
            }
            else if (args[i].equals("--Opt")) {
            	mode = "opt";
            }
            else if (args[i].equals("--Time")) {
            	showTime = true;
            }
            else if (args[i].equals("--Incoordinate")) {
            	inCoord = true;
            }
            else if (args[i].equals("--Outcoordinate")) {
            	outCoord = true;
            }
            else fileName = args[i];
        }

        // valid argument checks
        if (fileName.equals("")) {
            System.out.println("Missing command line argument for program!");
            return;
        }
        if (mode.equals("")) {
            System.out.println("Please specify between --Stack, --Queue, or --Opt");
            return;
        }

        File mapFile = new File(fileName);
        String[][][] myMap;

        if (inCoord) {
            myMap = coordinateMapper.coorMap(mapFile);
        } else {
            myMap = normalMapper.normMap(mapFile);
        }

        if (myMap == null) return;

        // checking for illegal characters
        for (int l = 0; l < myMap.length; l++) {
            for (int r = 0; r < myMap[l].length; r++) {
                for (int c = 0; c < myMap[l][r].length; c++) {
                    String tile = myMap[l][r][c];
                    if (!tile.equals("W") && !tile.equals(".") && !tile.equals("@") && 
                        !tile.equals("$") && !tile.equals("|") && !tile.equals("+")) {
                        System.out.println("IllegalMapCharacterException: Illegal character '" + tile + "' on map");
                        return;
                    }
                }
            }
        }

        //timing portion
        long start = System.nanoTime();
        
        if (mode.equals("stack")) {
            stackTraverse(myMap);
        } else {
            queueTraverse(myMap);
        }

        long end = System.nanoTime();

        if (goalWasFound) {
            if (outCoord) {
                coorPrinter(myMap);
            } else {
                normPrinter(myMap);
            }
        } else {
            System.out.println("The Wolverine Store is closed.");
        }

        if (showTime) {
            double duration = (end - start) / 1000000000.0;
            System.out.println("Total Runtime: " + duration + " seconds");
        }
    }
    
	
	public static void stackTraverse(String[][][] map) {
	    Stack<Coord> stacker = new Stack<Coord>();
	    boolean[][][] visited = new boolean[map.length][map[0].length][map[0][0].length];
	    
	    if (!mapHelpers.isWolverine(map)) {
	        return;
	    }
	    
	    Coord[][][] parentMap = new Coord[map.length][map[0].length][map[0][0].length];
	    
	    Coord start = mapHelpers.wolverineHop(map, 0);
	    stacker.push(start);
	    while (!stacker.empty()) {
	        Coord current = stacker.pop();
	        int l = current.getLev();
	        int r = current.getRow();
	        int c = current.getCol();
	        
	        if (l < 0 || l >= map.length || r < 0 || r >= map[l].length || c < 0 || c >= map[l][r].length) {
	            continue;
	        }
	        
	        if (visited[l][r][c]) {
	            continue;
	        }
	        
	        visited[l][r][c] = true;
	        if (map[l][r][c].equals("$")) {
	            System.out.println("Goal found!");
	            goalWasFound = true;
	            backtracePath(map, parentMap, current);
	            break;
	        }
	        
	        if (map[l][r][c].equals(".") || map[l][r][c].equals("|")) {
	        	//check for jump gate
	            if (!map[l][r][c].equals("|")) map[l][r][c] = "+";
	        }
	        
	        boolean[] moveKey = mapHelpers.canMove(map, current);
	        // North
	        if (moveKey[0] && r - 1 >= 0) {
	        	if (parentMap[l][r - 1][c] == null) {
	        		parentMap[l][r - 1][c] = current;
	        	}
	        	stacker.push(new Coord(r - 1, c, l));
	        }
	        // South
	        if (moveKey[1] && r + 1 < map[l].length) {
	        	if (parentMap[l][r + 1][c] == null) {
	        		parentMap[l][r + 1][c] = current;
	        	}
	        	stacker.push(new Coord(r + 1, c, l));
	        }
	        // East
	        if (moveKey[2] && c + 1 < map[l][r].length) {
	        	if (parentMap[l][r][c + 1] == null) {
	        		parentMap[l][r][c + 1] = current;
	        	}
	        	stacker.push(new Coord(r, c + 1, l));
	        }
	        // West
	        if (moveKey[3] && c - 1 >= 0) {
	        	if (parentMap[l][r][c - 1] == null) {
	        		parentMap[l][r][c - 1] = current;
	        	}
	        	stacker.push(new Coord(r, c - 1, l));
	        }
	       
	        if (moveKey[4] && l + 1 < map.length) {
	            Coord nextLevelStart = mapHelpers.wolverineHop(map, l + 1);
	            if (nextLevelStart != null) {
	            	int nextL = nextLevelStart.getLev();
		            int nextR = nextLevelStart.getRow();
		            int nextC = nextLevelStart.getCol();
		            if (parentMap[nextL][nextR][nextC] == null) {
		                parentMap[nextL][nextR][nextC] = current;
		            }
		            stacker.push(nextLevelStart);
	            }
	        }
	    }
	}
	
	public static void queueTraverse(String[][][] map) {
	    Queue<Coord> queue = new LinkedList<Coord>();
	    boolean[][][] visited = new boolean[map.length][map[0].length][map[0][0].length];
	    if (!mapHelpers.isWolverine(map)) {
	    	return;
	    }
	    
	    Coord[][][] parentMap = new Coord[map.length][map[0].length][map[0][0].length];
	    
	    Coord start = mapHelpers.wolverineHop(map, 0);
	    queue.add(start);
	    
	    while (!queue.isEmpty()) {
	        Coord current = queue.poll();
	        int l = current.getLev();
	        int r = current.getRow();
	        int c = current.getCol();
	        
	        if (l < 0 || l >= map.length || r < 0 || r >= map[l].length || c < 0 || c >= map[l][r].length) {
	            continue;
	        }
	        
	        if (visited[l][r][c]) {
	        	continue;
	        }
	        
	        visited[l][r][c] = true;
	        if (map[l][r][c].equals("$")) {
	            System.out.println("Goal found!");
	            goalWasFound = true;
	            backtracePath(map, parentMap, current);
	            break;
	        }
	        
	        if (map[l][r][c].equals(".")) {
	            map[l][r][c] = "+";
	        }
	        
	        boolean[] moveKey = mapHelpers.canMove(map, current);
	        // North
	        if (moveKey[0] && r - 1 >= 0) { 
	            if (parentMap[l][r - 1][c] == null) {
	            	parentMap[l][r - 1][c] = current;
	            }
	            queue.add(new Coord(r - 1, c, l));
	        }
	        // South
	        if (moveKey[1] && r + 1 < map[l].length) {
	            if (parentMap[l][r + 1][c] == null) {
	            	parentMap[l][r + 1][c] = current;
	            }
	            queue.add(new Coord(r + 1, c, l));
	        }
	        // East
	        if (moveKey[2] && c + 1 < map[l][r].length) {
	            if (parentMap[l][r][c + 1] == null) {
	            	parentMap[l][r][c + 1] = current;
	            }
	            queue.add(new Coord(r, c + 1, l));
	        }
	        // West
	        if (moveKey[3] && c - 1 >= 0) {
	            if (parentMap[l][r][c - 1] == null) {
	            	parentMap[l][r][c - 1] = current;
	            }
	            queue.add(new Coord(r, c - 1, l));
	        }

	        if (moveKey[4] && l + 1 < map.length) {
	            Coord nextLevel = mapHelpers.wolverineHop(map, l + 1);
	            if (nextLevel != null) {
	                int nextL = nextLevel.getLev();
	                int nextR = nextLevel.getRow();
	                int nextC = nextLevel.getCol();
	                if (parentMap[nextL][nextR][nextC] == null) {
	                	parentMap[nextL][nextR][nextC] = current;
	                }
	                queue.add(nextLevel);
	            }
	        }
	        
	    }
	}
	
	public static void backtracePath(String[][][] map, Coord[][][] parentMap, Coord goal) {
	    //clearing up to show only one optimal path
	    for (int l = 0; l < map.length; l++) {
	        for (int r = 0; r < map[l].length; r++) {
	            for (int c = 0; c < map[l][r].length; c++) {
	                if (map[l][r][c].equals("+")) {
	                    map[l][r][c] = ".";
	                }
	            }
	        }
	    }

	    // trace back from the goal to the start
	    Coord current = parentMap[goal.getLev()][goal.getRow()][goal.getCol()];
	    
	    while (current != null) {
	        int l = current.getLev(), r = current.getRow(), c = current.getCol();
	        
	        if (map[l][r][c].equals("W") && l == 0) { 
	            break; 
	        }
	        
	        if (map[l][r][c].equals(".")) {
	            map[l][r][c] = "+";
	        }
	        
	        current = parentMap[l][r][c];
	    }
	}

	
	
}

