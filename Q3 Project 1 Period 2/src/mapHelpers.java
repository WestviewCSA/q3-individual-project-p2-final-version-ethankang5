
public class mapHelpers {
	
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
	
	public static Coord wolverineHop(String[][][] map, int level) {
	    for (int r = 0; r < map[level].length; r++) {
	        for (int c = 0; c < map[level][r].length; c++) {
	            if (map[level][r][c].equals("W")) {
	                return new Coord(r, c, level);
	            }
	        }
	    }
	    return null;
	}
	public static boolean[] canMove(String[][][] map, Coord loc) {
		boolean[] direction = new boolean[5];
		
		boolean north = false;
		boolean south = false;
		boolean east = false;
		boolean west = false;
		boolean jump = false;
		
		int level = loc.getLev();
		int row = loc.getRow();
		int col = loc.getCol();
		
		// North
		if (row > 0 && !map[level][row-1][col].equals("@") && !map[level][row-1][col].equals("+")) {
		    north = true;
		}
		// South
		if (row < map[level].length - 1 && !map[level][row+1][col].equals("@") && !map[level][row+1][col].equals("+")) {
		    south = true;
		}
		// East
		if (col < map[level][row].length - 1 && !map[level][row][col+1].equals("@") && !map[level][row][col+1].equals("+")) {
		    east = true;
		}
		// West
		if (col > 0 && !map[level][row][col-1].equals("@") && !map[level][row][col-1].equals("+")) {
		    west = true;
		}
			
		
		direction[0] = north;
		direction[1] = south;
		direction[2] = east;
		direction[3] = west;
		
		if (map[level][row][col].equals("|") && level + 1 < map.length) {
		    jump = true;
		} else if (map[level][row][col].equals("W") && level > 0) {
		    jump = true;
		}
		direction[4] = jump;
		
		return direction;
	}
}
