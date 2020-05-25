public class Cell {

    static final String CRITTER = "critter";
    static final String ROCK = "rock";
    static final String HATCH = "hatch";

    Coord coords;
    String occupant = "";
    Critter critter;

    public Cell(int row, int col) {
        coords = new Coord(row, col);
    }

    public void setOccupant(String occupant){
        this.occupant = occupant;
        if(occupant.equals("critter")){
            critter = new Critter(coords);
        }
    }

    public boolean isOccupied(){
        return occupant.length() != 0;
    }

    public String getOccupant(){
        return occupant;
    }

    public String toString() {
        switch (occupant) {
            case "hatch":
                return " H ";
            case "rock":
                return " X ";
            case "critter":
                return critter.toString();
            default:
                return " - ";
        }

    }

    public void simulate(Cell[][] map){
        if(critter != null){
            critter.simulate(map);
        }
    }

}
