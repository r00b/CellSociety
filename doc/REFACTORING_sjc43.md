#Sam Curtis
##sjc43

###Refactoring

	Based on the discussion in class, I did a major refactoring of the simulations package. In my original implementation, grid and cell were concrete classes that were instantiated and used by every class. However, this lead to each simulation class (GameOfLife.java, Segregation.java, etc) to be quite large, and contain a lot of logic that really made more sense in other classes. For example, these classes contained information about the possible states of cells, and all had different ways of adding neighbors for given cells. Thus, I decided to change cell and grid to be abstract classes designed to be superclasses. Then, I made a specific grid and cell class for each type of simulation. I also implemented a Neighborhood class so that each cell had a Neighborhood instance variable instead of a list of its neighbors. This also allows all different methods for adding neighbors to be in the Neighboorhood class, and then each simulation cell can call whichever one of those methods is appropriate. This new code is much easier to understand as the encapsulation makes much more sense. The simulations basically only set the initial state of the grid and then have a method which goes through the grid and update it. They are much smaller classes than before and don't concern themselves with the logic or implementation of cells and neighbors. The following commits were all part of this refactoring process: 

1. https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team08/commit/c5d44d8b444e358fcd137faf3faffcfc05efd495
2. https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team08/commit/e65bc66ae4a9dd344c93546c09d36d3cc99776d3
3. https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team08/commit/469d5a812847045d90ae71da6e2302a35c8ea607
4. https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team08/commit/64ee5f002ace044e5663c4789883f23682334ce8

The commits probably won't make much sense until viewing commit number four. This is because i forgot to track new files that I had created in the earlier commits, and was only pushing files that already existed and I had modified. This is noted in the last commit above. 
