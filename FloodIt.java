import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

//Represents a single square of the game area
class Cell {
// In logical coordinates, with the origin at the top-left corner of the screen
  int x;
  int y;
  Color color;
  boolean flooded;
// the four adjacent cells to this one
  Cell left;
  Cell top;
  Cell right;
  Cell bottom;
  
  Cell(int x, int y, Color color) {
    this.x = x; 
    this.y = y; 
    this.color = color; 
  }
}

class FloodItWorld extends World {
// All the cells of the game
  ArrayList<Cell> board;
 // int cellWidth = 20; 
  int BOARD_SIZE = 22;  
  int CELL_SIZE = 30; 
  int CANVAS_SIZE = 660;
  
  FloodItWorld() {
    initBoard(); 
  }
  
  Color myRed = Color.decode("#F71E4A"); 
  Color myOrange = Color.decode("#D68D1D");
  Color myGreen = Color.decode("#01DC5F");
  Color myBlue = Color.decode("#1E90FF");
  Color myPurple = Color.decode("#9545E9");
  
  
  ArrayList<Color> myColors = new ArrayList<Color>(Arrays.asList(myRed, myOrange, myGreen, myBlue, myPurple)); 
  Random rand = new Random(); 
  
  Color getRandomColor() {
    int randIndex = rand.nextInt(myColors.size()); 
    return myColors.get(randIndex);
  }
  
  void initBoard() {
    this.board = new ArrayList<Cell>();
    ArrayList<ArrayList<Cell>> cells = new ArrayList<ArrayList<Cell>>();
    for(int k = 0; k < BOARD_SIZE; k++) {
      
      ArrayList<Cell> row = new ArrayList<Cell>(); 
      int y = k * CELL_SIZE; 
      
      for(int j = 0; j < BOARD_SIZE; j++) {
        int x = j * CELL_SIZE; 
        Cell newCell = new Cell(x, y, getRandomColor()); 
        row.add(newCell); 
        board.add(newCell);
      }
      cells.add(row); 
    }
  }
  
  @Override
  public WorldScene makeScene() {
    WorldScene scene = new WorldScene(CANVAS_SIZE, CANVAS_SIZE);
    for (Cell c : board) { 
      RectangleImage image = new RectangleImage(CELL_SIZE, CELL_SIZE, OutlineMode.SOLID, c.color); 
      scene.placeImageXY(image, c.x + CELL_SIZE / 2, c.y + CELL_SIZE / 2);
    }
    return scene;
  }
}

class ExamplesWorlds {

  void testStartGame(Tester t) {
    FloodItWorld g = new FloodItWorld();
    
    g.bigBang(660, 660);
  }
}