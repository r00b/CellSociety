package xml;

import javafx.scene.paint.Color;

/**
 * This class is the XMLParser for the Foraging Ants simulation. It has methods that return parameters read in from an XML file
 * The methods in this class are all getters for parameters specific to the Foraging Ants simulation.
 * ForagingAntsXMLParser objects are created by ForagingAnts.java, and is used by that class to retrieve simulation parameters.
 * It assumes that most XML files for Foraging Ants simulations will have the tags/information listed in XmlTags.properties
 * However, if the tags do not exist, or if the input data is bad (eg. string when expecting int), there are default values for each parameter.
 * This class depends on XmlTags.properties and DefaultParameters.properties for information on the tags and default paraemters of the simulation.
 * <p>
 * NOTE: all methods in this class are getters
 * I commented on the first method to explain how it works, but the rest are self explanatory so I did not feel the need to comment.
 *
 * @author Aaron Chang
 */
public class ForagingAntsXMLParser extends XMLParser {

    public ForagingAntsXMLParser(String xmlFilename) {
        super(xmlFilename);
    }

    /**
     * This method returns the row on which the nest is located.
     * It searches the XML file for this value by using the tag name specified in XmlTags.properties
     * If the tag name does not exist, or if the data is bad, it will replace the input data with a default value
     * This default value is in DefaultParameters.properties
     * If the location is out of bounds of the grid, it returns the last row of the grid
     * This method is used by ForagingAnts.java
     *
     * @return int - row number of the nest
     */
    public int getNestILocation() {
        String defaultLocation = myDefaultValueResources.getString("defaultNestLocationI");
        int nestLocation = getIntValueByTagName(myXmlTagResources.getString("nestLocationITag"), defaultLocation);
        if (nestLocation > getGridHeight()) {
            return getGridHeight();
        } else {
            return nestLocation;
        }
    }

    public int getNestJLocation() {
        String defaultLocation = myDefaultValueResources.getString("defaultNestLocationJ");
        int nestLocation = getIntValueByTagName(myXmlTagResources.getString("nestLocationJTag"), defaultLocation);
        if (nestLocation > getGridWidth()) {
            return getGridWidth();
        } else {
            return nestLocation;
        }
    }

    /**
     * returns the row on which the food source is located
     * If the source is out of bounds of the grid, it returns the first row of the grid
     *
     * @return int - row location of the food source
     */
    public int getSourceILocation() {
        String defaultLocation = myDefaultValueResources.getString("defaultSourceLocationI");
        int sourceLocation = getIntValueByTagName(myXmlTagResources.getString("sourceLocationITag"), defaultLocation);
        if (sourceLocation > getGridHeight()) {
            return 0;
        } else {
            return sourceLocation;
        }
    }

    public int getSourceJLocation() {
        String defaultLocation = myDefaultValueResources.getString("defaultSourceLocationJ");
        int sourceLocation = getIntValueByTagName(myXmlTagResources.getString("sourceLocationJTag"), defaultLocation);
        if (sourceLocation > getGridWidth()) {
            return 0;
        } else {
            return sourceLocation;
        }
    }

    public int getMaxAnts() {
        String defaultMaxAnts = myDefaultValueResources.getString("defaultMaxAnts");
        return getIntValueByTagName(myXmlTagResources.getString("maxAntsTag"), defaultMaxAnts);
    }

    public int getMaxAntsPerLocation() {
        String defaultMaxAntsPerLocation = myDefaultValueResources.getString("defaultMaxAntsPerLocation");
        return getIntValueByTagName(myXmlTagResources.getString("maxAntsPerLocationTag"), defaultMaxAntsPerLocation);
    }

    public int getAntLifetime() {
        String defaultAntLifetime = myDefaultValueResources.getString("defaultAntLifetime");
        return getIntValueByTagName(myXmlTagResources.getString("antLifetimeTag"), defaultAntLifetime);
    }

    public int getNumAntsAtNest() {
        String defaultNestAnts = myDefaultValueResources.getString("defaultNumAntsAtNest");
        return getIntValueByTagName(myXmlTagResources.getString("numAntsAtNestTag"), defaultNestAnts);
    }

    public int getNumAntsBornPerStep() {
        String defaultAntsPerStep = myDefaultValueResources.getString("defaultAntsBornPerStep");
        return getIntValueByTagName(myXmlTagResources.getString("antsBornPerStepTag"), defaultAntsPerStep);
    }

    public float getMinPheremone() {
        String defaultMinPheremone = myDefaultValueResources.getString("defaultMinPheremone");
        return getFloatValueByTagName(myXmlTagResources.getString("minPheremoneTag"), defaultMinPheremone);
    }

    public float getMaxPheremone() {
        String defaultMaxPheremone = myDefaultValueResources.getString("defaultMaxPheremone");
        return getFloatValueByTagName(myXmlTagResources.getString("maxPheremoneTag"), defaultMaxPheremone);
    }

    public float getEvaporationRatio() {
        String defaultEvapRatio = myDefaultValueResources.getString("defaultEvaporationRatio");
        return getFloatValueByTagName(myXmlTagResources.getString("evaporationRatioTag"), defaultEvapRatio);
    }

    public float getDiffusionRatio() {
        String defaultDiffusionRatio = myDefaultValueResources.getString("defaultDiffusionRatio");
        return getFloatValueByTagName(myXmlTagResources.getString("diffusionRatioTag"), defaultDiffusionRatio);
    }

    public int getPercentObstacles() {
        String defaultPercentObstacles = myDefaultValueResources.getString("defaultPercentObstacles");
        return getIntValueByTagName(myXmlTagResources.getString("percentObstaclesTag"), defaultPercentObstacles);
    }

    public float getK() {
        String defaultK = myDefaultValueResources.getString("defaultK");
        return getFloatValueByTagName(myXmlTagResources.getString("kTag"), defaultK);
    }

    public float getN() {
        String defaultN = myDefaultValueResources.getString("defaultN");
        return getFloatValueByTagName(myXmlTagResources.getString("nTag"), defaultN);
    }

    public Color getNestColor() {
        String defaultNestColor = myDefaultValueResources.getString("defaultNestColor");
        return getColor("nestColorTag", defaultNestColor);
    }

    public Color getFoodSourceColor() {
        String defaultSourceColor = myDefaultValueResources.getString("defaultFoodSourceColor");
        return getColor("foodSourceColorTag", defaultSourceColor);
    }

    public Color getAntsColor() {
        String defaultAntsColor = myDefaultValueResources.getString("defaultAntsColor");
        return getColor("antsColorTag", defaultAntsColor);
    }

    public Color getFullCellColor() {
        String defaultFullCellColor = myDefaultValueResources.getString("defaultFullCellColor");
        return getColor("fullCellColorTag", defaultFullCellColor);
    }

    public Color getObstacleColor() {
        String defaultObstacleColor = myDefaultValueResources.getString("defaultObstacleColor");
        return getColor("obstacleColorTag", defaultObstacleColor);

    }

    public Color getEmptyColor() {
        String defaultEmptyColor = myDefaultValueResources.getString("defaultEmptyColor");
        return getColor("emptyColorTag", defaultEmptyColor);
    }
}
