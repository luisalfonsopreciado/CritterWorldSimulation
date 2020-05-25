public class Coord {

    int row = -1;
    int col = -1;

    public Coord(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public String toString() {
        return "Coords:[" + row + "," + col + "]";
    }

    public boolean equals(Object obj) {
        Coord coord = (Coord) obj;
        return coord.row == row && coord.col == col;
    }

    public boolean isNeighbor(Coord c) {
        if(c.equals(this)) return false;
        boolean Column = c.col - 1 == col || c.col + 1 == col || c.col == col;
        boolean Row = c.row - 1 == row || c.row + 1 == row || c.row == row;
        return  Column && Row ;
    }

}
