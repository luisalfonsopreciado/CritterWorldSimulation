public class CoordTest {
    public static void main(String[] args) {
        Coord A = new Coord(3, 3);

        assert A.col == 3 : "Should be equal";
        assert A.row == 3 : "Should be equal";

        assert !A.isNeighbor(new Coord(0, 0)) : "Should not be neighbor";
        assert !A.isNeighbor(new Coord(3, 3)) : "Should not be neighbor";
        assert !A.isNeighbor(new Coord(1, 3)) : "Should not be neighbor";
        assert !A.isNeighbor(new Coord(1, 5)) : "Should not be neighbor";
        assert !A.isNeighbor(new Coord(5, 3)) : "Should not be neighbor";
        assert !A.isNeighbor(new Coord(5, 5)) : "Should not be neighbor";
        assert A.isNeighbor(new Coord(4, 4)) : "Should be neighbor";
        assert A.isNeighbor(new Coord(3, 2)) : "Should be neighbor";
        assert A.isNeighbor(new Coord(2, 2)) : "Should be neighbor";
        assert A.isNeighbor(new Coord(3, 4)) : "Should be neighbor";
        assert A.isNeighbor(new Coord(4, 4)) : "Should be neighbor";

        assert A.equals(new Coord(3, 3)) : "Should be equal";
        assert !A.equals(new Coord(0, 3)) : "Should not be equal";

        Coord B = new Coord(0, 0);
        assert !B.isNeighbor(new Coord(4, 1)) : "Should not be neighbor";
        assert !B.isNeighbor(new Coord(3, 1)) : "Should not be neighbor";
        
        Coord C = new Coord(0, 1);
        assert C.col == 1 : "Should be equal";
        assert C.row == 0 : "Should be equal";

        System.out.println("Tests Passed");
    }
}