package com.example.jimmyle.pacmanandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class DrawingView extends SurfaceView implements Runnable, SurfaceHolder.Callback {
    private Thread thread;
    private SurfaceHolder holder;
    private boolean canDraw = true;

    private Paint paint;

    private int totalFrame = 4;             // Total amount of frames fo each direction
    private int currentPacmanFrame = 0;     // Current Pacman frame to draw
    private int currentArrowFrame = 0;      // Current arrow frame to draw
    private long frameTicker;               // Current time since last frame has been drawn

    private float x1, x2, y1, y2;           // Initial/Final positions of swipe

    private int screenWidth;                // Width of the phone screen
    private int blockSize;                  // Size of a block on the map
    public static int LONG_PRESS_TIME=750;  // Time in milliseconds
    private int currentScore = 0;           //Current game score
    final Handler handler = new Handler();

    //Added Variables by Cole
    private int numOfPellets = 0;           //Total number of pellets remaining
    private short currentMap[][];           //the current map being played

    //refactor of DrawingView methods into separate objects/classes
    private Movement movement;
    private Pacman pacman;
    private Ghost ghost;
    private BitmapImages bitmap;

    public DrawingView(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        frameTicker = 1000/totalFrame;
        paint = new Paint();
        paint.setColor(Color.WHITE);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;

        //takes screen width of device and creates 17 equal block sizes in length x-dir
        blockSize = screenWidth/17;
        blockSize = (blockSize / 5) * 5;

        currentMap = LevelGenerator.getMap(0);            //gets the test map
        //currentMap = LevelGenerator.getMap(1);          //gets the first level

        movement = new Movement(currentMap, blockSize);   //create a new instance of the movement class

        pacman = movement.getPacman();                    //reference of pacman object
        ghost = movement.getGhost();                      //reference of ghost object

        bitmap = new BitmapImages(blockSize, context);    //loads the bitmap images

        Log.i("info", "Constructor");
    }

    @Override
    public void run() {
        Log.i("info", "Run");
        countPellets();
        while (canDraw) {
            if (!holder.getSurface().isValid()) {
                continue;
            }
            Canvas canvas = holder.lockCanvas();
            // Set background color to Transparent
            if (canvas != null) {
                canvas.drawColor(Color.BLACK);

                drawMap(canvas);

                drawArrowIndicators(canvas);

                updateFrame(System.currentTimeMillis());

                drawGhost(canvas);

                drawPacman(canvas);

                drawPellets(canvas);

                updateScores(canvas);

                holder.unlockCanvasAndPost(canvas);
            }
        }
    }

    public void updateScores(Canvas canvas) {
        paint.setTextSize(blockSize);

        Globals g = Globals.getInstance();
        int highScore = g.getHighScore();
        if (currentScore > highScore) {
            g.setHighScore(currentScore);
        }

        String formattedHighScore = String.format("%05d", highScore);
        String hScore = "High Score : " + formattedHighScore;
        canvas.drawText(hScore, 0, 2*blockSize - 10, paint);

        String formattedScore = String.format("%05d", currentScore);
        String score = "Score : " + formattedScore;
        canvas.drawText(score, 11 * blockSize, 2 * blockSize - 10, paint);
    }

    public void drawGhost(Canvas canvas) {
        //move ghost
        movement.moveGhost();

        //draw ghost
        canvas.drawBitmap(bitmap.getGhostBitmap(), ghost.getXPos(), ghost.getYPos(), paint);

        //check if there is a collision and handle game over
        try{
            movement.checkPlayerDeath();
        } catch (PlayerDeathException e){
            Log.i("info", "Death - GameOver");

            Intent failedIntent = new Intent(getContext(), FailedLevelActivity.class);
            getContext().startActivity(failedIntent);
        }
    }

    private void drawArrowIndicators(Canvas canvas) {
        switch(movement.getPacman().getNextDir()) {
            case(0):
                canvas.drawBitmap(bitmap.getArrowUp()[currentArrowFrame],5*blockSize , 20*blockSize, paint);
                break;
            case(1):
                canvas.drawBitmap(bitmap.getArrowRight()[currentArrowFrame],5*blockSize , 20*blockSize, paint);
                break;
            case(2):
                canvas.drawBitmap(bitmap.getArrowDown()[currentArrowFrame],5*blockSize , 20*blockSize, paint);
                break;
            case(3):
                canvas.drawBitmap(bitmap.getArrowLeft()[currentArrowFrame],5*blockSize , 20*blockSize, paint);
                break;
            default:
                break;
        }

    }

    // This was based on the non-Android Pacman legacy project for CS56
    // Method that draws pacman based on his viewDirection
    public void drawPacman(Canvas canvas) {
        //move pacman
        movement.movePacman();

        //draw pacman
        switch (pacman.getCurDir()) {
            case (0):
                canvas.drawBitmap(bitmap.getPacmanUp()[currentPacmanFrame], pacman.getXPos(), pacman.getYPos(), paint);
                break;
            case (1):
                canvas.drawBitmap(bitmap.getPacmanRight()[currentPacmanFrame], pacman.getXPos(), pacman.getYPos(), paint);
                break;
            case (3):
                canvas.drawBitmap(bitmap.getPacmanLeft()[currentPacmanFrame], pacman.getXPos(), pacman.getYPos(), paint);
                break;
            default:
                canvas.drawBitmap(bitmap.getPacmanDown()[currentPacmanFrame], pacman.getXPos(), pacman.getYPos(), paint);
                break;
        }

        //update pacman
        movement.updatePacman();

        //check for eaten pellet and handle winning game
        if(movement.needMapRefresh()) {
            currentMap = movement.updateMap();
            currentScore+=10;
            numOfPellets--;
            if(numOfPellets == 0){
                Log.i("info", "Level completed - GameOver");
                Intent completedIntent = new Intent(getContext(), CompletedLevelActivity.class);
                getContext().startActivity(completedIntent);
            }
        }

        //check if there is a collision
        try{
            movement.checkPlayerDeath();
        } catch (PlayerDeathException e){
            Log.i("info", "Death - GameOver");

            Intent failedIntent = new Intent(getContext(), FailedLevelActivity.class);
            getContext().startActivity(failedIntent);
        }
    }

    // Method that draws pellets and updates them when eaten
    public void drawPellets(Canvas canvas) {
        float x, y;
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 17; j++) {
                x = j * blockSize;
                y = i * blockSize;
                // Draws pellet in the middle of a block
                if ((currentMap[i][j] & 16) != 0) {
                    canvas.drawCircle(x + blockSize / 2, y + blockSize / 2, blockSize / 10, paint);
                }
            }
        }
    }

    // Method to draw map layout that is based on the non-Android Pacman legacy project for CS56
    public void drawMap(Canvas canvas) {
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2.5f);
        int x, y;

        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 17; j++) {
                x = j * blockSize;
                y = i * blockSize;
                if ((currentMap[i][j] & 1) != 0) // draws left
                    canvas.drawLine(x, y, x, y + blockSize - 1, paint);

                if ((currentMap[i][j] & 2) != 0) // draws top
                    canvas.drawLine(x, y, x + blockSize - 1, y, paint);

                if ((currentMap[i][j] & 4) != 0) // draws right
                    canvas.drawLine(
                            x + blockSize, y, x + blockSize, y + blockSize - 1, paint);
                if ((currentMap[i][j] & 8) != 0) // draws bottom
                    canvas.drawLine(
                            x, y + blockSize, x + blockSize - 1, y + blockSize , paint);
            }
        }
        paint.setColor(Color.WHITE);
    }

    Runnable longPressed = new Runnable() {
        public void run() {
            Log.i("info", "LongPress");
            Intent pauseIntent = new Intent(getContext(), PauseActivity.class);
            getContext().startActivity(pauseIntent);
        }
    };

    // Method to get touch events
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case (MotionEvent.ACTION_DOWN): {
                x1 = event.getX();
                y1 = event.getY();
                handler.postDelayed(longPressed, LONG_PRESS_TIME);
                break;
            }
            case (MotionEvent.ACTION_UP): {
                x2 = event.getX();
                y2 = event.getY();
                calculateSwipeDirection();
                handler.removeCallbacks(longPressed);
                break;
            }
        }
        return true;
    }

    // Calculates which direction the user swipes
    // based on calculating the differences in
    // initial position vs final position of the swipe
    private void calculateSwipeDirection() {
        float xDiff = (x2 - x1);
        float yDiff = (y2 - y1);

        // Directions
        // 0 means going up
        // 1 means going right
        // 2 means going down
        // 3 means going left
        // 4 means stop moving, look at move function

        // Checks which axis has the greater distance
        // in order to see which direction the swipe is
        // going to be (buffering of direction)
        if (Math.abs(yDiff) > Math.abs(xDiff)) {
            if (yDiff < 0) {
                pacman.setNextDir(0);
            } else if (yDiff > 0) {
                pacman.setNextDir(2);
            }
        } else {
            if (xDiff < 0) {
                pacman.setNextDir(3);
            } else if (xDiff > 0) {
                pacman.setNextDir(1);
            }
        }
    }

    // Check to see if we should update the current frame
    // based on time passed so the animation won't be too
    // quick and look bad
    private void updateFrame(long gameTime) {

        // If enough time has passed go to next frame
        if (gameTime > frameTicker + (totalFrame * 30)) {
            frameTicker = gameTime;

            // Increment the frame
            currentPacmanFrame++;
            // Loop back the frame when you have gone through all the frames
            if (currentPacmanFrame >= totalFrame) {
                currentPacmanFrame = 0;
            }
        }
        if (gameTime > frameTicker + (50)) {
            currentArrowFrame++;
            if (currentArrowFrame >= 7) {
                currentArrowFrame = 0;
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i("info", "Surface Created");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i("info", "Surface Changed");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i("info", "Surface Destroyed");
    }

    public void pause() {
        Log.i("info", "pause");
        canDraw = false;
        thread = null;
    }

    public void resume() {
        Log.i("info", "resume");
        if (thread != null) {
            thread.start();
        }
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
            Log.i("info", "resume thread");
        }
        canDraw = true;
    }

    //counts the number of pellets at the start of the game
    private void countPellets() {
        numOfPellets = 0;
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 17; j++) {
                if ((currentMap[i][j] & 16) != 0) {
                    //increases the total number of pellets
                    numOfPellets++;

                    Log.i("info", "Pellets = " + Integer.toString(numOfPellets));
                }
            }
        }
    }
}
