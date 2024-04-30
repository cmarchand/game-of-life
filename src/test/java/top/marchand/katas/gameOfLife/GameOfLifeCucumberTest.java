package top.marchand.katas.gameOfLife;


import io.cucumber.core.options.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("top.marchand.katas.gameOfLife")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "top.marchand.katas.gameOfLife.glues")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber")
public class GameOfLifeCucumberTest {
}
