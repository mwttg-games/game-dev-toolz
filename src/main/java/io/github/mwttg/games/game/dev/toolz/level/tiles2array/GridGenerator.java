package io.github.mwttg.games.game.dev.toolz.level.tiles2array;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class GridGenerator {

  private static final Logger LOG = LoggerFactory.getLogger(GridGenerator.class);

  private final ImageReader imageReader;
  private final Size tileSize;
  private final Map<String, Integer> indexByColor;

  GridGenerator(final File imageFile, final Size tileSize, final Map<String, Integer> indexByColor) {
    this.imageReader = new ImageReader(imageFile);
    this.tileSize = tileSize;
    this.indexByColor = indexByColor;

    assert this.imageReader.getDimension().width() % tileSize.width() == 0 : "Tile width and image width does not match up";
    assert this.imageReader.getDimension().height() % tileSize.height() == 0 : "Tile height and image height does not match up";
  }

  GridGenerator(final Configuration configuration) {
    this(new File(configuration.imageSourceFilename()), configuration.tileSize(), configuration.indexByColor());
  }

  List<List<Integer>> buildGrid() {
    final List<List<Integer>> result = new ArrayList<>();
    final var dimension = imageReader.getDimension();

    // x, y = position inside image
    // indexX, indexY = position inside the Grid
    //
    // Because of OpenGL y-coordinate (0 is bottom) is reverse than image (png)
    // y-coordinate (0 is top) we scan the image file from bottom to top (outer for loop)
    int indexX = -1;
    int indexY = 0;
    //  tileSize / 2 (for width and height) for checking the color in the middle of the tile of the image
    for (int y = dimension.height() - (tileSize.height() / 2); y > 0; y = y - tileSize.height()) {
      var row = new ArrayList<Integer>();
      for (int x = tileSize.width() / 2; x < dimension.width(); x = x + tileSize.width()) {
        indexX = indexX + 1;
        final var color = imageReader.getColorOfCoordinate(x, y);
        LOG.debug("Processing image coordinate x='{}' y ='{}' with color='{}'. The Grid position is x='{}' y ='{}'", x, y,
            color, indexX, indexY);

        if (!indexByColor.containsKey(color)) {
          LOG.error("The color '{}' in the image was not defined inside the indexByColor Map", color);
          row.add(666);
          continue;
        }

        final var colorIndex = indexByColor.get(color);
        row.add(colorIndex);
      }
      indexX = -1;
      indexY = indexY + 1;
      result.add(row);
    }

    return result;
  }
}
