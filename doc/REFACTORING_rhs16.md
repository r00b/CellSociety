Refactoring Discussion
=======================

Robert H. Steilberg II | rhs16

The refactoring changes documented in this file are reflected in this [commit](https://git.cs.duke.edu/CompSci308_2016Fall/cellsociety_team08/commit/4b951896166d498ee277c2465d2cdc4cc6cd05b3).

* I decided to refactor the for loop in the Animation class since it was duplicated between drawNewGrid and updateGrid, as shown by the results from CodePro AnalytiX. I did this by adding a boolean to distinguish whether or not the drawGrid function was drawing the grid for the first time or merely updating the grid (requiring that the previous grid be cleared of the canvas before the updated grid is drawn).

* I incorporated any stylistic choices into a CSS file, style.css, rather than hard-coding CSS styles within classes.

* I created a new class, GridParser.java, which handles all the drawing functions associated with the grid object passed through each simulation class during each step. All functionality associated with clearing the grid, drawing it for the first time, and updating it has been moved to this class. Now, Animation.java focuses solely on functionality associated with initializing and stepping through each simulation.

* Animation.java's subclasses are no longer set to extend Animation.java, which was previously the case to enable easy access to Animation.java's instance variables.

* I changed my protected instance variables to private and created constructors for each of my subclasses to get any variables that I needed from the superclass, such as resource files, the simulation scene, et cetera. However, I made the design choice to make protected the ComboBox instance variable in Animation.java. I did this because, after creating and setting an event handler for the ComboBox, the Animation class needs access to the current value of the ComboBox when the ComboBox is changed by the user to set a new simulation. The only way to do this is to instantiate the ComboBox in the SimControls subclass, which requires that Animation.java's myComboBox not be private.

* I created a new class, SimEvents,java, which defines all of the methods that are set to GUI elements by event handlers in the SimControls class. These events determine the overall control flow of the program and are easily modifiable.

This new version of the GUI package is much improved because it removes duplicate code, magic variables, and makes clearer the style with which classes inherit from each other. Now, the GUI component of the simulation can be more easily modified by an end-user, through manipulation of the properties file or other changes within the code. Subclasses can be changed and extended without jeopardizing the functionality of the overall superclass Animation.java. New actions can be created (or current events modified) within SimEvents that are triggered by event handlers set in SimControls. Furthermore, it is now much easier to understand the flow of the program and how each component interacts to create the overall GUI.
