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
While playing the game there should be a button on the bottom or sides that allows the game to pause and resume as the user wishes.
The Settings button should be implemented and display different options in the game such as more ghosts, higher difficulty, and pause the background music.
If the user surpases a previous high score then the game should make a noise or flash the text of the high score to give indication of a new high score.



I. Most of the current code is seperated into classes that serve one purpose and are name accordingly. There is a class called DrawingView that defentialy needs to be refractored and most of the game is inside that class. DrawingView could be seperated into many different subclasses such as a Pacman classes which contains multiple subclasses for movement and character actions. The same could be said about the ghost character and how it deserves a seperate class and its own subclasses. There could also be classes that handle the scoring of the game and the transition into a new level if that feature is ever implemented.

J. The current code does not make use of Test cases and that could be becuase it is programmed in Android.