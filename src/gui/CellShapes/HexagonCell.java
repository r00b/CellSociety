package gui.CellShapes;

import javafx.scene.shape.Polygon;

/**
 * @author Robert H. Steilberg II
 *         <p>
 *         The HexagonCell class creates a hexagon out of a JavaFX Polygon for
 *         drawing into a grid.
 */
public class HexagonCell implements CellShape {

    /**
     * Calculate the average of x and y
     *
     * @param x the first operand
     * @param y the second operand
     * @return a double representing the average of x and y
     */
    private double average(double x, double y) {
        return (x + y) / 2;
    }

    /**
     * Takes a new polygon and makes it into a hexagon by setting the
     * coordinates of its vertices
     *
     * @param cell       the Polygon to draw as a hexagon
     * @param cellSize   the height and width of each cell in pixels
     * @param gridOffset the position of the overall grid relative to the scene
     * @param x          the row of the cell
     * @param y          the column of the cell
     * @return the correctly positioned and shaped polygon
     */
    public Polygon createShape(Polygon cell, double cellSize, int gridOffset, double x, double y) {
        // corresponds to top left, top right, rightmost, bottom right, bottom
        // left, and leftmost vertices, respectively per x,y pair
        double x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6;
        double radius = cellSize / 2;
        double xOffset = (radius / 2) * x;
        double yOffset = radius;
        if (x % 2 == 0) {
            x1 = ((gridOffset + x * cellSize) + radius / 2) - xOffset;
            y1 = (gridOffset + y * cellSize) - yOffset;
            x2 = ((gridOffset + (x + 1) * cellSize) - radius / 2) - xOffset;
            y2 = y1;
            x3 = (gridOffset + (x + 1) * cellSize) - xOffset;
            y3 = average(gridOffset + y * cellSize, gridOffset + (y + 1) * cellSize) - yOffset;
            x4 = x2;
            y4 = (gridOffset + (y + 1) * cellSize) - yOffset;
            x5 = x1;
            y5 = y4;
            x6 = (gridOffset + x * cellSize) - xOffset;
            y6 = y3;
            cell.getPoints().addAll(new Double[]{x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6});
        } else {
            x1 = ((gridOffset + x * cellSize) + radius / 2) - xOffset;
            y1 = (gridOffset + y * cellSize);
            x2 = ((gridOffset + (x + 1) * cellSize) - radius / 2) - xOffset;
            y2 = y1;
            x3 = (gridOffset + (x + 1) * cellSize) - xOffset;
            y3 = average(gridOffset + y * cellSize, gridOffset + (y + 1) * cellSize);
            x4 = x2;
            y4 = (gridOffset + (y + 1) * cellSize);
            x5 = x1;
            y5 = y4;
            x6 = (gridOffset + x * cellSize) - xOffset;
            y6 = y3;
            cell.getPoints().addAll(new Double[]{x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6});
        }
        return cell;
    }
}
