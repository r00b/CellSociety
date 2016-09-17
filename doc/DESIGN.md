CompSci 308: JavaFX [CellSociety](http://www.cs.duke.edu/courses/compsci308/fall16/assign/02_cellsociety/part1_SDG.php) DESIGN.md
======================
Robert Steilberg | rhs16
Sam Curtis | sjc43
Aaron Chang | amc120

Introduction
---------------------
We intend for our program to implement a 2D grid cellular automata simulation through JavaFX. We will implement a simulation where the state information of each cell is updated each step based on rules applied to their state and those of their neighbors. Simulations will be able to run indefinitely.

The program will be able to accommodate a number of different specifications regarding how and when cells change states, how the cells interact with their neighbors, how and when they "move", the size of the grid, et cetera. These specifications will be stipulated by a XML file that will be loaded into the program. A user interface will facilitate loading in XML files, starting and stopping the simulation, stepping through the simulation, and changing the simulation's step rate. The user will be able to select from different simulation options and will be able to simulate multiple scenarios by loading in different configuration files. Rules are applied to each cell based on their current state, after which their states are updated during the next iteration. Apart from the rules, the basic structure and process of the cellular automata simulation cannot be changed by the user and will be "closed".

Overview
---------------------
Our project is divided into 3 packages: the *default package*, the *GUI package*, and the *SIMULATION package*. These packages, their classes and methods, and their relationships are shown in the "Class-hierarchy.jpg" image file. The *default package* will contain the Main class and the readInput class. These are the only two classes that interact with both of the other two packages. Main.java will start the animation, and Input.java reads the XML files and relays that information to the other packages.

The *GUI package* contains the classes responsible for presenting the view of the program, which are Animation.java, Menu.java, and MenuItem.java. This package also contains the game loop, which will import data (specifically about the grid of cells) from the *SIMULATION package* and update the view accordingly. This package also presents menu options to the user.

The *SIMULATION package* takes care of updating the state of the cells. Cell.java will get information about the simulation from readInput.java and update the state of the cells according to those rules (and the state of their neighbors).  Grid.java will be a 2D array of Cells, and its updateGrid() method will call the updateState() method of each cell.

User Interface
---------------------
The user interface is depicted in the "User-interface.jpg" image file. The cellular automata simulation will be displayed as a grid in a window. To the right of the grid, a drop down menu will contain a list of simulation specifications that can be selected by the user and will implement the various rules associated with each scenario. A play button will begin the simulation, a pause button will temporarily stop it, a stop button will stop the simulation and clear the grid, and a step button will step through individual frames of the simulation. Finally, there will be a slider or some other control feature for specifying the step speed of the simulation. There will be a verification check to make sure that the imported XML configuration file is properly formatted and readable. If it isn’t, a message will be displayed to the user that an error has occurred and the simulation cannot proceed as expected.

Design Details
---------------------

*default package*

* Main.java
	* The main class will simply handle launching the JavaFX program and calling the Animation class. Our goal is to keep the main class as simple and short as possible.
* ReadInput.java
	* The ReadInput.java class will read in all of the XML files. It will define the number of possible states for a cell and define the rules for how a cell should update its position. It will also have a method that displays error messages if the XML files are invalidly formatted. It is located in the *default package* because it is the only class that interacts with both the *SIMULATION package* and the *GUI package*.

*SIMULATION package*

* Grid.java
	* The Grid.java class will define a two dimensional array of cells. It will have three methods: initializeGrid(), updateGrid(), and resetGrid(). InitializeGrid() will take in a size parameter based on the user input. UpdateGrid() will loop through all cells and call cell.updateState() on each one. ResetGrid() will simply reinitialize the same grid again with the same parameters. This class is intended primarily to store information about the cellular automata 2D grid.
* Cell.java
	* The Cell.java class will have an instance variable which holds all of its possible states. It will also have a boolean value representing whether or not it is an edgeCell, determined upon simulation initialization. It will have instance variables for its generation and for its previous state, and an instance variable that holds the collection of all its neighbors. Each cell will have the ability to update its state, report its current state, report how many neighbors it has, report whether it is an edge cell, and reset itself to its initial state. This class is intended primarily to store information about each cell in the grid.

*GUI package*

* Animation.java
	* The Animation.java class will handle stepping through the automata simulation and displaying the simulation in the user interface. This class will serve as the GUI "engine" of the simulation. It will have a grid variable that specifies the current cell grid. The animation speed variable will specify the speed with which the program steps through each frame. Animation will implement functionality for starting the animation, pausing the animation, and stopping and resetting the entire grid in preparation for a new user specification or simulation. There will be a method that handles specification of the animation speed and stepping through individual frames of the simulation. This class will interact extensively with Grid.java and Cell.java from the *SIMULATION package* to gain the information required to render the cellular automata simulation.
* Menu.java
	* The Menu.java class will have an ArrayList<MenuItem> of all the various menu items it holds. It will also have methods to display the menu, set the current simulation mode, and report what the current simulation mode is. Methods will be included that facilitate stopping the simulation on configuration change and changing specifications to those of the new configuration file.
* MenuItem.java
	* This class will contain information about each possible XML specification, imported via the ReadInput.java class. The information gathered by this class will be used to populate the menu in the MenuItem.java class.


**Use Cases:**
1. To apply the rules to a middle cell, we would call cell.updateState() on the cell, which would loop through all the cells in the current cell's Neighbors field, call each of those cell's getCurrentState() methods, and then determine the new state of the cell based upon those results. 
2. Applying the rules to an edge cell would work in the exact same way as applying rules to a middle cell, however an edge cell would just have a different number of neighbors. This would be determined upon the cell's initialization, where its edgeCell field would be set to true or false depending upon its position in the grid. 
3. To move to the next generation, we would simply call Grid.updateGrid(), which would loop through every cell in the grid, and  call the update cell method for each one. 
4. To set a simulation parameter, the readInput class’ setParameters() method would be invoked. This method would define the conditions in which a cell changes state and how cells interact with their neighbors. 
5. To switch simulations, the Menu object's setSimulationMode() would be invoked, which would be passed over to the Animation.java class to stop the simulation step, clear the grid, and prepare the user interface for the new XML configuration specifications.

Design Considerations
---------------------
The biggest design issue that we must ultimately resolve is how each cell’s state will be updated in regards to its previous state and the state of the cell’s neighbors. We discussed at length which class would handle aggregating all of this data and making a final decision on a cell’s new state. Animation.java could handle updating states, making the implementation simpler since GUI updates would be computed in the same class as the states. However, we decided it would be better design to keep state computations in a separate package with other simulation-related classes, such as Grid.java. This makes implementation more complex but makes more sense design-wise, since we want classes within the *GUI package* to focus specifically on updating the user interface, not performing computation regarding cell state changes, which is the intended function of the *SIMULATION package*.

We also discussed how we would update the state of a cell without affecting how neighbor cells are subsequently calculated. We decided to keep two instance variables in the Cell.java class. *generation* will specify which generation the cell is currently “in” while *previousState* will specify the previous state of the cell. With this implementation, a cell cannot be incorrectly updated based on the state of a neighbor cell that has already been updated to the next state (i.e. the neighbor cell was updated, then the focus cell is updated, but is updated incorrectly because it is looking at the new state of the neighbor cell). With this design choice, the cell cannot be incorrectly updated, but the implementation will be more complex.

Team Responsibilities
---------------------
This is how we plan to divide our work:

* Robert’s primary responsibility will be to implement most components of the user interface (such as Animation.java, Menu.java, MenuItem.java, et cetera).
* Aaron’s primary responsibility will be to implement the readInput.java class. This includes reading in the XML files and creating the rules for each cell. This may also include some work within the Cell.java class.
* Sam’s primary responsibility will be to implement the Grid.java and Cell.java classes that will handle cell state changes.
