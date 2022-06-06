# Tiles2Array

You have a level as an image

| ![a level][level] |
|-------------------|

You create a mask of this level

| ![the mask][mask] |
| ----------------- |

You define 
* a Tile size e.g. 32x32 pixel
* an index by color map (e.g. blue -> 1 and pink -> 0)

The tool will separate your mask image into Tiles and will check the color of each tile center

| ![mask with grid][mask-grid] |
|------------------------------|

and creates an 2D array of Integers as a JSON file

```json
[ 
  [ 1, 1, 1, 1, 1, 1, 1, 1 ], 
  [ 1, 1, 1, 1, 1, 1, 1, 1 ], 
  [ 0, 0, 0, 0, 0, 0, 0, 0 ], 
  [ 0, 0, 0, 0, 0, 0, 0, 0 ], 
  [ 0, 0, 0, 0, 0, 1, 0, 0 ], 
  [ 0, 0, 0, 1, 1, 1, 0, 0 ], 
  [ 0, 0, 1, 1, 1, 1, 0, 0 ], 
  [ 1, 1, 1, 1, 1, 1, 1, 1 ] 
]
```

This output is visually 'up-side-down' because OpenGL y-coordinate starts at the bottom and JSON array index starts at the top.
But the index is matching!
Means: OpenGL (tile) coordinate (2, 2) is exactly the array index (2, 2).



[<< Back to main page][main]

[level]: level.png
[mask]: mask.png
[mask-grid]: mask-with-grid.png
[main]: ../../readme.md