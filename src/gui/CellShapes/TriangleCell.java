package gui.CellShapes;

import javafx.scene.shape.Polygon;

/**
 * Creates a square out of a new polygon.
 * 
 * @author Robert H. Steilberg II | rhs16
 *
 */
public class TriangleCell implements CellShape {

	/**
	 * Calculate the average of x and y
	 * 
	 * @param x
	 *            the first operand
	 * @param y
	 *            the second operand
	 * @return a double representing the average of x and y
	 */
	private double average(double x, double y) {
		return (x + y) / 2;
	}

	/**
	 * Takes a new polygon and makes it into a triangle by setting the
	 * coordinates of its vertices
	 * 
	 * @param cell
	 *            the Polygon to draw as a triangle
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
		double xOffset = (cellSize / 2) * x; // make triangles interleave
		// corresponds to the left, right, and top/bottom vertices, respectively per x,y pair
		double x1, y1, x2, y2, x3, y3;
		if (x % 2 == 0) {
			x1 = gridOffset + x * cellSize - xOffset;
			y1 = gridOffset + y * cellSize;
			x2 = gridOffset + (x + 1) * cellSize - xOffset;
			y2 = y1;
			x3 = average(gridOffset + (x + 1) * cellSize, gridOffset + x * cellSize) - xOffset;
			y3 = gridOffset + (y + 1) * cellSize;
			cell.getPoints().addAll(new Double[] { x1, y1, x2, y2, x3, y3 });
		} else { // invert this triangle
			x1 = average(gridOffset + x * cellSize, gridOffset + (x + 1) * cellSize) - xOffset;
			y1 = gridOffset + y * cellSize;
			x2 = gridOffset + (x + 1) * cellSize - xOffset;
			y2 = gridOffset + (y + 1) * cellSize;
			x3 = gridOffset + x * cellSize - xOffset;
			y3 = y2;
			cell.getPoints().addAll(new Double[] { x1, y1, x2, y2, x3, y3 });
		}
		return cell;
	}
}
