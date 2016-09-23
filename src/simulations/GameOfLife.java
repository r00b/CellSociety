package simulations;


import xml.XMLParser;

public class GameOfLife {
	private final Grid myGrid;
	private final XMLParser myParser;
	
	public GameOfLife() {
		myParser = new XMLParser("../data/GameOfLife.xml");
		myGrid = new Grid(Integer.parseInt(myParser.getTextValueByTagName("gridHeight")));
	}
	
	
}
