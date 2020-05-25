import java.util.ArrayList;

public class MyCritterWorldTester {
    public static void main(String[] args) {
        int N = 5; // Size of grid: N x N
        int T = 10; // Max # steps to simulate (it could end earlier).

        // Place critters, rock, and escape hatch:
        ArrayList<Coord> critterStartLocations = new ArrayList<>();
        critterStartLocations.add(new Coord(0, 0));
        critterStartLocations.add(new Coord(0, 1));
        critterStartLocations.add(new Coord(0, 2));

        ArrayList<Coord> rockLocations = new ArrayList<>();
        // No rocks.

        Coord escape = new Coord(4, 4);
        CritterWorld A = new CritterWorld(N, T, critterStartLocations, rockLocations, escape);
        System.out.println(A);
        ArrayList<Cell> n = CritterWorld.getNeighbors(new Coord(0, 0), A.map);
        System.out.println("(0,0) Neighbors");
        for (Cell c : n) {
            System.out.println(c.coords);
        }

        n = CritterWorld.getNeighbors(new Coord(0, 1), A.map);
        System.out.println("(0,1) Neighbors");
        for (Cell c : n) {
            System.out.println(c.coords);
        }

        n = CritterWorld.getNeighbors(new Coord(0, 2), A.map);
        System.out.println("(0,2) Neighbors");
        for (Cell c : n) {
            System.out.println(c.coords);
        }
        
        A.map[0][0].critter.simulate(A.map);
        System.out.println(A);

        A.map[0][1].critter.simulate(A.map);
        System.out.println(A);

        A.map[0][2].critter.simulate(A.map);
        System.out.println(A);

    }

}