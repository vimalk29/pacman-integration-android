# PacmanAndroid
A mobile version of the of Pacman game on Android

Authors: Kevin Lee, Jimmy Le

## Running the game
1. Load the project on Android Studio
2. Connect your device to your computer using a usb cable
3. Press "Run" (Shift + F10 on Windows)
4. Choose the device you wish to run it on such as your phone or the Android Studio emulator
5. The game should run on your device

## Current progress
* Title screen
* Three buttons on the screen (New Game, High Scores, and Help)
* New Game button takes you to a screen with the map layout and pacman
* Help button leads to the help screen
* You can move pacman around by swiping in the direction you wish to go
* Pacman will eat the pellets

## User Stories (Implementation Tasks/Goals):
1. ~~As a user, I can see the introduction screen when I open the app so that I can know it has sucessfully opened.~~ (100pts)
2. ~~As a user, I can see a play button and a help button and a high score button so that I can click on them.~~ (50pts)
3. ~~As a user, I can click on the help button so that I can know how to play.~~ (50pts)
4. ~~As a user, I can click on the play button so that I can start the actual game.~~ (50pts)
5. ~~As a user, I can see the game board so that I know the game is about to start.~~ (150pts)
6. ~~As a user, I can see my character on the gameboard so that I know where I start.~~ (50pts)
7. ~~As a user, I can move my character so that I see myself move across the game board.~~ (250pts)
  * ~~The character should animate.~~ (50 pts)
  * ~~The character should be able to move up down, left and right.~~(25 pts)
  * ~~The character should move based on user input (swipe gestures).~~ (75 pts)
  * ~~The character stops moving when it reaches a wall.~~ (100 pts)
8. ~~As a user, I can see the pellets on the board so I know where to move.~~ (50pts)
9. ~~As a user, I can see my score to see how well I've played.~~ (50pts)
10. ~~As a user, I can move my character to eat pellets so that I can get points.~~ (150pts)
 * ~~The pellet should dissapear when the character moves over it.~~ (100 pts)
 * ~~The score should increase when the pellets get eaten.~~ (50 pts)
11. ~~As a user, I can pause the game so that I can continue later on.~~ (50pts)
12. ~~As a user, I can unpause/resume the game so that I can continue where I left off.~~ (50pts)
13. ~~As a user, I can see enemy ghosts on the board so that I know where they spawn.~~ (50pts)
14. As a user, I can see enemy ghosts move so that I know where NOT to move. (250pts)
 * ~~The ghosts should animate.~~ (50 pts)
 * The ghosts should move according to unique personalities/behaviors. (200 pts)
   * [Ghost AI Movement](http://gameinternals.com/post/2072558330/understanding-pac-man-ghost-behavior)
15. ~~As a user, I can keep track of my highest score so I can strive to beat it.~~ (50pts)
16. ~~As a user, I can hear Pacman background music so I can get HYPED.~~ (150pts)
 * ~~The music shoud start when the user opens the app.~~ (25 pts)
 * ~~The music should stop when the app is not in focus.~~ (50 pts)
 * ~~The music should be shared across all screens so there is no overlap.~~ (75 pts)
17. As a user, I can see a mute button so that I have the ability to mute the music. (50pts)
18. As a user, I can click on the mute button to mute the music. (100 pts) 
19. As a user, I can set different difficulties so that I can make it easier or harder for myself. (200pts)
20. As a user, I can change the colors of the game so I can set things to my color preference. (100pts)

##W16 Final Remarks##
For future students, If you have no prior experience in android programming then it is highly recommended you at least go through the android tutorial [here](https://github.com/UCSB-CS56-Projects/cs56-android-getting-started). There are a couple of things that you should also be very familiar with which include: [Drawing/Animation](http://developer.android.com/guide/topics/graphics/2d-graphics.html), [Activities](http://developer.android.com/guide/components/activities.html), [Intents](http://developer.android.com/reference/android/content/Intent.html), [Touch Events](http://developer.android.com/reference/android/view/MotionEvent.html), [Bitmaps](http://developer.android.com/training/displaying-bitmaps/index.html), [Supporting multiple screens](http://developer.android.com/guide/practices/screens_support.html).

Next you are encouraged to look through DrawingView.java first as this is where the bulk of the code is including the animation, wall collision, points, etc. It may seem very overwhelming at first, and it is, but smart students like yourselves will understand it in no time. Right now the code is pretty messy especially in the DrawingView.java because we haven't refactored the code out to different classes for specific things like pacman or ghosts. That could possibly be one of the things you guys can do for points. 

Right now the core of the game is mostly done. The things that are currently working are the Main Menu Screen, Help Screen, Music, Pause/Resume, pacman animation using swipes, eating pellets, score system. The things that you guys should probably fix are the ghost AI (it kind of works but it has alot of bugs), making it so that when pacman and the ghosts touch each other pacman dies, centering the map without messing up the wall collisions (this may seem easy and even trivial however, keep in mind that since this is on android, the game will have to support multiple screen sizes and dimensions of a huge variety and this causes complicaitons with the wall collision), and make it so that when you finish eating all the pellets you go to the next level. By the way there should be a java file called BackgroundMusicService.java and note this is NOT the way music was implemented in the program, the way music was implemented in the program is using a static [mediaplayer](http://developer.android.com/guide/topics/media/mediaplayer.html) that could be accessed across all activities of the program. The way BackgroundMusicService implemented music is through [services](http://developer.android.com/guide/components/services.html) which you may choose to do if you like.

New features that you may like to add include settings that allow the user to change the difficulty, color of pacman/ghost, and allow users to choose their own music. Also I highly recommend that you guys add a mute button for the music because the music gets pretty annoying after a while (Note: it will get stuck in your head after you hear it for even a little bit). Perhaps you can also add another game mode and try to make it adhere to Google's [material design standard](https://www.google.com/design/spec/material-design/introduction.html#) if you have time. Well that's about it, I know this is a lot of information especially if you're new to android but trust me, it gets better and it's so much more fun and exciting once you get the hang of it. There is alot of good documentation about most of the things you need to know about android and Google is your friend.

##M16 Final Remarks##
-Kevin Chan and Cole Rogers

## Project History
```
W16 | Kevin Lee, Jimmy Le | CS56 Conrad | 5PM Section
```
