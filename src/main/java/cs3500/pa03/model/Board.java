package cs3500.pa03.model;

import java.util.ArrayList;

/**
 * Represents a board in the game of BattleSalvo whose height and width are specified by the user
 */
public class Board {

  private ArrayList<ArrayList<Coord>> board;

  public Board(int height, int width) {
    initBoard(height, width);
  }

  public ArrayList<ArrayList<Coord>> getBoard() {
    return board;
  }

  /**
   * Initializes a board by making a 2D arrayList of coordinates to later be filled with game data
   *
   * @param height height of the board
   * @param width width of the board
   */
  public void initBoard(int height, int width) {

    this.board = new ArrayList<>(height);

    for (int c = 0; c < height; c++) {
      ArrayList<Coord> rows = new ArrayList<>(width);

      for (int r = 0; r < width; r++) {
        Coord oceanCoord = new Coord(c, r, "_", false, false);
        rows.add(oceanCoord);
      }
      this.board.add(rows);
    }
  }

  public void replaceCoord(Coord newCoord) {
    board.get(newCoord.getYvalue()).set(newCoord.getXvalue(), newCoord);
  }
}


