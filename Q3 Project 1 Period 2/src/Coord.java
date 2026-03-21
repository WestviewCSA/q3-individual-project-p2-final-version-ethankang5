
public class Coord {
   int row;
   int col;
   int lev;
   Coord parent;
   
   public Coord() {
	   row = 0;
	   col = 0;
	   lev = 0;
   }
   
   public Coord(int row, int col, int lev) {
       this.row = row;
       this.col = col;
       this.lev = lev;
   }
   
   
   Coord(int r, int c, int l, Coord parent) {
       this.row = r;
       this.col = c;
       this.lev = l;
       this.parent = parent;
   }
   
   public int getRow() {
	   return row;
   }
   
   public int getCol() {
	   return col;
   }
   
   public int getLev() {
	   return lev;
   }
   
   public void setRow(int r) {
	   this.row = r;
   }
   
   public void setCol(int c) {
	   this.col = c;
   }
   
   public void setLev(int l) {
	   this.lev = l;
   }
   
}

