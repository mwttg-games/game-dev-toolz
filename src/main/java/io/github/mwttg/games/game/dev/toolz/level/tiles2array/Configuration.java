package io.github.mwttg.games.game.dev.toolz.level.tiles2array;

import java.util.Map;

record Configuration(String imageSourceFilename, String jsonDestinationFilename, Size tileSize,
                            Map<String, Integer> indexByColor) {
}
