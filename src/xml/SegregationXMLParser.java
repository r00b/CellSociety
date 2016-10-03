package xml;

import javafx.scene.paint.Color;

/**
 * SegregationXMLParser is an XMLParser used specifically for Segregation simulations
 * It has methods to retrieve attributes specific to Segregation simulations (eg. satisfaction)
 * It assumes that the Segregation xml files have tags called satisfaction, percentOne, and percentTwo
 * If these tags do not exist, or if the data value is bad, it will use default values
 * @author Aaron Chang
 *
 */
public class SegregationXMLParser extends XMLParser{

	public SegregationXMLParser(String xmlFilename) {
		super(xmlFilename);
	}
	
	/**
	 * gets the required percent of neighbors that are similar for the agent to be satisfied
	 * @return int - percent (out of 100) required for agent to be satisfied
	 */
	public int getSatisfactionThreshold() {
		String defaultSatisfaction = myDefaultValueResources.getString("defaultSatisfaction");
		return getIntValueByTagName(myXmlTagResources.getString("satisfactionTag"), defaultSatisfaction);
	}
	
	/**
	 * gets the ratio of agents of type 1 to the whole grid
	 * @return int - percentage (out of 100) of agent1 types out of all cells in the grid
	 */
	public int getPercentOfAgentOne() {
		String defaultOne = myDefaultValueResources.getString("defaultPercentOne");
		return getIntValueByTagName(myXmlTagResources.getString("percentOneTag"), defaultOne);
	}
	
	/**
	 * gets the ratio of agents of type 2 to the whole grid
	 * @return int - percentage (out of 100) of agent2 types out of all cells in the grid
	 */
	public int getPercentOfAgentTwo() {
		String defaultTwo = myDefaultValueResources.getString("defaultPercentTwo");
		return getIntValueByTagName(myXmlTagResources.getString("percentTwoTag"), defaultTwo);
	}
	
	/**
	 * gets the ratio of empty cells out of the whole grid
	 * @return int - percentage (out of 100) of empty cells out of all cells in the grid
	 */
	public int getPercentEmpty() {
		return 100 - (getPercentOfAgentOne() + getPercentOfAgentTwo());
	}
	
	public Color getEmptyColor() {
		String defaultEmptyColor = myDefaultValueResources.getString("defaultEmptyColor");
		return getColor("emptyColorTag", defaultEmptyColor);
	}
	
	public Color getAgentOneColor() {
		String defaultOneColor = myDefaultValueResources.getString("defaultOneColor");
		return getColor("agentOneColorTag", defaultOneColor);
	}
	
	public Color getAgentTwoColor() {
		String defaultTwoColor = myDefaultValueResources.getString("defaultTwoColor");
		return getColor("agentTwoColorTag", defaultTwoColor);
	}
}
