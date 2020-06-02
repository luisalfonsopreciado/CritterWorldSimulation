public class CellTest {
    public static void main(String[] args) {
        Cell A = new Cell(3,3);

        assert A.coords.row == 3 && A.coords.col == 3 : "Should be equal";
        assert !A.equals(new Cell(0,3)) : "Should not be equal";
        A.setOccupant("critter");
        assert A.isOccupied() : "Should be occupied";
        assert A.getOccupant().equals("critter") : "Should match"; 
        assert A.toString().equals("S 1") : "Should match";
         System.out.println("All Tests Passed");
    }
}