package simulations.AntForaging;

import xml.ForagingAntsXMLParser;

public class Obstacle extends ForagingAntCell {
    public static final int OBSTACLE = 1;
    private ForagingAntsXMLParser myParser;

    public Obstacle(int i, int j, String xmlFilename) {
        super(i, j, xmlFilename);
        myParser = new ForagingAntsXMLParser(xmlFilename);
        setCurrState(OBSTACLE, myParser.getObstacleColor());
    }

    @Override
    public void mapStatesToColors() {
        updateColorMap(OBSTACLE, myParser.getObstacleColor());
    }
}
