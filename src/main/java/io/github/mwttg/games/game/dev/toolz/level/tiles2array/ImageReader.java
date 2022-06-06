package io.github.mwttg.games.game.dev.toolz.level.tiles2array;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ImageReader {

  private static final Logger LOG = LoggerFactory.getLogger(ImageReader.class);

  private final BufferedImage image;

  ImageReader(final File imageFile) {
    LOG.info("Source image file is: '{}'.", imageFile.getAbsolutePath());
    try {
      this.image = ImageIO.read(imageFile);
    } catch (final IOException ioException) {
      LOG.error("Could not read image file '" + imageFile.getAbsolutePath() + "'. Exception was: ", ioException);
      throw new RuntimeException(ioException);
    }
  }

  String getColorOfCoordinate(final int x, final int y) {
    final var color = image.getRGB(x, y);
    final int red = (color & 0x00ff0000) >> 16;
    final int green = (color & 0x0000ff00) >> 8;
    final int blue = color & 0x000000ff;

    return String.format("%02X%02X%02X", red, green, blue);
  }

  Size getDimension() {
    final var width = image.getWidth();
    final var height = image.getHeight();

    return new Size(width, height);
  }
}
