package xml;

import javafx.scene.paint.Color;

public class ForagingAntsXMLParser extends XMLParser{
	
	public ForagingAntsXMLParser(String xmlFilename) {
		super(xmlFilename);
	}
	
	public int getNestILocation() {
		String defaultLocation = myDefaultValueResources.getString("defaultNestLocationI");
		return getIntValueByTagName(myXmlTagResources.getString("nestLocationITag"), defaultLocation);
	}
	
	public int getNestJLocation() {
		String defaultLocation = myDefaultValueResources.getString("defaultNestLocationJ");
		return getIntValueByTagName(myXmlTagResources.getString("nestLocationJTag"), defaultLocation);
	}
	
	public int getSourceILocation() {
		String defaultLocation = myDefaultValueResources.getString("defaultSourceLocationI");
		return getIntValueByTagName(myXmlTagResources.getString("sourceLocationITag"), defaultLocation);
	}
	public int getSourceJLocation() {
		String defaultLocation = myDefaultValueResources.getString("defaultSourceLocationJ");
		return getIntValueByTagName(myXmlTagResources.getString("sourceLocationJTag"), defaultLocation);
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
		return getColor("ObstacleColorTag", defaultObstacleColor);
	}
}
