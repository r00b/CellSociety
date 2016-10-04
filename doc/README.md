# cellsociety 
### Aaron Chang, Sam Curtis, Robert Steilberg

##### Project Dates/Times

1. Start Date: 09/16/16
2. End Date: 10/2/16
3. Estimate of Hours Worked: 60

##### Roles

1. Sam Curtis: Implement Backend Simulation Logic
   * This was my primary role throughout the entire project. I was responsible for writing the code in the simulations package corresponding  to the Game Of Life Simulation, the Fire simulation,  and the Foraging Ants simulation. I also wrote the code for the superclasses that all simulations inherited or used. I also refactored the code for the Segregation and Wator simulations (originally written by Aaron Chang), to conform with the inheritance hierarchy I had created for the other simulations. I also was responsible for communicating with Robert and Aaron about how the GUI and XML reading components of the program would interact with the simulations logic. 


##### Resources Used 

1. [Segregation description](http://nifty.stanford.edu/2014/mccown-schelling-model-segregation/)
2. [predator-prey description](http://nifty.stanford.edu/2011/scott-wator-world/)
3. [fire description](http://nifty.stanford.edu/2007/shiflet-fire/)
4. [game of life description](http://en.wikipedia.org/wiki/Conway's_Game_of_Life)
5. [Foraging Ants Description](http://cs.gmu.edu/~eclab/projects/mason/publications/alife04ant.pdf)

##### Files Used to Start the Project


##### Files Used to Test the Project

##### Data and Resource Files required by the project

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
    
