package cs3500.pa03;

import cs3500.pa03.controller.GameController;
import cs3500.pa03.view.GameDisplayer;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;


/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {

    Readable input = new InputStreamReader(System.in);
    GameDisplayer view = new GameDisplayer(System.out);
    Random rand = new Random();

    GameController controller = new GameController(input, view, rand);

    try {
      controller.run();
    } catch (IOException e) {
      throw new RuntimeException("Could not run program:" + e);
    }
  }
}