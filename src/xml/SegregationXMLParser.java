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
	
	public SegregationXMLParser(String xmlFilename) {
		super(xmlFilename);
	}
	
	/**
	 * gets the required percent of neighbors that are similar for the agent to be satisfied
	 * @return int - percent (out of 100) required for agent to be satisfied
	 */
	public int getSatisfactionThreshold() {
		return getIntValueByTagName(myResources.getString("satisfactionTag"));
	}
	
	/**
	 * gets the ratio of agents of type 1 to the whole grid
	 * @return int - percentage (out of 100) of agent1 types out of all cells in the grid
	 */
	public int getPercentOfAgentOne() {
		return getIntValueByTagName(myResources.getString("percentOneTag"));
	}
	
	/**
	 * gets the ratio of agents of type 2 to the whole grid
	 * @return int - percentage (out of 100) of agent2 types out of all cells in the grid
	 */
	public int getPercentOfAgentTwo() {
		return getIntValueByTagName(myResources.getString("percentTwoTag"));
	}
	
	/**
	 * gets the ratio of empty cells out of the whole grid
	 * @return int - percentage (out of 100) of empty cells out of all cells in the grid
	 */
	public int getPercentEmpty() {
		return 100 - (getPercentOfAgentOne() + getPercentOfAgentTwo());
	}
	
	public Color getEmptyColor() {
		String colorString = getTextValueByTagName(myResources.getString("emptyColorTag"));
		return Color.valueOf(colorString);
	}
	
	public Color getAgentOneColor() {
		String colorString = getTextValueByTagName(myResources.getString("agentOneColorTag"));
		return Color.valueOf(colorString);
	}
	
	public Color getAgentTwoColor() {
		String colorString = getTextValueByTagName(myResources.getString("agentTwoColorTag"));
		return Color.valueOf(colorString);
	}
}
