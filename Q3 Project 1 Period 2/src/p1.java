import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.List;

public class p1 {

    private boolean outCoord = false;
    private boolean goalWasFound = false;

    public p1() {
    	
    }
    
    public static void main(String[] args) {
        p1 project = new p1();
        project.run(args);
    }

    public void run(String[] args) {
        try {
            String mode = "";
            boolean showTime = false;
            boolean inCoord = false;
            boolean help = false;
            String fileName = "";

            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("--Stack")) {
                    mode = "stack";
                } else if (args[i].equals("--Queue")) {
                    mode = "queue";
                } else if (args[i].equals("--Opt")) {
                    mode = "opt";
                } else if (args[i].equals("--Time")) {
                    showTime = true;
                } else if (args[i].equals("--Incoordinate")) {
                    inCoord = true;
                } else if (args[i].equals("--Outcoordinate")) {
                    this.outCoord = true;
                } else if (args[i].equals("--Help")) {
                    help = true;
                } else fileName = args[args.length - 1];
            }

            if (help) {
                System.out.println("This program, given a map (txt file), shows the path with '+' in a VALID map from the wolverine to the wolverine buck" + "\n");
                System.out.println("Pick only ONE of the following algorithm switches:");
                System.out.println("--Stack             (uses stack algorithm)");
                System.out.println("--Queue             (uses Queue algorithm)");
                System.out.println("--Opt               (uses Optimal path algorithm)" + "\n");
                System.out.println("Pick ANY of these switches w/ an algorithm switch if your map satisfies their conditions");
                System.out.println("--Incoordinate      ONLY if you are providing a coordinate map");
                System.out.println("--Outcoordinate     If switched, prints back the solved map in coordinate form, otherwise it'll print the base map layout");
                System.out.println("--Time              Shows the runtime of the code");
                System.exit(0);
            }

            if (fileName.equals("") || mode.equals("")) {
                throw new RuntimeException("IllegalCommandLineInputsException: Missing required command line input argument for program");
            }

            File mapFile = new File(fileName);
            String[][][] myMap;

            if (inCoord) {
                myMap = coordinateMapper.coorMap(mapFile);
            } else {
                myMap = normalMapper.normMap(mapFile);
            }

            if (myMap == null) {
                return;
            }

            for (int l = 0; l < myMap.length; l++) {
                for (int r = 0; r < myMap[l].length; r++) {
                    for (int c = 0; c < myMap[l][r].length; c++) {
                        String tile = myMap[l][r][c];
                        if (!tile.equals("W") && !tile.equals(".") && !tile.equals("@") &&
                                !tile.equals("$") && !tile.equals("|") && !tile.equals("+")) {
                            throw new RuntimeException("IllegalMapCharacterException: Illegal characters on a map");
                        }
                    }
                }
            }

            long start = System.nanoTime();

            if (mode.equals("stack")) {
                stackTraverse(myMap);
            } else {
                queueTraverse(myMap);
            }

            long end = System.nanoTime();

            if (this.goalWasFound) {
                if (!this.outCoord) {
                    normPrinter(myMap);
                }
            } else {
                System.out.println("The Wolverine Store is closed.");
            }

