package top.marchand.katas.gameOfLife;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Grid {
  public final int rowsCount;
  public final int colsCount;
  private Row[] rows;

  public Grid(int colsCount, int rowsCount) {
    super();
    this.colsCount = colsCount;
    this.rowsCount = rowsCount;
    rows = new Row[rowsCount];
    for (int rowPosition = 0; rowPosition < rowsCount; rowPosition++) {
      rows[rowPosition] = new Row(colsCount);
    }

  }

  /**
   * Constructs a new grid, based on an array of String.
   * Alive cells are marqued as a 'X'.
   * <p>
   * Example :
   *
   * @param rows
   * @return
   * @code{ Grid.buildGrid({ " X ", " X X ", " X ", " X " })
   * }
   */
  public static Grid of(String[] rows) {
    int rowsCount = rows.length;
    int colsCount = Arrays.stream(rows)
        .mapToInt(String::length)
        .max().getAsInt();
    Grid grid = new Grid(colsCount, rowsCount);
    for (int row = 0; row < rows.length; row++) {
      String rowData = rows[row];
      for (int col = 0; col < rowData.length(); col++) {
        grid.setCellAlive(col, row, rowData.charAt(col) == 'X');
      }
    }
    return grid;
  }

  public Row getRow(int rowIndex) {
    Row row = rows[rowIndex];
    try {
      return (Row) row.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  private void setCellAlive(int col, int row, boolean alive) {
    rows[row].cols[col] = alive;
  }

  /**
   * After this call, no cell is alive
   */
  public void clear() {
    for (Row row : rows) {
      Arrays.fill(row.cols, false);
    }
  }

  public int countAliveNeighbors(int cellX, int cellY) {
    int count = 0;
    for (int y = keepYinRows(cellY - 1); y <= keepYinRows(cellY + 1); y++) {
      for (int x = keepXinCols(cellX - 1); x <= keepXinCols(cellX + 1); x++) {
        if(cellX==x && cellY==y) continue;
        if (rows[y].cols[x]) count++;
      }
    }
    return count;
  }
  private int keepXinCols(int x) {
    if(x < 0) return 0;
    if(x >= colsCount) return colsCount - 1;
    return x;
  }
  private int keepYinRows(int y) {
    if(y < 0) return 0;
    if(y >= rowsCount) return rowsCount - 1;
    return y;
  }

  /**
   * Constructs a grid with only dead cells
   * @param rows
   * @param cols
   * @return
   */
  public static Grid of(int rows, int cols) {
    String[] data = new String[rows];
    String row = " ".repeat(cols);
    for (int i = 0; i < rows; i++) {
      data[i] = row;
    }
    return of(data);
  }

  public void putNextGenerationIn(Grid target) {
    target.clear();
    for (int row = 0; row < rowsCount; row++) {
      for(int col = 0; col < colsCount; col++) {
        switch(countAliveNeighbors(col,row)) {
          case 2, 3 -> target.rows[row].cols[col]=true;
          default -> target.rows[row].cols[col]=false;
        }
      }
    }
  }

  public class Row implements Cloneable {
    boolean[] cols;

    private Row(int nbCols) {
      cols = new boolean[nbCols];
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
      return super.clone();
    }
  }

  @Override
  public String toString() {
    return Arrays.stream(rows)
        .map(row -> {
          String ret = "";
          for (int x = 0; x < row.cols.length; x++) {
            ret += row.cols[x] ? "X" : " ";
          }
          return ret;
        })
        .collect(Collectors.joining("/"));
  }
}
