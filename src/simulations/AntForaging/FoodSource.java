package simulations.AntForaging;

import xml.ForagingAntsXMLParser;

public class FoodSource extends ForagingAntCell {
	private ForagingAntsXMLParser myParser;
	private static final int FOOD = 7;
	
	public FoodSource(int i, int j, String xmlFilename) {
		super(i, j, xmlFilename);
		myParser = new ForagingAntsXMLParser(xmlFilename);
		setCurrState(FOOD, myParser.getFoodSourceColor());
	}
	
	@Override
	public void mapStatesToColors(){
		updateColorMap(FOOD, myParser.getFoodSourceColor());
	}
}
