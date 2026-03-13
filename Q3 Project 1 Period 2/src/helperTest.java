import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class helperTest {
	@Test
	public void testWolverineHop() {
	    String[][][] map = { { {"@", ".", "."}, {".", "W", "."} } };
	   
	    Coord result = mapHelpers.wolverineHop(map, 0);
	   
	    assertNotNull(result, "Should find Wolverine");
	    assertEquals(1, result.getRow(), "Wolverine should be at row 1");
	    assertEquals(1, result.getCol(), "Wolverine should be at col 1");
	    assertEquals(0, result.getLev(), "Wolverine should be at lev 0");
	}
	
	@Test
	public void testCanMoveLogic() {
	    String[][][] map = { { {"@", ".", "@"}, {".", "|", "."}, {"@", "@", "@"} } };
	   
	   //location of the level hopper "|"
	    Coord center = new Coord(1, 0, 0);
	    boolean[] moves = mapHelpers.canMove(map, center);
	   
	    //North, South, East, West
	    assertFalse(moves[0], "Should NOT be able to move North to '@'");
	    assertFalse(moves[1], "Should NOT be able to move South to '@'");
	    assertTrue(moves[2], "Should be able to move East to '|' and jump");
	    assertFalse(moves[3], "Should NOT be able to move West, out of bounds");
	}
	
	@Test
	public void testBoundarySafety() {
	    String[][][] map = { { {"W", "."} } };
	   
	    //top left corner
	    Coord corner = new Coord(0, 0, 0);
	   
	    assertDoesNotThrow(() -> {
	        mapHelpers.canMove(map, corner);
	    });
	}
}

