package gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import simulations.Grid;

/**
 * @author Robert H. Steilberg II | rhs16
 *
 *         The CellNode class creates a Polygon object for each cell of the
 *         grid. Each Node is created with the correct state according to the
 *         simulation and the correct position according to the grid specified
 *         via the Animation class. Each Node object is created as a user-
 *         specified shape; the points of the polygon are determined depending
 *         on which shape should be drawn.
 * 
 *         Dependencies: Animation.java
 */
public class CellNode {

	private double average(double x, double y) {
		return (x + y) / 2;
	}

	/**
	 * 
	 * @param node
	 *            the Polygon to draw as a hexagon
	 * @param cellSize
	 *            the height and width of each cell in pixels
	 * @param gridOffset
	 *            the position of the overall grid relative to the scene
	 * @param x
	 *            the ith cell
	 * @param y
	 *            the jth cell
	 * @return the correctly positioned and shaped polygon
	 */
	private Polygon drawHexagon(Polygon polygon, double cellSize, int gridOffset, double x, double y, boolean offset) {
	//	cellSize *= 2;
		double radius = cellSize / 2;
		double spacing = (radius/2) * x;
		double ySub = radius;//(Math.sqrt(3)/2) * radius;
		if (offset) {
			polygon.getPoints()
					.addAll(new Double[] {
							// top left (3*radius)+ ((2 * radius * x) +
							((gridOffset + x * cellSize) + radius / 2) - spacing, (gridOffset + y * cellSize) - ySub,
							// top right
							((gridOffset + (x + 1) * cellSize) - radius / 2) - spacing, (gridOffset + y * cellSize) - ySub,
							// rightmost
							(gridOffset + (x + 1) * cellSize) - spacing,
							average(gridOffset + y * cellSize, gridOffset + (y + 1) * cellSize) - ySub,
							// bottom right
							((gridOffset + (x + 1) * cellSize) - radius / 2) - spacing, (gridOffset + (y + 1) * cellSize) - ySub,
							// bottom left
							((gridOffset + x * cellSize) + radius / 2) - spacing, (gridOffset + (y + 1) * cellSize) - ySub,
							// leftmost
							(gridOffset + x * cellSize) - spacing,
							average(gridOffset + y * cellSize, gridOffset + (y + 1) * cellSize) - ySub,
							});
		} else {
			polygon.getPoints()
					.addAll(new Double[] {
							// top left (2 * radius * x)
							((gridOffset + x * cellSize) + radius / 2) - spacing, (gridOffset + y * cellSize),
							// top right
							((gridOffset + (x + 1) * cellSize) - radius / 2) - spacing, (gridOffset + y * cellSize),
							// rightmost
							(gridOffset + (x + 1) * cellSize) - spacing,
							average(gridOffset + y * cellSize, gridOffset + (y + 1) * cellSize),
							// bottom right
							((gridOffset + (x + 1) * cellSize) - radius / 2) - spacing, (gridOffset + (y + 1) * cellSize),
							// bottom left
							((gridOffset + x * cellSize) + radius / 2) - spacing, (gridOffset + (y + 1) * cellSize),
							// leftmost
							(gridOffset + x * cellSize) - spacing,
							average(gridOffset + y * cellSize, gridOffset + (y + 1) * cellSize), });
		}
		polygon.setStroke(Color.LIGHTGREY);
		return polygon;
	}

	/**
	 * 
	 * @param node
	 *            the Polygon to draw as a square
	 * @param cellSize
	 *            the height and width of each cell in pixels
	 * @param gridOffset
	 *            the position of the overall grid relative to the scene
	 * @param x
	 *            the ith cell
	 * @param y
	 *            the jth cell
	 * @return the correctly positioned and shaped polygon
	 */
	private Polygon drawSquare(Polygon polygon, double cellSize, int gridOffset, double x, double y) {
		polygon.getPoints()
				.addAll(new Double[] {
						// top left
						gridOffset + x * cellSize, gridOffset + y * cellSize,
						// top right
						gridOffset + (x + 1) * cellSize, gridOffset + y * cellSize,
						// bottom right
						gridOffset + (x + 1) * cellSize, gridOffset + (y + 1) * cellSize,
						// bottom left
						gridOffset + x * cellSize, gridOffset + (y + 1) * cellSize });
		return polygon;
	}

	/**
	 * 
	 * @param node
	 *            the Polygon to draw as a triangle
	 * @param cellSize
	 *            the height and width of each cell in pixels
	 * @param gridOffset
	 *            the position of the overall grid relative to the scene
	 * @param x
	 *            the ith cell
	 * @param y
	 *            the jth cell
	 * @return the correctly positioned and shaped polygon
	 */
	private Polygon drawTriangle(Polygon polygon, double cellSize, int gridOffset, double x, double y, boolean invert) {
		if (invert) {
			polygon.getPoints()
					.addAll(new Double[] {
							// top left
							gridOffset + x * cellSize, gridOffset + y * cellSize,
							// top right
							gridOffset + (x + 1) * cellSize, gridOffset + y * cellSize,
							// bottom point
							average(gridOffset + (x + 1) * cellSize, gridOffset + x * cellSize),
							gridOffset + (y + 1) * cellSize });
		} else {
			polygon.getPoints().addAll(new Double[] {
					// top point
					average(gridOffset + x * cellSize, gridOffset + (x + 1) * cellSize), gridOffset + y * cellSize,
					// bottom right
					gridOffset + (x + 1) * cellSize, gridOffset + (y + 1) * cellSize,
					// bottom left
					gridOffset + x * cellSize, gridOffset + (y + 1) * cellSize }); // left
		}
		return polygon;
	}

	/**
	 * 
	 * @param grid
	 *            the Grid object in which the cells are located
	 * @param cellSize
	 *            the height and width of each cell in pixels
	 * @param gridOffset
	 *            the position of the overall grid relative to the scene
	 * @param i
	 *            the ith cell
	 * @param j
	 *            the jth cell
	 * @param numVertices
	 *            the number of vertices that each Node should have (i.e. the
	 *            shape)
	 * @return a ready-to-print Polygon with the correct position, size, and
	 *         state
	 */
	protected Polygon getCellNode(Grid grid, double cellSize, int gridOffset, int i, int j, int numVertices,
			boolean invert, boolean offset) {
		Polygon polygon = new Polygon();
		// id so that the Node can be removed from the Scene later
		String id = Integer.toString(i) + Integer.toString(j);
		polygon.setId(id);
		// cast i,j to double so that we can create polygon points
		double x = (double) i;
		double y = (double) j;
		if (numVertices == 3) { // TODO: triangle

			polygon = drawTriangle(polygon, cellSize, gridOffset, (x / 2), y, invert);
		}
		if (numVertices == 4) {
			polygon = drawSquare(polygon, cellSize, gridOffset, x, y);
		}
		if (numVertices == 6) { // TODO: hexagon
			polygon = drawHexagon(polygon, cellSize, gridOffset, x, y, offset);
		}
		polygon.setFill(grid.getCell(i, j).getStateColor());
		return polygon;
	}
}
