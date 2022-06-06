package io.github.mwttg.games.game.dev.toolz.level.tiles2array;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import org.testng.annotations.Test;

public class ImageReaderTest {

  private ImageReader subject;

  @Test
  public void testGetDimension() {
    final var filename = ImageReader.class.getResource("/files/5x5.png").getFile();
    subject = new ImageReader(new File(filename));

    final var actual = subject.getDimension();
    assertThat(actual).isEqualTo(new Size(5, 5));
  }

  @Test(expectedExceptions = RuntimeException.class)
  public void testConstructor_fileDoesNotExists() {
    subject = new ImageReader(new File("does-not-exists.png"));
  }

  @Test
  public void testGetColorOfCoordinate() {
    final var filename = ImageReader.class.getResource("/files/5x5.png").getFile();
    subject = new ImageReader(new File(filename));

    assertThat(subject.getColorOfCoordinate(0, 0)).isEqualTo("FFFF00");
    assertThat(subject.getColorOfCoordinate(0, 1)).isEqualTo("FFFFFF");
    assertThat(subject.getColorOfCoordinate(0, 4)).isEqualTo("FF0000");
  }

  @Test(expectedExceptions = ArrayIndexOutOfBoundsException.class)
  public void testGetColorOfCoordinate_outOfBounce() {
    final var filename = ImageReader.class.getResource("/files/5x5.png").getFile();
    subject = new ImageReader(new File(filename));
    subject.getColorOfCoordinate(6, 6);
  }
}