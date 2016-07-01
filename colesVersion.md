A-D

A. The project is an Android application that runs a Pacman game on mobile devices.

B. As of now the user swipes in the direction that they want Pacman to go anywhere on the screen and Pacman will move in that direction.
There are pellets layed around the map of the game and each time Pacman moves over one the score increases by 10 points and the pellet disappears from the screen.
There is a ghost that follows the user around trying to touch him and cause a death, however, the death implementation is missing.
There is also a high score counter that keeps track of the highest score achived by the user.
There is a title screen that shows up when the application is first started and if the user presses the back button from the game.
Three buttons are attached the the title screen: New Game, How To Play, and Settings. As of now only the New Game and How To Play buttons are functional.

C. After installing the Android Studio IDE and downloading the correct SDK updates the application was able to be launched and played. You can play the game and collect the pellets in order to get a high score but if you are touched by the ghost no action will happen to you and you will be allowed to play an endless game.

D. If the ghost is able to touch the player then the game should display a message to the user stating that they have died and the game should reset to the titile screen.
While playing the game the user can long press on the screen in order to pause the game, a improved version of this would be to add a pause button. This would take care of a possible bug if the user does a long directional swipe which would indicate a movement instead of a game pause.
The Settings button should be implemented and display different options in the game such as more ghosts, higher difficulty, and pause the background music.
If the user surpases a previous high score then the game should make a noise or flash the text of the high score to give indication of a new high score.



I. Most of the current code is seperated into classes that serve one purpose and are name accordingly. There is a class called DrawingView that defentialy needs to be refractored and most of the game is inside that class. DrawingView could be seperated into many different subclasses such as a Pacman classes which contains multiple subclasses for movement and character actions. The same could be said about the ghost character and how it deserves a seperate class and its own subclasses. There could also be classes that handle the scoring of the game and the transition into a new level if that feature is ever implemented.

J. The current code does not make use of Test cases written by the previous students. Test cases could be used to test different features of the code especially after a refractor of the DrawingView class. Some examples could be testing if the game resets to the title screen after a player is touched by a ghost, testing to see if the pause option successfully saves the current state of the game, and testing to check if the high score text changes after a player surpases a previous high score.


E. The current Readme provides a brief discription of the project and the authors of the game. There is a section about how to run the current code using android studio on  either an emulator or on an android device. The previous students provide the current features of the game as well as the implemented and remaining User Stories. Lastly they provide a final remarks section where they explain areas to improve and possible features to implement in the game as well as a project history.

F. Since this is an android project there is not an included build.xml file and 
G.
  Current Issues

  add more levels - 200 per level
  settings page - 200
  indication of winning a level - 200
  pacman and ghost collision - 150
  fix ghost ai - 400
  center game map - 250

  total w/ 1 level added - 1400
H.


Errors:
					      I/Choreographer: Skipped 35 frames! The application my be doing too much work on its main thread.
					      I/Choreographer: Skipped 72 frames! ""
(clicking how to play button)com.example.jimmyle.pacmanandroid E/Surface: getslotFromBufferLocked: unknown buffer: 0xae433c30

(clicking new game button) skipped 71 frames! ""
	      	   	   getSlotFromBufferLocked ""
