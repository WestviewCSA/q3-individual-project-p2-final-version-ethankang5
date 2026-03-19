import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class stackTest {
	@Test
   public void testPath() {
       String[][][] testMap = { { {"W", ".", "."}, {"@", ".", "@"}, {"$", ".", "."} } };
       p1.stackTraverse(testMap);
       //check if traversal makes it around the wall
       assertEquals("+", testMap[0][0][1], "Tile (0,1) should be marked as visited");
       assertEquals("+", testMap[0][1][1], "Tile (1,1) should be marked as visited");
      
       //Wolverine and buck should NOT be changed
       assertEquals("$", testMap[0][2][0], "Buck symbol should remain '$'");
       assertEquals("W", testMap[0][0][0], "Wolv symbol should remain 'W'");
   }
   @Test
   public void testImpossiblePath() {
       String[][][] blockedMap = { { {"W", ".", "@"}, {"@", "@", "@"}, {"$", ".", "."} } };
       p1.stackTraverse(blockedMap);
       assertEquals(".", blockedMap[0][2][1], "Blocked area should remain '.'");
   }
   @Test
   public void testMultiLevelJump() {
   	//map has 2 levels
       String[][][] levelMap = { {  {"W", "|"} }, {  {"W", "$"} } };
       p1.stackTraverse(levelMap);
       //check if properly uses portal
       assertEquals("$", levelMap[1][0][1], "Goal on second level index 1 should be found");
   }
}
