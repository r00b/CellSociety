package simulations.AntForaging;

import simulations.Cell;
import simulations.Grid;
import xml.ForagingAntsXMLParser;

public class Nest extends ForagingAntCell {
	private static final int NEST = 0;
	private ForagingAntsXMLParser myParser;
	
	public Nest(int i, int j, String XMLFileName) {
		super(i, j, XMLFileName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mapStatesToColors() {
		updateColorMap(NEST, myParser.getNestColor());
	}

	@Override
	public void setRandomInitialState() {
		// TODO Auto-generated method stub
	}

	@Override
	public void setNeighborhood(Grid grid) {
		// TODO Auto-generated method stub
		
	}

}
