package gui.CellShapes;

import javafx.scene.shape.Polygon;

/**
 * @author Robert H. Steilberg II
 * 
 *         The SquareCell class creates a square out of a JavaFX Polygon for
 *         drawing into a grid.
 * 
 */
public class SquareCell implements CellShape {

	/**
	 * Takes a new polygon and makes it into a square by setting the coordinates
	 * of its vertices
	 * 
	 * @param cell
	 *            the Polygon to draw as a square
	 * @param cellSize
	 *            the height and width of each cell in pixels
	 * @param gridOffset
	 *            the position of the overall grid relative to the scene
	 * @param x
	 *            the row of the cell
	 * @param y
	 *            the column of the cell
	 * @return the correctly positioned and shaped polygon
	 */
	public Polygon createShape(Polygon cell, double cellSize, int gridOffset, double x, double y) {
		// corresponds to top left, top right, bottom right, bottom left,
		// respectively per x,y pair
		double x1, y1, x2, y2, x3, y3, x4, y4;
		x1 = gridOffset + x * cellSize;
		y1 = gridOffset + y * cellSize;
		x2 = gridOffset + (x + 1) * cellSize;
		y2 = gridOffset + y * cellSize;
		x3 = gridOffset + (x + 1) * cellSize;
		y3 = gridOffset + (y + 1) * cellSize;
		x4 = gridOffset + x * cellSize;
		y4 = gridOffset + (y + 1) * cellSize;
		cell.getPoints().addAll(new Double[] { x1, y1, x2, y2, x3, y3, x4, y4 });
		return cell;
	}
}
