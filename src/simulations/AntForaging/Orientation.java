package simulations.AntForaging;

import java.util.ArrayList;

import simulations.Tuple;

public class Orientation {
	private static final String NORTH = "N";
	private static final String NORTHEAST = "NE";
	private static final String EAST = "E";
	private static final String SOUTHEAST = "SE";
	private static final String SOUTH = "S";
	private static final String SOUTHWEST = "SW";
	private static final String WEST = "W";
	private static final String NORTHWEST = "NW";
	private String myOrientation;
	
	public ArrayList<Tuple> getForwardLocations(ForagingAntCell currentLocation){
		ArrayList<Tuple> forwardLocations = new ArrayList<Tuple>();
		int i = currentLocation.getPosition().getIPos();
		int j = currentLocation.getPosition().getJPos();
		Tuple forward;
		Tuple leftForward;
		Tuple rightForward;
		if(myOrientation.equals(NORTH)){
			forward = new Tuple(i-1, j);
			leftForward = new Tuple(i-1, j-1);
			rightForward = new Tuple(i-1, j+1);
		}
		else if(myOrientation.equals(NORTHEAST)){
			forward = new Tuple(i-1, j+1);
			leftForward = new Tuple(i-1, j);
			rightForward = new Tuple(i, j+1);
		}
		else if(myOrientation.equals(EAST)){
			forward = new Tuple(i, j+1);
			leftForward = new Tuple(i-1, j+1);
			rightForward = new Tuple(i+1, j+1);
		}
		else if(myOrientation.equals(SOUTHEAST)){
			forward = new Tuple(i+1, j+1);
			leftForward = new Tuple(i, j+1);
			rightForward = new Tuple(i+1, j);
		}
		else if(myOrientation.equals(SOUTH)){
			forward = new Tuple(i+1, j);
			leftForward = new Tuple(i+1, j+1);
			rightForward = new Tuple(i+1, j-1);
		}
		else if(myOrientation.equals(SOUTHWEST)){
			forward = new Tuple(i+1, j-1);
			leftForward = new Tuple(i+1, j);
			rightForward = new Tuple(i, j+1);
		}
		else if(myOrientation.equals(WEST)){
			forward = new Tuple(i, j-1);
			leftForward = new Tuple(i+1, j-1);
			rightForward = new Tuple(i-1, j-1);
		}
		else{
			forward = new Tuple(i-1, j-1);
			leftForward = new Tuple(i, j-1);
			rightForward = new Tuple(i-1, j);
		}
		forwardLocations.add(forward);
		forwardLocations.add(leftForward);
		forwardLocations.add(rightForward);
		return forwardLocations;
	}
	
	public void setOrientation(ForagingAntCell currCell, ForagingAntCell nextCell){
		if(nextCell == null){
			myOrientation = NORTH;
			return;
		}
		int currI = currCell.getPosition().getIPos();
		int currJ = currCell.getPosition().getJPos();
		int nextI = nextCell.getPosition().getIPos();
		int nextJ = nextCell.getPosition().getJPos();
		if(nextI == currI -1 && nextJ == currJ - 1){
			myOrientation = NORTHWEST;
		}
		else if(nextI == currI -1 && nextJ == currJ){
			myOrientation = NORTH;
		}
		else if(nextI == currI -1 && nextJ == currJ + 1){
			myOrientation = NORTHEAST;
		}
		else if(nextI == currI && nextJ == currJ + 1){
			myOrientation = EAST;
		}
		else if(nextI == currI + 1 && nextJ == currJ + 1){
			myOrientation = SOUTHEAST;
		}
		else if(nextI == currI + 1 && nextJ == currJ){
			myOrientation = SOUTH;
		}
		else if(nextI == currI + 1 && nextJ == currJ - 1){
			myOrientation = SOUTHWEST;
		}
		else{
			myOrientation = WEST;
		}	
	}
	
}
