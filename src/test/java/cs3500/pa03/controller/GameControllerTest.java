package cs3500.pa03.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.view.GameDisplayer;
import java.io.IOException;
import java.io.StringReader;
import java.util.Random;
import org.junit.jupiter.api.Test;

class GameControllerTest {

  @Test
  public void testRunInvalidBoardSize() throws IOException {
    // Define our input we want to test
    String string = "6 6\n" +
        "\n" +
        "1 2 2 1\n" +
        "\n" +
        "0 0\n" +
        "1 0\n" +
        "2 0\n" +
        "3 0\n" +
        "4 0\n" +
        "5 0\n" +
        "\n" +
        "0 1\n" +
        "1 1\n" +
        "2 1\n" +
        "3 1\n" +
        "4 1\n" +
        "5 1\n" +
        "\n" +
        "0 2\n" +
        "1 2\n" +
        "2 2\n" +
        "3 2\n" +
        "4 2\n" +
        "5 2\n" +
        "\n" +
        "0 3\n" +
        "1 3\n" +
        "2 3\n" +
        "3 3\n" +
        "4 3\n" +
        "5 3\n" +
        "\n" +
        "0 4\n" +
        "1 4\n" +
        "2 4\n" +
        "3 4\n" +
        "4 4\n" +
        "\n" +
        "1 5\n" +
        "2 5\n" +
        "3 5\n" +
        "4 5\n" +
        "5 5";

    // StringReader implements Readable
    Readable input = new StringReader(string);

    // StringBuilder implements Appendable
    Appendable output = new StringBuilder();

    GameDisplayer view = new GameDisplayer(output);

    Random rand = new Random(0);

    // pass them into the controller
    GameController controller = new GameController(input, view, rand);

    // check that the StringBuilder is empty to start
    assertEquals("", output.toString());

    // run the workflow
    controller.run();

    assertEquals("Hello! Welcome to the OOD BattleSalvo Game! \n" +
        "Please enter a valid height and width below:\n" +
        "Please enter your fleet in the order \n" +
        "[Carrier, Battleship, Destroyer, Submarine].\n" +
        "Remember, your fleet may not exceed size 6.\n" +
        "\n" +
        "OPPONENT'S BOARD: \n" +
        "_  _  _  _  _  _  \n" +
        "_  _  _  _  _  _  \n" +
        "_  _  _  _  _  _  \n" +
        "_  _  _  _  _  _  \n" +
        "_  _  _  _  _  _  \n" +
        "_  _  _  _  _  _  \n" +
        "\n" +
        "YOUR BOARD: \n" +
        "C  C  C  C  C  C  \n" +
        "_  B  B  B  B  B  \n" +
        "_  S  S  S  _  _  \n" +
        "_  _  D  D  D  D  \n" +
        "D  D  D  D  _  _  \n" +
        "B  B  B  B  B  _  \n" +
        "\n" +
        "Please Enter 6 Shots:\n" +
        "\n" +
        "OPPONENT'S BOARD: \n" +
        "#  #  #  #  #  x  \n" +
        "_  _  _  _  _  _  \n" +
        "_  _  _  _  _  _  \n" +
        "_  _  _  _  _  _  \n" +
        "_  _  _  _  _  _  \n" +
        "_  _  _  _  _  _  \n" +
        "\n" +
        "YOUR BOARD: \n" +
        "C  #  #  C  C  C  \n" +
        "_  B  B  #  B  B  \n" +
        "_  S  S  S  _  _  \n" +
        "_  _  D  D  D  D  \n" +
        "D  D  D  #  _  x  \n" +
        "B  B  B  B  #  _  \n" +
        "\n" +
        "Please Enter 6 Shots:\n" +
        "\n" +
        "OPPONENT'S BOARD: \n" +
        "#  #  #  #  #  x  \n" +
        "x  #  #  #  x  x  \n" +
        "_  _  _  _  _  _  \n" +
        "_  _  _  _  _  _  \n" +
        "_  _  _  _  _  _  \n" +
        "_  _  _  _  _  _  \n" +
        "\n" +
        "YOUR BOARD: \n" +
        "C  #  #  C  #  C  \n" +
        "_  B  B  #  B  B  \n" +
        "_  #  S  S  _  _  \n" +
        "x  _  D  D  D  D  \n" +
        "D  D  #  #  _  x  \n" +
        "B  B  B  B  #  x  \n" +
        "\n" +
        "Please Enter 6 Shots:\n" +
        "\n" +
        "OPPONENT'S BOARD: \n" +
        "#  #  #  #  #  x  \n" +
        "x  #  #  #  x  x  \n" +
        "#  #  #  #  #  #  \n" +
        "_  _  _  _  _  _  \n" +
        "_  _  _  _  _  _  \n" +
        "_  _  _  _  _  _  \n" +
        "\n" +
        "YOUR BOARD: \n" +
        "C  #  #  #  #  #  \n" +
        "_  #  B  #  B  B  \n" +
        "_  #  S  S  _  _  \n" +
        "x  x  D  D  D  D  \n" +
        "D  #  #  #  _  x  \n" +
        "B  B  B  B  #  x  \n" +
        "\n" +
        "Please Enter 6 Shots:\n" +
        "\n" +
        "OPPONENT'S BOARD: \n" +
        "#  #  #  #  #  x  \n" +
        "x  #  #  #  x  x  \n" +
        "#  #  #  #  #  #  \n" +
        "#  #  #  #  #  x  \n" +
        "_  _  _  _  _  _  \n" +
        "_  _  _  _  _  _  \n" +
        "\n" +
        "YOUR BOARD: \n" +
        "#  #  #  #  #  #  \n" +
        "_  #  #  #  B  B  \n" +
        "_  #  S  S  _  _  \n" +
        "x  x  #  #  D  D  \n" +
        "D  #  #  #  _  x  \n" +
        "B  B  B  B  #  x  \n" +
        "\n" +
        "Please Enter 5 Shots:\n" +
        "\n" +
        "OPPONENT'S BOARD: \n" +
        "#  #  #  #  #  x  \n" +
        "x  #  #  #  x  x  \n" +
        "#  #  #  #  #  #  \n" +
        "#  #  #  #  #  x  \n" +
        "#  #  #  #  x  _  \n" +
        "_  _  _  _  _  _  \n" +
        "\n" +
        "YOUR BOARD: \n" +
        "#  #  #  #  #  #  \n" +
        "x  #  #  #  #  B  \n" +
        "_  #  S  #  _  _  \n" +
        "x  x  #  #  D  D  \n" +
        "D  #  #  #  _  x  \n" +
        "B  B  B  B  #  x  \n" +
        "\n" +
        "Please Enter 5 Shots:\n" +
        "GAMEOVER, YOU WIN! \n" +
        "You have sunk all of your opponent's ships!\n", output.toString());

  }
}