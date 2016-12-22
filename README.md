# CellSociety

### Robert Steilberg, Aaron Chang, Sam Curtis

#### Overview

CellSociety is a cellular automata simulation engine coded in Java using the JavaFX framework. I developed this application with two other team members. For this project, I served as the front end developer, and was in charge of interfacing the front end GUI with the program's back end logic that would power each simulation.

##### Instructions
The simulation initializes with the default simulation defined in the English.properties properties file. The user can change the simulation with the combo box containing each type of simulation. Upon choosing a simulation, the user is prompted to choose a corresponding XML file to initialize the parameters for that simulation. The "Play" button plays the simulation, the "Pause" button pauses it, the "Step" button steps through it, the "Stop & Reset" button resets the simulations to the initial parameters, and the "Change XML File" allows the user to change the initial parameters for the simulation type. The slider can be used to control the speed at which the simulation steps through each frame. The graph displays information about the number of cells of each state in every time frame of the simulation.

##### Roles

1. Sam Curtis: Implement Backend simulation logic
* Sam was responsible for writing the code in the simulations package corresponding to the Game Of Life Simulation, the Fire simulation, and the Foraging Ants simulation. Sam also wrote the code for the superclasses that all simulations inherited or used. Sam refactored the code for the Segregation and Wa-tor simulations (originally written by Aaron Chang), to conform with the inheritance hierarchy I had created for the other simulations. Sam also was responsible for communicating with Aaron and me about how the GUI and XML reading components of the program would interact with the simulation's logic.
2. Robert Steilberg: Front end GUI
* My primary role for the project was the entire GUI implementation and how the GUI would interface with the backend (with the exception of the graph, implemented by Aaron). I made design choices regarding the look and feel of the GUI along with the different grid cell shape types, simulation controls, XML error checking, CSS implementations, and their connection with the back end. Additionally, I managed all of the event handlers associated with GUI elements that controlled the flow of the program (i.e. through changing simulations, selecting XML files to read in, et cetera). I also implemented the front-end for different cell shapes. Importantly, I wrote the GUI such that it could easily interface with any of the CellSociety simulations through simple method calls carried through each frame step of the program.
3. Aaron Chang: Implement XML package, some simulations, and graph
* Aaron's primary role on this project was implementing the XML package. For the XML package, he communicated closely with Sam and me to determine what information (and in what form) they needed from the XML files. I was responsible for writing all the XML files and XMLParsers. I also implemented Segregation (which Sam debugged) and Wator. In the last sprint, I added error checking to the XML package and implemented the graph.

##### Design
Our project is divided into 4 super-packages: the *default package*, the *gui package*, the *simulation package*, and the *xml package*. There is also a *resource package* containing our CSS and properties files. Furthermore, there are multiple sub-packages. The *gui package* includes a sub-package *gui.CellShapes* that involves defining different grid cell shapes. The *simulation package* has a sub-package for each simulation type (i.e. *simulations.GameOfLife*) These packages, their classes and methods, and their relationships are shown in the "Class-hierarchy.jpg" image file, created during the planning phase of this project (but slightly outdated now in post-production). The *default package* will contain only the Main class that starts the application.

The *GUI package* contains the classes responsible for implementing the view of the program. This package also contains the game loop/step function, which will import data (specifically about the grid of cells) from the *simulation package* and update the view accordingly. This package also presents menu options and the cell state graph to the user.

The *simulation package* handles updating the state of the cells. The main simulation class (i.e. GameOfLife.java) will get information about the simulation parameters from its associated XMLParser class (i.e. GameOfLifeXMLParser.java) and update the initial states of the cells according to those rules. There will also be an associated cell class (i.e. GameOfLifeCell.java) and grid class (i.e. GameOfLifeGrid.java) that handles creating each cell and the overall grid for each simulation step.  Grid.java implements a 2D array of Cells, and its updateGrid() method will call the updateState() method of each cell.

The *xml package* handles reading the XML files and providing this information to the GUI and simulation classes. We wanted the simulation packages to do as little work as possible, so we made specialized xml objects for each simulation type.  These xml objects have methods to return specific simulation parameters to each simulation. It also implements error checking for bad xml files/input values. It also has a robust set of default values, so that errors in input values can be handled internally as much as possible. The GUI package uses a generic XMLParser object to check if an error message must be displayed to the user.

##### Design details

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

*xml package*

* XMLParser.java
	* This is the superclass for all XMLParser objects. It handles all generic behavior for XMLParsers such as getting the Root Element for the file (to parse it). It also has methods to get value from the xml file from a given tagName. It has separate methods to return the value in the form of an Integer, String, or boolean. All of the subclasses uses these three methods to get values from specific tag names.


##### Resources Used
We use the following resources in the completion of this assignment:

1. [Segregation simulation](http://nifty.stanford.edu/2014/mccown-schelling-model-segregation/)
2. [Predator-prey simulation](http://nifty.stanford.edu/2011/scott-wator-world/)
3. [Fire simulation](http://nifty.stanford.edu/2007/shiflet-fire/)
4. [Game of Life simulation](http://en.wikipedia.org/wiki/Conway's_Game_of_Life)
5. [Foraging Ants simulation](http://cs.gmu.edu/~eclab/projects/mason/publications/alife04ant.pdf)
6. [JavaFX Polygon](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/Polygon.html)
7. [JavaFX CSS integration](https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html)
8. [JavaFX Filechooser](http://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm)
9. [JavaFX Slider](http://docs.oracle.com/javafx/2/ui_controls/slider.htm)
10. [Properties and Binding](http://docs.oracle.com/javafx/2/binding/jfxpub-binding.htm)
11. [JavaFX LineChart](https://docs.oracle.com/javafx/2/charts/line-chart.htm#CIHGBCFI)
12. [JavaFX XMLParser](https://git.cs.duke.edu/CompSci308_2016Fall/example_xml)

##### Known bugs

1. Game of Life simulation using hexagonal cells
	* The Game of Life simulation does not execute properly when an XML file specifies that the the number of surrounding neighbors should be 6 (i.e. using hexagonal cells). There are no errors reported, but all of the cells die off very quickly because the logic was not changed to reduce the requirements for life given the decrease in neighbors.
2. Foraging Ants return-to-nest bug
	* The Foraging Ants simulation seems to fail at simulating the ants developing an efficient path from the food source back to their nest. It appears that as the simulation progresses, the ants do find the food more efficiently, but they do not seem to find their way back to their nest any more efficiently. However, I did not have enough time to test to what degree this is the case (Sam Curtis).
3. Lag
	* Rendering the graph in the simulation *sometimes* causes for the simulation to increasingly lag as time progresses. This lag, we think, is a product of both large grid dimensions and the JavaFX Graph object. The lag is not present when the graph is not being drawn. We weren't exactly sure how to fix the lag issue without removing the graph.
4. Percentage of cells in XML files
	* The XML package implements error checking for bad input values, however, it does not account for the case in which the percentages of cells in the XML files adds up to more than 100.  This will have a minimal effect in most cases, but can sometimes result in a lack of empty cells.
