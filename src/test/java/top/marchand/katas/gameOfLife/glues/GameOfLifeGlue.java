package top.marchand.katas.gameOfLife.glues;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import top.marchand.katas.gameOfLife.Grid;

public class GameOfLifeGlue {

  private Grid source;
  private Grid target;

  @Given("grid is {string}")
  public void grid_is(String gridModel) {
    source = Grid.of(gridModel.split("/"));
    target = Grid.of(source.rowsCount, source.colsCount);
  }
  @When("calculating next generation")
  public void calculating_next_generation() {
    source.putNextGenerationIn(target);
  }
  @Then("new grid is {string}")
  public void new_grid_is(String expected) {
    Assertions.assertThat(target.toString()).isEqualTo(expected);
  }

}
