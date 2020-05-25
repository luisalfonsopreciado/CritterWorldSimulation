import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Critter {

    // Some variables already defined for you.
    static int IDCount = 1;
    int ID;
    Coord location;
    char status;

    public Critter(Coord location) {
        this.ID = IDCount++;
        this.location = location;
        this.status = 'S';
    }

    public String toString() {
        return status + " " + ID;
    }

    public void simulate(Cell[][] map) {
        Cell currentCell = map[location.row][location.col];
        if (status == 'S') {
            if (CritterWorld.hasFoundLeader(location, map)) {
                status = 'F';
                return;
            }
            ArrayList<Cell> neighbors = CritterWorld.getNeighbors(location, map);
            if(neighbors.size() <= 0) return;
            int targetIndex = ThreadLocalRandom.current().nextInt(0, neighbors.size());
            CritterWorld.swapCells(currentCell, neighbors.get(targetIndex), map);
            if (hasFoundHatch(map))
                status = 'L';
        } else if (status == 'L') {
            ArrayList<Cell> critters = CritterWorld.getSearchingCritters(location, map);
            if (critters.size() == 0) {
                status = 'F';
                return;
            }

            Coord targetCoord = getRouteToNearestSearcher(critters, map);
            Cell targetCell = map[targetCoord.row][targetCoord.col];
            CritterWorld.swapCells(currentCell, targetCell, map);
        } else {
            if(hasFoundHatch(map)){
                CritterWorld.critterEscaped(this);
                CritterWorld.removeCell(location, map);
                return;
            }
            Coord targetCoord = getRouteToHatch(map);
            Cell targetCell = map[targetCoord.row][targetCoord.col];
            CritterWorld.swapCells(currentCell, targetCell, map);
            
        }

    }

    private Coord getRouteToNearestSearcher(ArrayList<Cell> lostCritters, Cell[][] map) {

        ArrayList<Cell> neighbors = CritterWorld.getNeighbors(location, map);
        if (neighbors.size() == 0)
            return location;
        Coord nearestGuy = neighbors.get(0).coords;
        double minDistance = distance(nearestGuy, lostCritters.get(0).coords);
        for (Cell neighbor : neighbors) {
            for (Cell lostCritter : lostCritters) {
                double distance = distance(neighbor.coords, lostCritter.coords);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestGuy = neighbor.coords;
                }
            }
        }
        return nearestGuy;
    }

    private Coord getRouteToHatch(Cell[][] map) {
        ArrayList<Cell> neighbors = CritterWorld.getNeighbors(location, map);
        if (neighbors.size() == 0)
            return location;
        Coord hatchCoords = getHatchCoords(map);
        Coord nearestGuy = neighbors.get(0).coords;
        double minDistance = distance(neighbors.get(0).coords, hatchCoords);
        for (Cell neighbor : neighbors) {
            double distance = distance(neighbor.coords, hatchCoords);
            if (distance < minDistance) {
                minDistance = distance;
                nearestGuy = neighbor.coords;
            }

        }
        return nearestGuy;
    }

    private boolean hasFoundHatch(Cell[][] map) {
        Coord hatchCoords = map[0][0].coords;
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                Cell current = map[row][col];
                if (current.getOccupant().equals(Cell.HATCH)) {
                    hatchCoords = current.coords;
                }
            }
        }
        return location.isNeighbor(hatchCoords);
    }

    private Coord getHatchCoords(Cell[][] map) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                Cell current = map[row][col];
                if (current.getOccupant().equals(Cell.HATCH)) {
                    return current.coords;
                }
            }
        }
        return location;
    }

    private double distance(Coord a, Coord b) {
        return Math.sqrt(((a.row - b.row) * (a.row - b.row)) + ((a.col - b.col) * (a.col - b.col)));
    }

}
