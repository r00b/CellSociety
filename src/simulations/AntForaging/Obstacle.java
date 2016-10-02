package simulations.AntForaging;

import xml.ForagingAntsXMLParser;

public class Obstacle extends ForagingAntCell {
	private static final int OBSTACLE = 0;
	private ForagingAntsXMLParser myParser;
	
	public Obstacle(int i, int j, String xmlFilename) {
		super(i, j, xmlFilename);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void mapStatesToColors(){
		updateColorMap(OBSTACLE, myParser.getObstacleColor());
	}
}
