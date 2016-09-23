

#Cell Society Outline

Note: this outline just gives a high level understanding of how classes interact with each other

###Animation.java

##### Instance Variables
  * mySimulation
  
##### Methods

  * initStep()
	  * creates an instance of Simulation (the specific instance will be determined by the ComboBox selection)
      * eg. mySimulation = new GameOfLifeSimulation();
	  * calls mySim.createGrid()
	  
### Simulation.java
##### Instance Variables
  * myGrid: 2D array
  * myXMLParser: specific xml parser object for that simulation
  
##### Methods
  * createGrid()
	  * uses XMLParser to get parameters gridWidth and gridHeight
	  * uses XMLParser to initialize state of cells
	  * when creating each cell, assigns ID to each cell (see Cell.java for details)
  * updateGrid()
	  * loops through cells and calls each cell's update() method
	  * places updated cells on a **new grid** so updated states won't affect how other cells calculate their new states

### Cell.java
##### Instance Variables
  * myID:  tuple which serves as an index for its position on the grid 
	  * takes the form (rowNum, columnNum)
  * myNeighbors: ArrayList of tuples representing the IDs of this cell's neighbors (could be a different data structure if we want)
  
##### Methods
  * calculateNeighborsIDs
	  * calculates the IDs of neighbors based on the Cell's own ID and stores those IDs into the attribute myNeighbors
  * update(Grid)
	  * uses an algorithm to update its state
	  * it needs Grid as a parameter b/c it needs to be able to find out the states of its neighbors
