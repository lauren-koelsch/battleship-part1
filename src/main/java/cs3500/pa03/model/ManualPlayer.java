package cs3500.pa03.model;

import cs3500.pa03.controller.GameController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Represents the player playing the game of BattleSalvo
 */
public class ManualPlayer implements Player {

  private final Board board;

  private final Random rand;

  private final GameController controller;

  private final List<Ship> shipPlacements = new ArrayList<>();

  private int numBullets = 0;

  /**
   * Represents the manual player in a game of BattleSalvo
   *
   * @param board 2D ArrayList representing the manual player's board during the game
   * @param rand random to be used for random generation
   * @param controller controller to see shot input from user
   */
  public ManualPlayer(Board board, Random rand, GameController controller) {
    this.board = board;
    this.rand = rand;
    this.controller = controller;
  }

  /**
   * Get the player's name.
   *
   * @return the player's name
   */
  @Override
  public String name() {
    return "Human Player";
  }

  /**
   * Given the specifications for a BattleSalvo board, return a list of ships with their locations
   * on the board.
   *
   * @param height         the height of the board, range: [6, 15] inclusive
   * @param width          the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return the placements of each ship on the board
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {

    int numCarriers = specifications.get(ShipType.CARRIER);
    for (int i = 0; i < numCarriers; i++) {
      Ship carrier = setCarrier(height, width);
      shipPlacements.add(carrier);
    }

    int numBattleships = specifications.get(ShipType.BATTLESHIP);
    for (int i = 0; i < numBattleships; i++) {
      Ship battleship = setBattleship(height, width);
      shipPlacements.add(battleship);
    }

    int numDestroyers = specifications.get(ShipType.DESTROYER);
    for (int i = 0; i < numDestroyers; i++) {
      Ship destroyer = setDestroyer(height, width);
      shipPlacements.add(destroyer);
    }

    int numSubmarines = specifications.get(ShipType.SUBMARINE);
    for (int i = 0; i < numSubmarines; i++) {
      Ship submarine = setSubmarine(height, width);
      shipPlacements.add(submarine);
    }
    numBullets = shipPlacements.size();
    return shipPlacements;
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public List<Coord> takeShots() {
    updateNumBullets();
    return controller.collectShots(numBullets);
  }

  /**
   * Given the list of shots the opponent has fired on this player's board, report which
   * shots hit a ship on this player's board.
   *
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return a filtered list of the given shots that contain all locations of shots that hit a
   *     ship on this board
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    List<Coord> damageReport = new ArrayList<>();

    for (Coord c : opponentShotsOnBoard) {
      Coord coord = board.getBoard().get(c.getYvalue()).get(c.getXvalue());
      if (coord.getIsShip()) {
        coord.setIsHit(true);
        coord.setLabel("#");
        damageReport.add(coord);
      } else {
        coord.setIsHit(true);
        coord.setLabel("x");
      }
    }
    return damageReport;
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   * COMMENT: I did not end up having to use this field because I didn't have any use in my code
   * for a player to know what shots in their previous volley hit a ship. This is because the
   * manual player can see visually on the board display which shots hit a ship and which shots
   * missed (hits denoted by "#" and misses denoted by "x"). And my AI player selects shots
   * randomly, so it has no reason to know which shots were hits.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
  }

  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   * COMMENT: Didn't end up using this method either, game already ends when the while loop in the
   * controller class is broken
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {


  }

  private Ship setCarrier(int height, int width) {

    boolean shipNotSet = true;
    List<Coord> shipCoords = new ArrayList<>();
    Ship ship = new Ship(ShipType.CARRIER, shipCoords, false);

    while (shipNotSet) {

      int randYval = rand.nextInt(height);
      int randXval = rand.nextInt(width - 5);
      Coord rootCell = new Coord(randYval, randXval, "_", false, false);

      if (goodLocation(rootCell)) {
        rootCell = new Coord(randYval, randXval, "C", true, false);
        board.replaceCoord(rootCell);
        shipCoords.add(rootCell);

        for (int i = 1; i <= 5; i++) {
          int newXval = randXval + i;
          Coord stemCoord = new Coord(randYval, newXval, "C", true, false);
          board.replaceCoord(stemCoord);
          shipCoords.add(stemCoord);
        }
        ship = new Ship(ShipType.CARRIER, shipCoords, false);
        shipNotSet = false;
      }
    }
    return ship;
  }

  private Ship setBattleship(int height, int width) {

    boolean shipNotSet = true;
    List<Coord> shipCoords = new ArrayList<>();
    Ship ship = new Ship(ShipType.BATTLESHIP, shipCoords, false);

    while (shipNotSet) {

      int randYval = rand.nextInt(height);
      int randXval = rand.nextInt(width - 4);
      Coord rootCell = new Coord(randYval, randXval, "_", false, false);

      if (goodLocation(rootCell)) {
        rootCell = new Coord(randYval, randXval, "B", true, false);
        board.replaceCoord(rootCell);
        shipCoords.add(rootCell);

        for (int i = 1; i <= 4; i++) {
          int newXval = randXval + i;
          Coord stemCoord = new Coord(randYval, newXval, "B", true, false);
          board.replaceCoord(stemCoord);
          shipCoords.add(stemCoord);
        }
        ship = new Ship(ShipType.BATTLESHIP, shipCoords, false);
        shipNotSet = false;
      }
    }
    return ship;
  }

  private Ship setDestroyer(int height, int width) {

    boolean shipNotSet = true;
    List<Coord> shipCoords = new ArrayList<>();
    Ship ship = new Ship(ShipType.DESTROYER, shipCoords, false);

    while (shipNotSet) {
      int randYval = rand.nextInt(height);
      int randXval = rand.nextInt(width - 3);
      Coord rootCell = new Coord(randYval, randXval, "_", false, false);

      if (goodLocation(rootCell)) {
        rootCell = new Coord(randYval, randXval, "D", true, false);
        board.replaceCoord(rootCell);
        shipCoords.add(rootCell);

        for (int i = 1; i <= 3; i++) {
          int newXval = randXval + i;
          Coord stemCoord = new Coord(randYval, newXval, "D", true, false);
          board.replaceCoord(stemCoord);
          shipCoords.add(stemCoord);
        }
        ship = new Ship(ShipType.DESTROYER, shipCoords, false);
        shipNotSet = false;
      }
    }
    return ship;
  }

  private Ship setSubmarine(int height, int width) {

    boolean shipNotSet = true;
    List<Coord> shipCoords = new ArrayList<>();
    Ship ship = new Ship(ShipType.SUBMARINE, shipCoords, false);

    while (shipNotSet) {
      int randYval = rand.nextInt(height);
      int randXval = rand.nextInt(width - 2);
      Coord rootCell = new Coord(randYval, randXval, "_", false, false);

      if (goodLocation(rootCell)) {
        rootCell = new Coord(randYval, randXval, "S", true, false);
        board.replaceCoord(rootCell);
        shipCoords.add(rootCell);

        for (int i = 1; i <= 2; i++) {
          int newXval = randXval + i;
          Coord stemCoord = new Coord(randYval, newXval, "S", true, false);
          board.replaceCoord(stemCoord);
          shipCoords.add(stemCoord);
        }
        ship = new Ship(ShipType.SUBMARINE, shipCoords, false);
        shipNotSet = false;
      }
    }
    return ship;
  }

  private boolean goodLocation(Coord topLeftCoord) {

    int rootY = topLeftCoord.getYvalue();
    boolean isGoodLocation = true;
    ArrayList<Coord> row = board.getBoard().get(rootY);

    for (Coord coord : row) {
      isGoodLocation = !coord.getIsShip();
      if (!isGoodLocation) {
        break;
      }
    }
    return isGoodLocation;
  }

  private void updateNumBullets() {
    int shotCounter = shipPlacements.size();

    for (Ship s : shipPlacements) {
      if (s.checkIsSunk()) {
        shotCounter = shotCounter - 1;
      }
    }
    numBullets = shotCounter;
  }
}
