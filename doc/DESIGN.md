#CompSci 308: JavaFX [CellSociety](http://www.cs.duke.edu/courses/compsci308/fall16/assign/02_cellsociety/part1_SDG.php) DESIGN.md

Robert Steilberg | rhs16
Sam Curtis | sjc43
Aaron Chang | amc120

**NOTE**: GitLab has uploaded our images in incorrect orientations (i.e. upside-down). We apologize for this inconvenience, but we don't know how to fix this problem.

##Introduction
Our program implements a 2D grid cellular automata simulation through JavaFX. The state information of each cell is updated each frame step based on rules applied to their state and those of their neighbors. Simulations run until stopped or changed by the user.

The program accommodates a number of different specifications regarding how and when cells change states, how the cells interact with their neighbors, how and when they "move", the size of the grid, the grid edge type, and the shape of the grid cells. There are also XML specifications unique to each different simulation. A user interface facilitates loading in or changing XML files, starting, pausing, and stopping the simulation, stepping through the simulation, and changing the simulation's step rate. The user is able to select from different simulation options and can simulate multiple scenarios by loading in different configuration files. Rules are applied to each cell based on their current state, after which their states are updated during the next iteration. Apart from the rules, the basic structure and process of the cellular automata simulation cannot be changed by the user and will be "closed". However, our code is organized in a way that makes it relatively simple to add new simulations or GUI features.

##Overview
Our project is divided into 4 super-packages: the *default package*, the *gui package*, the *simulation package*, and the *xml package*. There is also a *resource package* containing our CSS and properties files. Furthermore, there are multiple sub-packages. The *gui package* includes a sub-package *gui.CellShapes* that involves defining different grid cell shapes. The *simulation package* has a sub-package for each simulation type (i.e. *simulations.GameOfLife*) These packages, their classes and methods, and their relationships are shown in the "Class-hierarchy.jpg" image file, created during the planning phase of this project (but slightly outdated now in post-production). The *default package* will contain only the Main class that starts the application.

The *GUI package* contains the classes responsible for implementing the view of the program. This package also contains the game loop/step function, which will import data (specifically about the grid of cells) from the *simulation package* and update the view accordingly. This package also presents menu options and the cell state graph to the user.

The *simulation package* handles updating the state of the cells. The main simulation class (i.e. GameOfLife.java) will get information about the simulation parameters from its associated XMLParser class (i.e. GameOfLifeXMLParser.java) and update the initial states of the cells according to those rules. There will also be an associated cell class (i.e. GameOfLifeCell.java) and grid class (i.e. GameOfLifeGrid.java) that handles creating each cell and the overall grid for each simulation step.  Grid.java implements a 2D array of Cells, and its updateGrid() method will call the updateState() method of each cell.

##User Interface
The user interface is depicted in the "User-interface.jpg" image file, created during the planning phase of this project (but slightly outdated now in post-production). The cellular automata simulation will be displayed as a grid in a window. To the right of the grid, a drop down menu will contain a list of simulation specifications that can be selected by the user and will implement the various rules associated with each scenario. A play button will begin the simulation, a pause button will temporarily stop it, a stop button will stop the simulation and clear the grid, and a step button will step through individual frames of the simulation. An XML file-change button will handle changing the initial simulation parameters by prompting the user to change the XML file. Finally, there will be a slider for specifying the step speed of the simulation. There will be a verification check to make sure that the imported XML configuration file is properly formatted and readable. If it isn’t, a message will be displayed to the user that an error has occurred and the simulation cannot proceed as expected. A graph is also displayed that documents the populations of each cell state in each frame of the simulation.

##Design Details

*default package*

* Main.java
	* The main class will simply handle launching the JavaFX program and calling the Animation class.

*simulation package*

* Grid.java
	* The Grid.java class will define a two dimensional array of cells. It will always have two methods: initializeGrid(), updateGrid(). InitializeGrid() will take in a size parameter based on the user input. UpdateGrid() will loop through all cells and call cell.updateState() on each one. This class is intended primarily to store information about the cellular automata 2D grid.
