package simulations.AntForaging;

import xml.ForagingAntsXMLParser;

public class FoodSource extends ForagingAntCell {
	private ForagingAntsXMLParser myParser;
	private static final int FOOD = 0;
	
	public FoodSource(int i, int j, String xmlFilename) {
		super(i, j, xmlFilename);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void mapStatesToColors(){
		updateColorMap(FOOD, myParser.getFoodSourceColor());
	}
}
