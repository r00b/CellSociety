package gui;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import simulations.Grid;

public class SquareCell {

	protected Shape getCellNode(Grid grid, double cellWidth, double cellHeight, int gridOffset, int i, int j) {
		Rectangle squareCell = new Rectangle(gridOffset + i * cellWidth, gridOffset + j * cellHeight, cellWidth, cellHeight);
		squareCell.setFill(grid.getCell(i, j).getStateColor());
		squareCell.setStroke(Color.BLACK);
		String id = Integer.toString(i) + Integer.toString(j);
		squareCell.setId(id);
		return squareCell;
	}
	
}
