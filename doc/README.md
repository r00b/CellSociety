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
	* My primary role for the project was the entire GUI implementation and how the GUI would implement with the backend (with the exception of the graph, implemented by Aaron). I made design choices regarding the look and feel of the GUI along with the different grid cell shape types, simulation controls, XML error checking, CSS implementations, and interface with the backend. Additionally, I managed all of the event handlers associated with GUI elements that controlled the flow of the program (i.e. through changing simulations, selecting XML files to read in, et cetera) I also wrote the interface along with its implementations for different cell shapes. Importantly, I wrote the GUI such that it could easily interface with any of the CellSociety simulations through simple method calls carried out in each "step" of the program.

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
* For the GUI, I used the foundational JavaFX code given at the beginning of the Game assignment as a guide, but didn't copy any of it directly.
* Planning files located in the /doc/ directory created as part of the Plan phase of the project.

##### Files Used to Test the Project
* None, other than data and resource files listed below.

##### Data and Resource Files required by the project
* XML files (DefaultParameters for the backend Simulation classes, English for the frontend GUI, and XmlTags for XML files) located in the /data/ directory.
* Properties files located in the resources package
* style.css file located in the resources package
* JPG image located in the resources package


##### Using the Program

##### Known bugs

1. Game of Life simulation using hexagonal cells
   * The Game of Life simulation does not execute properly when an xml file specifies that the the number of surrounding neighbors should be 6 (i.e. using hexagonal cells). There are no errors reported, but all of the cells die off very quickly because the logic was not changed to reduce the requirements for life given the decrease in neighbors. 
2. Foraging Ants return-to-nest bug
  * The Foraging Ants simulation seems to fail at simulating the ants developing an efficient path from the food source back to their nest. It appears that as the simulation progresses, the ants do find the food more efficiently, but they do not seem to find their way back to their nest any more efficiently. However, I did not have enough time to test to what degree this is the case (Sam Curtis). 
  

##### Extra features

##### Impressions 

1. Sam Curtis
	* I really enjoyed working on the project. In future years, I would stress from the beginning the amount of flexibility that groups are allowed in deciding how to implement any sort of simulations.
2. Robert Steilberg
	* f
    
