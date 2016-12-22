package gui;

import gui.CellShapes.HexagonCell;
import gui.CellShapes.SquareCell;
import gui.CellShapes.TriangleCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import simulations.Grid;

/**
 * @author Robert H. Steilberg II | rhs16
 *         <p>
 *         The CellNode class creates a Polygon object for each cell of the
 *         grid. Each Node is created with the correct state according to the
 *         simulation and the correct position according to the grid specified
 *         via the Animation class. Each Node object is created as a user-
 *         specified shape; the points of the polygon are determined depending
 *         on which shape should be drawn.
 *         <p>
 *         Dependencies: Animation.java
 */
public class CellNode {

    /**
     * @param grid        the Grid object in which the cells are located
     * @param cellSize    the height and width of each cell in pixels
     * @param gridOffset  the position of the overall grid relative to the scene
     * @param i           the cell's column
     * @param j           the cell's row
     * @param numVertices the number of vertices that each Node should have (i.e. the
     *                    shape)
     * @return a ready-to-print Polygon with the correct position, size, and
     * state
     */
    protected Polygon getCellNode(Grid grid, double cellSize, int gridOffset, int i, int j, int numVertices) {
        Polygon polygon = new Polygon();
        // cast i,j to double so that we can create polygon points
        double x = (double) i;
        double y = (double) j;
        if (numVertices == 3) {
            TriangleCell triangle = new TriangleCell();
            polygon = triangle.createShape(polygon, cellSize, gridOffset, x, y);
        }
        if (numVertices == 4) {
            SquareCell square = new SquareCell();
            polygon = square.createShape(polygon, cellSize, gridOffset, x, y);
        }
        if (numVertices == 6) {
            HexagonCell hexagon = new HexagonCell();
            polygon = hexagon.createShape(polygon, cellSize, gridOffset, x, y);
        }
        // id so that the Node can be removed from the Scene later
        String id = Integer.toString(i) + Integer.toString(j);
        polygon.setId(id);
        polygon.setFill(grid.getCell(i, j).getStateColor());
        polygon.setStroke(Color.LIGHTGREY);
        return polygon;
    }
}
