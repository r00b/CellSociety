package simulations.AntForaging;

import java.security.KeyStore.PrivateKeyEntry;

import simulations.Cell;
import simulations.Grid;
import xml.ForagingAntsXMLParser;

public class Nest extends ForagingAntCell {
    public static final int NEST = 5;
    private ForagingAntsXMLParser myParser;
    private int antsLeftToBirth;
    private int antsBornPerStep;
    private int numBirthed;
    private int lifetime;

    public Nest(int i, int j, String XMLFileName) {
        super(i, j, XMLFileName);
        myParser = new ForagingAntsXMLParser(XMLFileName);
        setCurrState(NEST, myParser.getNestColor());
        antsLeftToBirth = myParser.getMaxAnts();
        antsBornPerStep = myParser.getNumAntsBornPerStep();
        lifetime = myParser.getAntLifetime();
        numBirthed = 0;
        birthAnts();
    }

    public void birthAnts() {
        numBirthed++;
        if (numBirthed / (float) lifetime > 1) {
            antsLeftToBirth += antsBornPerStep;
        }
        for (int i = 0; i < antsBornPerStep; i++) {
            if (antsLeftToBirth > 0) {
                addAnt(new Ant(this, myParser.getAntLifetime()));
                antsLeftToBirth--;
            }
        }
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
        getMyNeighborhood().set_EightNeighbor_NoWraparound(this, grid);
    }


}
