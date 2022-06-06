package io.github.mwttg.games.game.dev.toolz.level.tiles2array;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.Map;
import org.testng.annotations.Test;

public class GridGeneratorTest {

  private GridGenerator subject;

  @Test
  public void testBuildGrid() {
    final var filename = ImageReader.class.getResource("/files/level-solids.png").getFile();
    final var tileSize = new Size(16, 18);
    final var indexByColor = Map.of("0000FF", 58, "FF00FF", 0);
    subject = new GridGenerator(new File(filename), tileSize, indexByColor);

    final var actual = subject.buildGrid();
    assertThat(actual.get(0)).containsExactly(58, 58, 58, 58, 58);
    assertThat(actual.get(1)).containsExactly(58, 0, 58, 0, 0);
    assertThat(actual.get(2)).containsExactly(0, 0, 0, 0, 0);
  }

  @Test
  public void testBuildGrid_hasMissingColorDefinition() {
    final var filename = ImageReader.class.getResource("/files/level-solids2.png").getFile();
    final var tileSize = new Size(16, 18);
    final var indexByColor = Map.of("0000FF", 58, "FF00FF", 0);
    subject = new GridGenerator(new File(filename), tileSize, indexByColor);

    final var actual = subject.buildGrid();
    assertThat(actual.get(1).get(1)).isEqualTo(666);

    assertThat(actual.get(0)).containsExactly(58, 58, 58, 58, 58);
    assertThat(actual.get(1)).containsExactly(58, 666, 58, 0, 0);
    assertThat(actual.get(2)).containsExactly(0, 0, 0, 0, 0);
  }

  @Test(expectedExceptions = AssertionError.class)
  public void testConstructor_wrongTileWidth() {
    final var filename = ImageReader.class.getResource("/files/level-solids.png").getFile();
    final var tileSize = new Size(22, 18);
    final var indexByColor = Map.of("0000FF", 58, "FF00FF", 0);
    subject = new GridGenerator(new File(filename), tileSize, indexByColor);
  }

  @Test(expectedExceptions = AssertionError.class)
  public void testConstructor_wrongTileHeight() {
    final var filename = ImageReader.class.getResource("/files/level-solids.png").getFile();
    final var tileSize = new Size(16, 22);
    final var indexByColor = Map.of("0000FF", 58, "FF00FF", 0);
    subject = new GridGenerator(new File(filename), tileSize, indexByColor);
  }
}