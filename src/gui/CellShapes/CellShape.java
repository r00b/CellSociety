package gui.CellShapes;

import javafx.scene.shape.Polygon;

/**
 * Interface for a class defining the shape of a grid cell
 * 
 * @author Robert H. Steilberg II | rhs16
 *
 */
public interface CellShape {

	public Polygon createShape(Polygon cell, double cellSize, int gridOffset, double x, double y);
}
