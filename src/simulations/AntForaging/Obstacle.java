package simulations.AntForaging;

import xml.ForagingAntsXMLParser;

public class Obstacle extends ForagingAntCell {
	private static final int OBSTACLE = 5;
	private ForagingAntsXMLParser myParser;
	
	public Obstacle(int i, int j, String xmlFilename) {
		super(i, j, xmlFilename);
		myParser = new ForagingAntsXMLParser(xmlFilename);
		setCurrState(OBSTACLE, myParser.getObstacleColor());
		mapStatesToColors();
	}
	
	@Override
	public void mapStatesToColors(){
		updateColorMap(OBSTACLE, myParser.getObstacleColor());
	}
}
