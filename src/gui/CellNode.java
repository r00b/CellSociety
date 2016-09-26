package gui;

import javafx.scene.shape.Polygon;
import simulations.Grid;

/**
 * 
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
	private Polygon drawHexagon(Polygon polygon, double cellSize, int gridOffset, double x, double y) {
		// TODO implement hexagon
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
						gridOffset + x * cellSize, gridOffset + y * cellSize, // top left
						gridOffset + (x + 1) * cellSize, gridOffset + y * cellSize, // top right
						gridOffset + (x + 1) * cellSize, gridOffset + (y + 1) * cellSize, // bottom right
						gridOffset + x * cellSize, gridOffset + (y + 1) * cellSize }); // bottom left
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
	private Polygon drawTriangle(Polygon polygon, double cellSize, int gridOffset, double x, double y) {
		// TODO implement triangle
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
	protected Polygon getCellNode(Grid grid, double cellSize, int gridOffset, int i, int j, int numVertices) {
		Polygon polygon = new Polygon();
		// id so that the Node can be removed from the Scene later
		String id = Integer.toString(i) + Integer.toString(j);
		polygon.setId(id);
		// cast i,j to double so that we can create polygon points
		double x = (double) i;
		double y = (double) j;
		if (numVertices == 3) { // TODO: triangle
			polygon = drawTriangle(polygon, cellSize, gridOffset, x, y);
		}
		if (numVertices == 4) {
			polygon = drawSquare(polygon, cellSize, gridOffset, x, y);
		}
		if (numVertices == 6) { // TODO: hexagon
			polygon = drawHexagon(polygon, cellSize, gridOffset, x, y);
		}
		polygon.setFill(grid.getCell(i, j).getStateColor());
		return polygon;
	}
}
