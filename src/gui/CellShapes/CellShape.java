package gui.CellShapes;

import javafx.scene.shape.Polygon;

/**
 * Interface for a class defining the shape of a grid cell
 * 
 * @author Robert H. Steilberg II | rhs16
 *
 */
public interface CellShape {

	/**
	 * Takes a new polygon and makes it into a shape by setting the
	 * coordinates of its vertices
	 * 
	 * @param cell
	 *            the Polygon to draw
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
	public Polygon createShape(Polygon cell, double cellSize, int gridOffset, double x, double y);
}