* Cell.java
	* The Cell.java class will have an instance variable which holds all of its possible states. It will also have a boolean value representing whether or not it is an edgeCell, determined upon simulation initialization. It will have instance variables for its generation and for its previous state, and an instance variable that holds the collection of all its neighbors. Each cell will have the ability to update its state, report its current state, report how many neighbors it has, report whether it is an edge cell, and reset itself to its initial state. This class is intended primarily to store information about each cell in the grid.

*gui package*

* Animation.java
	* The Animation.java class will handle stepping through the automata simulation and displaying the simulation in the user interface. This class will serve as the GUI "engine" of the simulation. It will have a grid variable that specifies the current cell grid. The animation speed variable will specify the speed with which the program steps through each frame. Animation will implement functionality for starting the animation, pausing the animation, and stopping and resetting the entire grid in preparation for a new user specification or simulation. There will be a method that handles specification of the animation speed and stepping through individual frames of the simulation. This class will interact extensively with Grid.java and Cell.java from the *simulation package* to gain the information required to render the cellular automata simulation.
* CellNode.java
	* The CellNode class handles implementing each individual cell of the grid and the shape of that cell.
* SimControls.java
	* The SimControls class handles the GUI elements that control the flow of the simulation.
* SimEvents.java
	* The SimEvents class handles the event handlers associated with the GUI elements from the SimControls class.
* GridParser.java
	* The GridParser class handles drawing the grid to the canvas during each step.
* FileBrowser.java
	* The FileBrowser class handles the file chooser used to pick an XML file to load into the simulation.

**Use Cases:**
1. To apply the rules to a middle cell, we would call cell.updateState() on the cell, which would loop through all the cells in the current cell's Neighbors field, call each of those cell's getCurrentState() methods, and then determine the new state of the cell based upon those results. 
2. Applying the rules to an edge cell would work in the exact same way as applying rules to a middle cell, however an edge cell would just have a different number of neighbors. This would be determined upon the cell's initialization, where its edgeCell field would be set to true or false depending upon its position in the grid. 
3. To move to the next generation, we would simply call GridParser.updateGrid(), which would loop through every cell in the grid, and  call the update cell method for each one. 
4. To set a simulation parameter, the XMLParser class’ setParameters() method would be invoked. This method would define the conditions in which a cell changes state and how cells interact with their neighbors. 
5. To switch simulations, the Animation class' resetSimulation() would be invoked, which would stop the simulation step, clear the grid, and prepare the user interface for the new XML configuration specifications.

##Design Considerations
The biggest design issue that we dealt with is how each cell’s state would be updated in regards to its previous state and the state of the cell’s neighbors. We discussed at length which class would handle aggregating all of this data and making a final decision on a cell’s new state. Animation.java could handle updating states, making the implementation simpler since GUI updates would be computed in the same class as the states. However, we decided it would be better design to keep state computations in a separate package with other simulation-related classes, such as Grid.java and Cell.java. This makes implementation more complex but makes more sense design-wise, since we want classes within the *gui package* to focus specifically on updating the user interface, not performing computation regarding cell state changes, which is the intended function of the *simulation package*.

We also discussed how we would update the state of a cell without affecting how neighbor cells are subsequently calculated. We decided that we would rebuild a new grid with every step, pulling information from the old grid to determine the states of the new grid. This way, there is no possibility that an already-updated state could affect the next calculated state of other cells.

##Team Responsibilities
This is how we divided our work:

* Robert’s primary responsibility was  to implement most components of the user interface (the entire *gui package*, with the exception of Graph.java)
* Aaron’s primary responsibility was to implement the XMLParser classes. This included reading in the XML files and creating the rules for each cell. Aaron also worked on the back end simulations, such as the Segregation and Wa-tor simulations.
* Sam’s primary responsibility was to implement the Grid.java and Cell.java classes that will handle cell state changes in addition to other back end simulations, such as the GameOfLife, ForagingAnts, and Fire simulations.
