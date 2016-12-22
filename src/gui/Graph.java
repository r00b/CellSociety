package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import simulations.Cell;
import simulations.Grid;
import simulations.Simulation;

/**
 * The Graph object is used to visualize the populations of each kind of cell over time
 * It is used by the Animation class and adds itself to the root of the Animation object
 * It depends on each Simulation class to create a map of Cell states (integer to string)
 *
 * @author Aaron Chang
 */
public class Graph {
    private Simulation mySimulation;
    private Pane myRoot;
    private HashMap<Integer, XYChart.Series<Number, Number>> mySeriesMap;
    private int xSeriesData = 0;

    private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    private static final String LANGUAGE = "English";
    private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + LANGUAGE);

    public Graph(Simulation sim, Pane root) {
        mySimulation = sim;
        myRoot = root;
        mySeriesMap = new HashMap<Integer, XYChart.Series<Number, Number>>();
        init();
    }

    private void init() {
        //create axes and graph
        final NumberAxis xAxis = new NumberAxis();
        double maxY = mySimulation.getGridHeight() * mySimulation.getGridWidth();
        final NumberAxis yAxis = new NumberAxis(0, maxY, maxY / 10);
        xAxis.setLabel("Time");
        yAxis.setLabel("Number of Cells");
        final LineChart<Number, Number> graph = new LineChart<Number, Number>(xAxis, yAxis);
        graph.setTitle("Cell State Populations");
        graph.setId(myResources.getString("GraphID"));
        //create objects to hold data
        for (int stateInt : mySimulation.getStateMap().keySet()) {
            XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
            series.setName(mySimulation.getStateMap().get(stateInt));
            mySeriesMap.put(stateInt, series);
            graph.getData().add(series);
        }
        graph.setLayoutX(750);
        graph.setLayoutY(130);
        myRoot.getChildren().add(graph);
    }

    /**
     * loops through the Grid, calculates populations of each cell type, and updates value
     * called by animation.java for each step
     *
     * @param grid - the simulation's grid
     */
    public void updateGraph(Grid grid) {
        ArrayList<Integer> stateCount = new ArrayList<Integer>();
        for (int i = 0; i < mySeriesMap.size(); i++) {
            stateCount.add(0);
        }
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                Cell cell = grid.getCell(i, j);
                int oldCount = stateCount.get(cell.getCurrState());
                stateCount.set(cell.getCurrState(), oldCount + 1);
            }
        }
        for (int state = 0; state < stateCount.size(); state++) {
            mySeriesMap.get(state).getData().add(new XYChart.Data<Number, Number>(xSeriesData, stateCount.get(state)));
        }
        xSeriesData += 1;
    }

    /**
     * removes the Graph object from the scene
     * this is called by Animation.java when the simulation type changes
     */
    public void clearGraph() {
        myRoot.getChildren().remove(myRoot.lookup("#" + myResources.getString("GraphID")));
    }


}
