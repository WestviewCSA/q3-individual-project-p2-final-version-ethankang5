import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class queueTest {
	@Test
   public void testEfficiency() {
      
       String[][][] map = {{ {"W", ".", "$"}, {".", "@", "@"}, {".", ".", "."} }};
       mapRunner.queueTraverse(map);
      
       assertEquals("+", map[0][0][1], "Queue should take the direct path to buck");
   }
   @Test
   public void testJump() {
      
       String[][][] map = { { {"W", "|"} }, { {"W", "$"} } };
       mapRunner.queueTraverse(map);
       assertEquals("$", map[1][0][1], "Goal should be reachable with pipe");
   }
   @Test
   public void testWall() {
       String[][][] map = {{ {"W", "@", "$"} }};
       mapRunner.queueTraverse(map);
       // The buck is behind a wall. The queue should empty and the method should return.
       assertEquals("$", map[0][0][2], "Buck should remain '$'");
       assertEquals("@", map[0][0][1], "Wall should remain '@'");
   }
}

