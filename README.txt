=======================================================================
=                                                                     =
=                                                                     =
=   Game Maker - A Interface to make your own game                    =
=                                                                     =
=                                                                     =
=                                                                     =
=                                                                     =
=                                                                     =
=                                                                     =
=                                                                     =
=======================================================================


What is it?
-----------
This is a project built as a part of course work for Object Oriented Software Development. It allows naive users to create games such as Pacman, Breakout, Frogger, etc.

A simple demo can be viewed at https://www.youtube.com/watch?v=JSWU8H8nCLc


Documentation
-------------

Brief of Different Modules
++++++++++++++++++++++++++++

i) com.gamemaker.action
This package contains all the actions that a sprite can perform. By convention, each action is named as WHAT_DOES_IT_DO_Action. Each action is inherited from com.gamemaker.action.Action which includes all necessary boilerplate code for each action. 

ii) com.gamemaker.models 
This package contains mainly the game model which represents the game in terms of list of sprites, properties of each sprite, supported event and actions, etc. 

iii) com.gamemaker.controllers 
This is heavy package which contains both DisplayController and MakerController. 

iv) com.gamemaker.controllers.PlayController 
PlayController works with GamePlayView to support game play. The controller is responsible for triggering events at specified point of time in the game play. 

v) com.gamemaker.controllers.MakerController 
MakerController works with GameMakerView to support game making activity. The controller is responsible for loading and saving the game into supported data backend. Currently, we support saving of game information into relation database.

vi) com.gamemaker.views 
This package contains all views associated with the product. There are mainly three views; StartView which lets user either to start building a new or existing game or play already saved game, GameMakerView which lets user to build a new or existing game, GamePlayView lets user to play a 
game.
