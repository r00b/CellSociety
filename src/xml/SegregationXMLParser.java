package xml;
/**
 * SegregationXMLParser is an XMLParser used specifically for Segregation simulations
 * It has methods to retrieve attributes specific to Segregation simulations (eg. satisfaction)
 * It assumes that the Segregation xml files have tags called satisfaction, percentOne, and percentTwo
 * @author Aaron Chang
 *
 */
public class SegregationXMLParser extends XMLParser{
	private static final String SATISFACTION_TAG = "satisfaction";
	private static final String PERCENT_AGENT_ONE_TAG = "percentOne";
	private static final String PERCENT_AGENT_TWO_TAG = "percentTwo";
	
	public SegregationXMLParser(String xmlFilename) {
		super(xmlFilename);
	}
	
	/**
	 * gets the required percent of neighbors that are similar for the agent to be satisfied
	 * @return int - percent (out of 100) required for agent to be satisfied
	 */
	public int getSatisfactionRate() {
		return getIntValueByTagName(SATISFACTION_TAG);
	}
	
	/**
	 * gets the ratio of agents of type 1 to the whole grid
	 * @return int - percentage (out of 100) of agent1 types out of all cells in the grid
	 */
	public int getPercentOfAgentOne() {
		return getIntValueByTagName(PERCENT_AGENT_ONE_TAG);
	}
	
	/**
	 * gets the ratio of agents of type 2 to the whole grid
	 * @return int - percentage (out of 100) of agent2 types out of all cells in the grid
	 */
	public int getPercentOfAgentTwo() {
		return getIntValueByTagName(PERCENT_AGENT_TWO_TAG);
	}
	
	/**
	 * gets the ratio of empty cells out of the whole grid
	 * @return int - percentage (out of 100) of empty cells out of all cells in the grid
	 */
	public int getPercentEmpty() {
		return 100 - (getPercentOfAgentOne() + getPercentOfAgentTwo());
	}
}
