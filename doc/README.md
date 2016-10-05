# CellSociety 
### Aaron Chang (amc120), Sam Curtis (sjc23), Robert Steilberg (rhs16)

##### Project Dates/Times

1. Start Date: 09/16/16
2. End Date: 10/2/16
3. Estimate of Hours Worked: ~60

##### Roles

1. Sam Curtis: Implement Backend Simulation Logic
   * This was my primary role throughout the entire project. I was responsible for writing the code in the simulations package corresponding  to the Game Of Life Simulation, the Fire simulation,  and the Foraging Ants simulation. I also wrote the code for the superclasses that all simulations inherited or used. I also refactored the code for the Segregation and Wator simulations (originally written by Aaron Chang), to conform with the inheritance hierarchy I had created for the other simulations. I also was responsible for communicating with Robert and Aaron about how the GUI and XML reading components of the program would interact with the simulations logic. 
2. Robert Steilberg: Implement frontend GUI
	* My primary role for the project was the entire GUI implementation and how the GUI would interface with the backend (with the exception of the graph, implemented by Aaron). I made design choices regarding the look and feel of the GUI along with the different grid cell shape types, simulation controls, XML error checking, CSS implementations, and interface with the backend. Additionally, I managed all of the event handlers associated with GUI elements that controlled the flow of the program (i.e. through changing simulations, selecting XML files to read in, et cetera) I also implemented the front-end for different cell shapes. Importantly, I wrote the GUI such that it could easily interface with any of the CellSociety simulations through simple method calls carried out in each "step" of the program.
3. Aarong Chang:
	* 


##### Resources Used 
We use the following resources in the completion of this assignment:

1. [Segregation description](http://nifty.stanford.edu/2014/mccown-schelling-model-segregation/)
2. [predator-prey description](http://nifty.stanford.edu/2011/scott-wator-world/)
3. [fire description](http://nifty.stanford.edu/2007/shiflet-fire/)
4. [game of life description](http://en.wikipedia.org/wiki/Conway's_Game_of_Life)
5. [Foraging Ants Description](http://cs.gmu.edu/~eclab/projects/mason/publications/alife04ant.pdf)
6. [JavaFX Polygon](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/Polygon.html)
7. [JavaFX CSS integration](https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html)
8. [JavaFX Filechooser](http://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm)
9. [JavaFX Slider](http://docs.oracle.com/javafx/2/ui_controls/slider.htm)
10. [Properties and Binding](http://docs.oracle.com/javafx/2/binding/jfxpub-binding.htm)

##### Files Used to Start the Project
* For the GUI, we used the foundational JavaFX code given at the beginning of the Game assignment as a guide, but didn't copy any of it directly.
* Planning files located in the /doc/ directory created as part of the Plan phase of the project.

##### Files Used to Test the Project
* None, other than data and resource files listed below.

##### Data and Resource Files required by the project
* XML files (DefaultParameters for the backend Simulation classes, English for the frontend GUI, and XmlTags for XML files) located in the /data/ directory.
* Properties files located in the resources package.
* style.css file located in the resources package.
* JPG background image located in the resources package.


##### Program Use
The simulation initializes with the default simulation defined in the English.properties properties file. The user can change the simulation with the combo box containing each type of simulation. Upon choosing a simulation, the user is prompted to choose a corresponding XML file to initialize the parameters for that simulation. The "Play" button plays the simulation, the "Pause" button pauses it, the "Step" button steps through it, the "Stop & Reset" button resets the simulations to the initial parameters, and the "Change XML File" allows the user to change the initial parameters for the simulation type. The slider can be used to control the speed at which the simulation steps through each frame. The graph displays information about the number of cells of each state in every frame.

##### Known bugs

1. Game of Life simulation using hexagonal cells
	* The Game of Life simulation does not execute properly when an XML file specifies that the the number of surrounding neighbors should be 6 (i.e. using hexagonal cells). There are no errors reported, but all of the cells die off very quickly because the logic was not changed to reduce the requirements for life given the decrease in neighbors. 
2. Foraging Ants return-to-nest bug
	* The Foraging Ants simulation seems to fail at simulating the ants developing an efficient path from the food source back to their nest. It appears that as the simulation progresses, the ants do find the food more efficiently, but they do not seem to find their way back to their nest any more efficiently. However, I did not have enough time to test to what degree this is the case (Sam Curtis). 
3. Lag
	* Rendering the graph in the simulation *sometimes* causes for the simulation to increasingly lag as time progresses. This lag, we think, is a product of both large grid dimensions and the JavaFX Graph object. The lag is not present when the graph is not being drawn. We weren't exactly sure how to fix the lag issue without removing the graph.
  

##### Extra features
* We implemented different grid cell shapes, specified in the XML file. Grids can be made up of squares, triangles, or hexagons, each of which is associated with different neighbor settings (i.e. square and triangle have 8 neighbors, hexagon has 6)
* We allow for a variety of grid edge types, specified in the XML file. Possible grid edge types include finite and toroidal grid edges.
* We implemented an additional simulation, Foraging Ants.
* We implemented error checking with the XML in two forms. A corrupted XML that cannot be read at all throws a GUI error to the user that prompts the user to load in a different XML. An XML with incorrect specifications (i.e. percentages that don't add up to 100, invalid cell states, et cetera) are changed to default values to allow the simulation to initialize.
* We implemented a graph that displays the populations of each cell state. The graph updates in real-time as the simulation progresses.
* We added a button that allows the user to change the XML file.
* We added a file browser that allows the user to easily view and choose a desired XML file. The file browser automatically opens up to the directory containing the relevant XML files (i.e. when the current simulation is Game of Life, the file browser will only show Game of Life XML files).
* We added CSS styling to improve the look and feel of the GUI.

##### Impressions 

1. Sam Curtis
	* I really enjoyed working on the project. In future years, I would stress from the beginning the amount of flexibility that groups are allowed in deciding how to implement any sort of simulations.
2. Robert Steilberg
	* I found this project to be more challenging than I expected. It was certainly the most complex and involved Java project that I have ever worked on. Ideally, initial planning before the project began could have been better, so that our code base would be better designed. However, I think our lack in initial planning is more due to our prior inexperience with Java projects as complex as this.