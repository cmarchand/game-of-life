package top.marchand.katas.gameOfLife;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameOfLifeTest {

  @Test
  @DisplayName("given XX/XX/XX grid has 2 cols")
  public void test1() {
    Grid grid = Grid.of("XX/XX/XX".split("/"));
    int expected = 2;
    Assertions.assertThat(grid.colsCount).isEqualTo(expected);
  }
  @Test
  @DisplayName("given XX/XX/XX grid has 3 rows")
  public void test2() {
    Grid grid = Grid.of("XX/XX/XX".split("/"));
    int expected = 3;
    Assertions.assertThat(grid.rowsCount).isEqualTo(expected);
  }
  @Test
  @DisplayName("given XX/XX/XX row[0] is (true,true)")
  public void test3() {
    Grid grid = Grid.of("XX/XX/XX".split("/"));
    boolean[] expected = new boolean[]{true,true};
    SoftAssertions softly = new SoftAssertions();
    for (int i = 0; i < 2; i++) {
      softly.assertThat(grid.getRow(0).cols[i]).isEqualTo(expected[i]);
    }
    softly.assertAll();
  }

  @Test
  @DisplayName("given XXX/X_X/XXX center has 8 neighbors")
  public void test4() {
    // given
    Grid grid = Grid.of("XXX/X X/XXX".split("/"));
    int expected = 8;
    // when
    int actual = grid.countAliveNeighbors(1, 1);
    // then
    Assertions.assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("given ___/_X_/___ center has 0 neighbor")
  public void test5() {
    // given
    Grid grid = Grid.of("   / X /   ".split("/"));
    int expected = 0;
    // when
    int actual = grid.countAliveNeighbors(1, 1);
    // then
    Assertions.assertThat(actual).isEqualTo(expected);
  }
  @Test
  @DisplayName("given ___/___/___ center has 0 neighbor")
  public void test51() {
    // given
    Grid grid = Grid.of("   /   /   ".split("/"));
    int expected = 0;
    // when
    int actual = grid.countAliveNeighbors(1, 1);
    // then
    Assertions.assertThat(actual).isEqualTo(expected);
  }
  @Test
  @DisplayName("given X__/___/___ center has 1 neighbor")
  public void test52() {
    // given
    Grid grid = Grid.of("X  /   /   ".split("/"));
    int expected = 1;
    // when
    int actual = grid.countAliveNeighbors(1, 1);
    // then
    Assertions.assertThat(actual).isEqualTo(expected);
  }
  @Test
  @DisplayName("given _X_/___/___ center has 1 neighbor")
  public void test53() {
    // given
    Grid grid = Grid.of(" X /   /   ".split("/"));
    int expected = 1;
    // when
    int actual = grid.countAliveNeighbors(1, 1);
    // then
    Assertions.assertThat(actual).isEqualTo(expected);
  }
  @Test
  @DisplayName("given __X/___/___ center has 1 neighbor")
  public void test54() {
    // given
    Grid grid = Grid.of("  X/   /   ".split("/"));
    int expected = 1;
    // when
    int actual = grid.countAliveNeighbors(1, 1);
    // then
    Assertions.assertThat(actual).isEqualTo(expected);
  }
  @Test
  @DisplayName("given ___/X__/___ center has 1 neighbor")
  public void test55() {
    // given
    Grid grid = Grid.of("   /X  /   ".split("/"));
    int expected = 1;
    // when
    int actual = grid.countAliveNeighbors(1, 1);
    // then
    Assertions.assertThat(actual).isEqualTo(expected);
  }
  @Test
  @DisplayName("given ___/XX_/___ center has 1 neighbor")
  public void test56() {
    // given
    Grid grid = Grid.of("   /XX /   ".split("/"));
    int expected = 1;
    // when
    int actual = grid.countAliveNeighbors(1, 1);
    // then
    Assertions.assertThat(actual).isEqualTo(expected);
  }
  @Test
  @DisplayName("given ___/__X/___ center has 1 neighbor")
  public void test57() {
    // given
    Grid grid = Grid.of("   /  X/   ".split("/"));
    int expected = 1;
    // when
    int actual = grid.countAliveNeighbors(1, 1);
    // then
    Assertions.assertThat(actual).isEqualTo(expected);
  }
  @Test
  @DisplayName("given ___/___/X__ center has 1 neighbor")
  public void test58() {
    // given
    Grid grid = Grid.of("   /   /X  ".split("/"));
    int expected = 1;
    // when
    int actual = grid.countAliveNeighbors(1, 1);
    // then
    Assertions.assertThat(actual).isEqualTo(expected);
  }
  @Test
  @DisplayName("given ___/___/_X_ center has 1 neighbor")
  public void test59() {
    // given
    Grid grid = Grid.of("   /   / X ".split("/"));
    int expected = 1;
    // when
    int actual = grid.countAliveNeighbors(1, 1);
    // then
    Assertions.assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("given XX/XX/XX when clean() [0,0] has 0 neighbors")
  public void test6() {
    // given
    Grid grid = Grid.of("XX/XX/XX".split("/"));
    // when
    grid.clear();
    // then
    Assertions.assertThat(grid.countAliveNeighbors(0,0)).isEqualTo(0);
  }
  @Test
  @DisplayName("given XXX/XXX/XXX when clean() [1,1] has 0 neighbors")
  public void test7() {
    // given
    Grid grid = Grid.of("XXX/XXX/XXX".split("/"));
    // when
    grid.clear();
    // then
    Assertions.assertThat(grid.countAliveNeighbors(1,1)).isEqualTo(0);
  }
  @Test
  @DisplayName("given XXX/XXX/XXX when clean() [1,1] has 0 neighbors")
  public void test8() {
    // given
    Grid grid = Grid.of("XXX/XXX/XXX".split("/"));
    // when
    grid.clear();
    // then
    Assertions.assertThat(grid.countAliveNeighbors(2,2)).isEqualTo(0);
  }

  @Test
  @DisplayName("a cell with 0 neighbors dies")
  public void test9() {
    Grid grid = Grid.of("   / X /   ".split("/"));
    Grid target = grid.of(3,3);
    // when
    grid.putNextGenerationIn(target);
    // then
    Assertions.assertThat(target.getRow(1).cols[1]).isFalse();
  }
}
