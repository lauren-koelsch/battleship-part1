package cs3500.pa03.model;

/**
 * Represents a coordinate location on the board
 */
public class Coord {
  private int yvalue;

  private int xvalue;

  private String label;

  private boolean isShip;

  private boolean isHit;

  /**
   * Represents a coordinate on the board
   *
   * @param yvalue the y value of the coordinate
   * @param xvalue the x value of the coordinate
   * @param label a string representing what occupies the coordinate
   * @param isShip boolean representing if the coordinate is a ship
   * @param isHit boolean representing if the coordinate has been hit
   */
  public Coord(int yvalue, int xvalue, String label, boolean isShip, boolean isHit) {
    this.yvalue = yvalue;
    this.xvalue = xvalue;
    this.label = label;
    this.isShip = isShip;
    this.isHit = isHit;
  }

  public Coord(int xvalue, int yvalue) {
    this.xvalue = xvalue;
    this.yvalue = yvalue;
  }

  // GETTERS
  public int getYvalue() {
    return yvalue;
  }

  public int getXvalue() {
    return xvalue;
  }

  public String getLabel() {
    return label;
  }

  public boolean getIsShip() {
    return isShip;
  }

  public boolean getIsHit() {
    return isHit;
  }

  public void setLabel(String string) {
    label = string;
  }

  public void setIsHit(boolean bool) {
    isHit = bool;
  }
}
