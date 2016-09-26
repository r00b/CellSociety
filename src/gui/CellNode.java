package gui;

import javafx.scene.shape.Polygon;
import simulations.Grid;

public class CellNode {

	protected Polygon getCellNode(Grid grid, double cellSize, int gridOffset, int i, int j, int numVertices) {
		Polygon node = new Polygon();
		String id = Integer.toString(i) + Integer.toString(j);
		node.setId(id);
		if (numVertices == 3) {
			// node = createTriangleNode();
		}
		if (numVertices == 4) {
			double x = (double) i;
			double y = (double) j;
			node.getPoints().addAll(new Double[] { // x = 0, y = 0
				gridOffset + x * cellSize, gridOffset + y * cellSize, 
				gridOffset + (x + 1) * cellSize, gridOffset + y * cellSize,
				gridOffset + (x + 1) * cellSize, gridOffset + (y + 1) * cellSize,
				gridOffset + x * cellSize, gridOffset + (y + 1) * cellSize });
			node.setFill(grid.getCell(i, j).getStateColor());
		}
		if (numVertices == 6) {
			// node = createHexagonNode();
		}

		return node;
	}
}
