Refactoring Discussion
=======================

rhs16 | amc120 | sjc43

* We decided to refactor the for loop in the Animation class since it was duplicated between drawNewGrid and updateGrid, as shown by the results from CodePro AnalytiX. We did this by adding a boolean to distinguish whether or not the drawGrid function was drawing the grid for the first time or merely updating the grid (requiring that the previous grid be cleared of the canvas before the updated grid is drawn). 