            if (showTime) {
                double duration = (end - start) / 1000000000.0;
                System.out.println("Total Runtime: " + duration + " seconds");
            }

        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }

    public void normPrinter(String[][][] map) {
        for (int l = 0; l < map.length; l++) {
            for (int r = 0; r < map[l].length; r++) {
                for (int c = 0; c < map[l][r].length; c++) {
                    System.out.print(map[l][r][c]);
                }
                System.out.println();
            }
        }
    }

    public void coorPrinter(List<String> path) {
        Collections.reverse(path);
        for (String res : path) {
            System.out.println(res);
        }
    }

   

    public void stackTraverse(String[][][] map) {
        Stack<Coord> stacker = new Stack<Coord>();
        boolean[][][] visited = new boolean[map.length][map[0].length][map[0][0].length];
        Coord[][][] parentMap = new Coord[map.length][map[0].length][map[0][0].length];

        if (!mapHelpers.isWolverine(map)) return;

        Coord start = mapHelpers.wolverineHop(map, 0);
        stacker.push(start);

        while (!stacker.empty()) {
            Coord current = stacker.pop();
            int l = current.getLev();
            int r = current.getRow();
            int c = current.getCol();

            if (l < 0 || l >= map.length || r < 0 || r >= map[l].length || c < 0 || c >= map[l][r].length) continue;
            if (visited[l][r][c]) continue;

            visited[l][r][c] = true;

            if (map[l][r][c].equals("$")) {
                this.goalWasFound = true;
                backtracePath(map, l, r, c, parentMap);
                return;
            }

            boolean[] moveKey = mapHelpers.canMove(map, current);
            int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
            for (int i = 0; i < 4; i++) {
                int nr = r + dirs[i][0];
                int nc = c + dirs[i][1];
                if (moveKey[i] && nr >= 0 && nr < map[l].length && nc >= 0 && nc < map[l][nr].length) {
                    if (!visited[l][nr][nc]) {
                        if (parentMap[l][nr][nc] == null) parentMap[l][nr][nc] = current;
                        stacker.push(new Coord(nr, nc, l));
                    }
                }
            }

            if (moveKey[4] && l + 1 < map.length) {
                Coord nextLevel = mapHelpers.wolverineHop(map, l + 1);
                if (nextLevel != null) {
                    int nL = nextLevel.getLev();
                    int nR = nextLevel.getRow();
                    int nC = nextLevel.getCol();
                    if (!visited[nL][nR][nC]) {
                        if (parentMap[nL][nR][nC] == null) parentMap[nL][nR][nC] = current;
                        stacker.push(nextLevel);
                    }
                }
            }
        }
    }

    public void queueTraverse(String[][][] map) {
        Queue<Coord> queue = new LinkedList<Coord>();
        boolean[][][] visited = new boolean[map.length][map[0].length][map[0][0].length];
        Coord[][][] parentMap = new Coord[map.length][map[0].length][map[0][0].length];

        if (!mapHelpers.isWolverine(map)) return;

        Coord start = mapHelpers.wolverineHop(map, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Coord current = queue.poll();
            int l = current.getLev();
            int r = current.getRow();
            int c = current.getCol();

            if (l < 0 || l >= map.length || r < 0 || r >= map[l].length || c < 0 || c >= map[l][r].length) continue;
            if (visited[l][r][c]) continue;

            visited[l][r][c] = true;

            if (map[l][r][c].equals("$")) {
                this.goalWasFound = true;
                backtracePath(map, l, r, c, parentMap);
                return;
            }

            boolean[] moveKey = mapHelpers.canMove(map, current);
            int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
            for (int i = 0; i < 4; i++) {
                int nr = r + dirs[i][0];
                int nc = c + dirs[i][1];
                if (moveKey[i] && nr >= 0 && nr < map[l].length && nc >= 0 && nc < map[l][nr].length) {
                    if (!visited[l][nr][nc]) {
                        if (parentMap[l][nr][nc] == null) parentMap[l][nr][nc] = current;
                        queue.add(new Coord(nr, nc, l));
                    }
                }
            }

            if (moveKey[4] && l + 1 < map.length) {
                Coord nextLevel = mapHelpers.wolverineHop(map, l + 1);
                if (nextLevel != null) {
                    int nL = nextLevel.getLev();
                    int nR = nextLevel.getRow();
                    int nC = nextLevel.getCol();
                    if (!visited[nL][nR][nC]) {
                        if (parentMap[nL][nR][nC] == null) parentMap[nL][nR][nC] = current;
                        queue.add(nextLevel);
                    }
                }
            }
        }
    }

    public void backtracePath(String[][][] map, int l, int r, int c, Coord[][][] parentMap) {
        List<String> pathCoords = new ArrayList<>();
        Coord curr = parentMap[l][r][c];

        while (curr != null && !map[curr.getLev()][curr.getRow()][curr.getCol()].equals("W")) {
            int currL = curr.getLev();
            int currR = curr.getRow();
            int currC = curr.getCol();

            map[currL][currR][currC] = "+";

            if (this.outCoord) {
                pathCoords.add("+ " + currR + " " + currC + " " + currL);
            }
            curr = parentMap[currL][currR][currC];
        }

        if (this.outCoord) {
            coorPrinter(pathCoords);
        }
    }
}
