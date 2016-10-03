package simulations.AntForaging;

import xml.ForagingAntsXMLParser;

public class FoodSource extends ForagingAntCell {
	private ForagingAntsXMLParser myParser;
	public static final int FOOD = 4;
	
	public FoodSource(int i, int j, String xmlFilename) {
		super(i, j, xmlFilename);
		myParser = new ForagingAntsXMLParser(xmlFilename);
		setCurrState(FOOD, myParser.getFoodSourceColor());
		topOffFoodPheremones();
	}
	
	@Override
	public void mapStatesToColors(){
		updateColorMap(FOOD, myParser.getFoodSourceColor());
	}
}
