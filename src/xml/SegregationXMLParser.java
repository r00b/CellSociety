package xml;

import javafx.scene.paint.Color;

/**
 * SegregationXMLParser is an XMLParser used specifically for Segregation simulations
 * It has methods to retrieve attributes specific to Segregation simulations (eg. satisfaction)
 * It assumes that the Segregation xml files have tags called satisfaction, percentOne, and percentTwo
 * @author Aaron Chang
 *
 */
public class SegregationXMLParser extends XMLParser{
	public static final String DEFAULT_SATISFACTION = myDefaultValueResources.getString("defaultSatisfaction");
	public static final String DEFAULT_PERCENT_ONE = myDefaultValueResources.getString("defaultPercentOne");
	public static final String DEFAULT_PERCENT_TWO = myDefaultValueResources.getString("defaultPercentTwo");
	public static final String DEFAULT_EMPTY_COLOR = myDefaultValueResources.getString("defaultEmptyColor");
	public static final String DEFAULT_ONE_COLOR = myDefaultValueResources.getString("defaultOneColor");
	public static final String DEFAULT_TWO_COLOR = myDefaultValueResources.getString("defaultTwoColor");

	public SegregationXMLParser(String xmlFilename) {
		super(xmlFilename);
	}
	
	/**
	 * gets the required percent of neighbors that are similar for the agent to be satisfied
	 * @return int - percent (out of 100) required for agent to be satisfied
	 */
	public int getSatisfactionThreshold() {
		return getIntValueByTagName(myXmlTagResources.getString("satisfactionTag"),DEFAULT_SATISFACTION);
	}
	
	/**
	 * gets the ratio of agents of type 1 to the whole grid
	 * @return int - percentage (out of 100) of agent1 types out of all cells in the grid
	 */
	public int getPercentOfAgentOne() {
		return getIntValueByTagName(myXmlTagResources.getString("percentOneTag"), DEFAULT_PERCENT_ONE);
	}
	
	/**
	 * gets the ratio of agents of type 2 to the whole grid
	 * @return int - percentage (out of 100) of agent2 types out of all cells in the grid
	 */
	public int getPercentOfAgentTwo() {
		return getIntValueByTagName(myXmlTagResources.getString("percentTwoTag"), DEFAULT_PERCENT_TWO);
	}
	
	/**
	 * gets the ratio of empty cells out of the whole grid
	 * @return int - percentage (out of 100) of empty cells out of all cells in the grid
	 */
	public int getPercentEmpty() {
		return 100 - (getPercentOfAgentOne() + getPercentOfAgentTwo());
	}
	
	public Color getEmptyColor() {
		String colorString = getTextValueByTagName(myXmlTagResources.getString("emptyColorTag"), DEFAULT_EMPTY_COLOR);
		return Color.valueOf(colorString);
	}
	
	public Color getAgentOneColor() {
		String colorString = getTextValueByTagName(myXmlTagResources.getString("agentOneColorTag"), DEFAULT_ONE_COLOR);
		return Color.valueOf(colorString);
	}
	
	public Color getAgentTwoColor() {
		String colorString = getTextValueByTagName(myXmlTagResources.getString("agentTwoColorTag"), DEFAULT_TWO_COLOR);
		return Color.valueOf(colorString);
	}
}
