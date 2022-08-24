package io.github.mwttg.games.game.dev.toolz.level.tiles2array;

import io.github.mwttg.games.basic.utilities.files.JsonFile;
import java.io.File;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tiles2Array {

  private static final Logger LOG = LoggerFactory.getLogger(Tiles2Array.class);

  public static void main(String[] args) {
    final var configuration = createConfiguration();
    final var gridGenerator = new GridGenerator(configuration);
    final var grid = gridGenerator.buildGrid();
    final var destination = new File(configuration.jsonDestinationFilename());
    LOG.info("Destination JSON file is: '{}'.", configuration.jsonDestinationFilename());
    JsonFile.writeTo(grid, destination);
  }

  private static Configuration createConfiguration() {
    return new Configuration(
        "./data/scene-test-for-p006/level-solid-grid.png",
        "./data/scene-test-for-p006/level-solid-grid.json",
        new Size(16, 18),
        Map.of("000000", 0, // WALKABLE
            "FF00FF", 1,        // SOLID
            "FF0000", 2,        // LADDER TOP
            "FF5000", 3));      // LADDER BODY
  }
}
