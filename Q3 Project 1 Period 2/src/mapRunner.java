import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
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
		File coor = new File("HARDmapCOOR");
		
		
		String[][][] myNormMap = normalMapper.normMap(map);
	    String[][][] myCoorMap = coordinateMapper.coorMap(coor);
	    queueTraverse(myNormMap);
	    printer(myNormMap);
			
			
		
		
		
	}
	
	public static void stackTraverse(String[][][] map) {
	    Stack<Coord> stacker = new Stack<Coord>();
	    boolean[][][] visited = new boolean[map.length][map[0].length][map[0][0].length];
	    if (!mapHelpers.isWolverine(map)) {
	        return;
	    }
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
	            return;
	        } 
	        if (map[l][r][c].equals(".") || map[l][r][c].equals("|")) {
	        	//check for jump gate
	            if (!map[l][r][c].equals("|")) map[l][r][c] = "+";
	        }
	        boolean[] moveKey = mapHelpers.canMove(map, current);
	        // North
	        if (moveKey[0]) stacker.push(new Coord(r - 1, c, l));
	        // South
	        if (moveKey[1]) stacker.push(new Coord(r + 1, c, l));
	        // East
	        if (moveKey[2]) stacker.push(new Coord(r, c + 1, l));
	        // West
	        if (moveKey[3]) stacker.push(new Coord(r, c - 1, l));
	       
	        if (moveKey[4]) {
	            Coord nextLevelStart = mapHelpers.wolverineHop(map, l + 1);
	            if (nextLevelStart != null) {
	                stacker.push(nextLevelStart);
	            }
	        }
	    }
	}
	
	public static void queueTraverse(String[][][] map) {
	    Queue<Coord> queue = new LinkedList<Coord>();
	    boolean[][][] visited = new boolean[map.length][map[0].length][map[0][0].length];
	    if (!mapHelpers.isWolverine(map)) return;
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
	        if (visited[l][r][c]) continue;
	        visited[l][r][c] = true;
	        if (map[l][r][c].equals("$")) {
	            System.out.println("Goal found!");
	            return;
	        }
	        if (map[l][r][c].equals(".")) {
	            map[l][r][c] = "+";
	        }
	        boolean[] moveKey = mapHelpers.canMove(map, current);
	        // North
	        if (moveKey[0]) queue.add(new Coord(r - 1, c, l));
	        // South
	        if (moveKey[1]) queue.add(new Coord(r + 1, c, l));
	        // East
	        if (moveKey[2]) queue.add(new Coord(r, c + 1, l));
	        // West
	        if (moveKey[3]) queue.add(new Coord(r, c - 1, l));
	        if (moveKey[4]) {
	            Coord nextLevel = mapHelpers.wolverineHop(map, l + 1);
	            if (nextLevel != null) {
	                queue.add(nextLevel);
	            }
	        }
	    }
	}

	
	
}

