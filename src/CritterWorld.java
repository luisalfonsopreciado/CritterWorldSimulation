import java.util.*;

public class CritterWorld {
    static ArrayList<Critter> escapedCritters;
    int N, T;
    Coord escape;
    ArrayList<Coord> rockCoords;
    public ArrayList<Cell> activeCritters;
    int numCritters;
    Cell[][] map;

    public CritterWorld(int N, int T, ArrayList<Coord> critterStartCoords, ArrayList<Coord> rockCoords, Coord escape) {
        this.N = N;
        this.escape = escape;
        this.numCritters = critterStartCoords.size();
        this.rockCoords = rockCoords;
        this.map = new Cell[N][N];
        this.escapedCritters = new ArrayList<Critter>();
        this. activeCritters = new ArrayList<Cell>();

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                map[row][col] = new Cell(row, col);
            }
        }

        for (Coord c : critterStartCoords) {
            map[c.row][c.col].setOccupant(Cell.CRITTER);
            activeCritters.add(map[c.row][c.col]);
        }

        for (Coord c : rockCoords) {
            map[c.row][c.col].setOccupant(Cell.ROCK);
        }

        map[escape.row][escape.col].setOccupant(Cell.HATCH);

    }

    public String toString() {
        String s = "----------------- CritterWorld ----------------\n";
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                s += "    " + map[row][col];
            }
            s += "\n";
        }
        return s;
    }

    // Return true if over.

    public boolean nextStep() {
        for(Cell critterCell: activeCritters){
            critterCell.simulate(map);
        }
    
        return checkIsOver();
    }

    public void shuffle(){
        Collections.shuffle(activeCritters);
    }

    public void printStats() {
        // ... Print the list of critters that escaped ...
        System.out.print("Escaped critters: " + escapedCritters.size());

        // ... Print the list of critters that escaped ...
        for (Critter c : escapedCritters) {
            System.out.print(c);
        }
        System.out.println();
    }

    public static ArrayList<Cell> getNeighbors(Coord location, Cell[][] map) {
        ArrayList<Cell> result = new ArrayList<Cell>();
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (location.isNeighbor(map[row][col].coords) && !map[row][col].isOccupied()) {
                    result.add(map[row][col]);
                }

            }
        }
        return result;

    }

    public static boolean hasFoundLeader(Coord location, Cell[][] map) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                Cell current = map[row][col];
                if (location.isNeighbor(current.coords) && current.getOccupant().equals(Cell.CRITTER)) {
                    if (current.critter.status == 'L')
                        return true;

                }
            }
        }
        return false;
    }

    public static ArrayList<Cell> getSearchingCritters(Coord location, Cell[][] map) {
        ArrayList<Cell> result = new ArrayList<Cell>();
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                Cell current = map[row][col];
                if (current.getOccupant().equals(Cell.CRITTER)) {
                    if (current.critter.status == 'S') {
                        result.add(current);
                    }
                }
            }
        }
        return result;
    }

    private boolean checkIsOver() {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col].getOccupant().equals(Cell.CRITTER))
                    return false;
            }
        }
        return true;
    }

    public static void swapCells(Cell one, Cell two, Cell[][] map) {
        Coord oneCoords = one.coords;
        Coord twoCoords = two.coords;
        map[oneCoords.row][oneCoords.col] = two;
        map[twoCoords.row][twoCoords.col] = one;
        one.coords = twoCoords;
        two.coords = oneCoords;

        one.critter.location = twoCoords;
    }

    public static void removeCell(Coord location, Cell[][] map) {
        map[location.row][location.col] = new Cell(location.row, location.col);
    }

    public static void critterEscaped(Critter c) {
        escapedCritters.add(c);
    }
}
