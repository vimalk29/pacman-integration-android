package com.example.jimmyle.pacmanandroid;

/**
 * Created by colerogers on 7/25/16.
 */
public class Movement {
    //private instance vars
    private Pacman pacman;

    private Ghost ghost;

    private int blockSize;
    private short [][] currentMap;
    private int swipeDir;
    private boolean pelletEaten;

    public Movement(final short [][] curMap, final int blockSize){
        currentMap = curMap;
        this.blockSize = blockSize;
        pacman = new Pacman(blockSize);
        ghost = new Ghost(blockSize);

        swipeDir = 4;
        pelletEaten = false;
    }

    //public getters
    public Pacman getPacman(){ return pacman; }
    public Ghost getGhost(){ return ghost; }
    public int getSwipeDir(){ return swipeDir; }


    //checks to see if game over happens
    public void checkPlayerDeath() throws PlayerDeathException{
        if(((ghost.getXPos()/blockSize) == (pacman.getXPos()/blockSize))&&((ghost.getYPos()/blockSize) == (pacman.getYPos()/blockSize))){
            throw new PlayerDeathException("Pacman and Ghost collision");
        }
    }

    //METHODS FOR PELLET REMOVAL
    //returns the updated map with the pellet removed
    public short[][] updateMap(){
        pelletEaten = false;
        return currentMap;
    }
    //to see if we need to update the map if a pellet was removed
    public boolean needMapRefresh(){ return pelletEaten; }
    //private method to change the map when the pellet is eaten
    private void pelletWasEaten(int x, int y, short value){
        currentMap[x][y] = value;
        pelletEaten = true;
    }

    //move pacman
    public void movePacman(){
        short ch;
        int nextDirection = pacman.getNextDir();
        int xPosPacman = pacman.getXPos();
        int yPosPacman = pacman.getYPos();

        // This was based on the non-Android Pacman legacy project for CS56
        // Check if xPos and yPos of pacman is both a multiple of block size
        if ( (xPosPacman % blockSize == 0) && (yPosPacman  % blockSize == 0) ) {

            // When pacman goes through tunnel on
            // the right reappear at left tunnel
            if (xPosPacman >= blockSize * 17) {
                xPosPacman = 0;
                pacman.setXPos(0);
            }

            // Is used to find the number in the level array in order to
            // check wall placement, pellet placement, and candy placement
            ch = currentMap[yPosPacman / blockSize][xPosPacman / blockSize];

            // If there is a pellet, eat it
            if ((ch & 16) != 0) {

                // Toggle pellet so it won't be drawn anymore
                pelletWasEaten(yPosPacman / blockSize, xPosPacman / blockSize, (short) (ch ^ 16));
            }

            // Checks for direction buffering
            if (!((nextDirection == 3 && (ch & 1) != 0) ||
                    (nextDirection == 1 && (ch & 4) != 0) ||
                    (nextDirection == 0 && (ch & 2) != 0) ||
                    (nextDirection == 2 && (ch & 8) != 0))) {
                pacman.setCurDir(nextDirection);
                swipeDir = nextDirection;
            }

            // Checks for wall collisions
            if ((swipeDir == 3 && (ch & 1) != 0) ||
                    (swipeDir == 1 && (ch & 4) != 0) ||
                    (swipeDir == 0 && (ch & 2) != 0) ||
                    (swipeDir == 2 && (ch & 8) != 0)) {
                swipeDir = 4;
            }
        }

        // When pacman goes through tunnel on
        // the left reappear at right tunnel
        if (xPosPacman < 0) {
            xPosPacman = blockSize * 17;
            pacman.setXPos(blockSize * 17);
        }
    }

    //call method after we have moved and drawn pacman
    public void updatePacman(){
        // Depending on the direction move the position of pacman
        if (swipeDir == 0) {
            pacman.setYPos(pacman.getYPos() + -blockSize/15);
        } else if (swipeDir == 1) {
            pacman.setXPos(pacman.getXPos() + blockSize/15);
        } else if (swipeDir == 2) {
            pacman.setYPos(pacman.getYPos() + blockSize/15);
        } else if (swipeDir == 3) {
            pacman.setXPos(pacman.getXPos() + -blockSize/15);
        }
    }


    //move ghost
    public void moveGhost() {
        short ch;
        int ghostDir = ghost.getDir();
        int xPosGhost = ghost.getXPos();
        int yPosGhost = ghost.getYPos();
        int xDis = pacman.getXPos() - xPosGhost;
        int yDis = pacman.getYPos() - yPosGhost;


        if ((xPosGhost % blockSize == 0) && (yPosGhost % blockSize == 0)) {
            ch = currentMap[yPosGhost / blockSize][xPosGhost / blockSize];

            if (xPosGhost >= blockSize * 17) {
                xPosGhost = 0;
                ghost.setXPos(0);
            }
            if (xPosGhost < 0) {
                xPosGhost = blockSize * 17;
                ghost.setXPos(blockSize * 17);
            }


            // Move ghost right and down
            if (xDis >= 0 && yDis >= 0) {
                if ((ch & 4) == 0 && (ch & 8) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        ghostDir = 1;
                        ghost.setDir(1);
                    } else {
                        ghostDir = 2;
                        ghost.setDir(2);
                    }
                } else if ((ch & 4) == 0) {
                    ghostDir = 1;
                    ghost.setDir(1);
                } else if ((ch & 8) == 0) {
                    ghostDir = 2;
                    ghost.setDir(2);
                } else {
                    ghostDir = 3;
                    ghost.setDir(3);
                }
            }


            // Move ghost right and up
            if (xDis >= 0 && yDis <= 0) {
                if (xDis >= 0 && yDis <= 0) {
                    if ((ch & 4) == 0 && (ch & 2) == 0) {
                        if (Math.abs(xDis) > Math.abs(yDis)) {
                            ghostDir = 1;
                            ghost.setDir(1);
                        } else {
                            ghostDir = 0;
                            ghost.setDir(0);
                        }
                    } else if ((ch & 4) == 0) {
                        ghostDir = 1;
                        ghost.setDir(1);
                    } else if ((ch & 2) == 0) {
                        ghostDir = 0;
                        ghost.setDir(0);
                    } else {
                        ghostDir = 2;
                        ghost.setDir(2);
                    }
                }
            }


            // Move ghost left and down
            if (xDis <= 0 && yDis >= 0) {
                if ((ch & 1) == 0 && (ch & 8) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        ghostDir = 3;
                        ghost.setDir(3);
                    } else {
                        ghostDir = 2;
                        ghost.setDir(2);
                    }
                } else if ((ch & 1) == 0) {
                    ghostDir = 3;
                    ghost.setDir(3);
                } else if ((ch & 8) == 0) {
                    ghostDir = 2;
                    ghost.setDir(2);
                } else {
                    ghostDir = 1;
                    ghost.setDir(1);
                }
            }


            // Move ghost left and up
            if (xDis <= 0 && yDis <= 0) {
                if ((ch & 1) == 0 && (ch & 2) == 0) {
                    if (Math.abs(xDis) > Math.abs(yDis)) {
                        ghostDir = 3;
                        ghost.setDir(3);
                    } else {
                        ghostDir = 0;
                        ghost.setDir(0);
                    }
                } else if ((ch & 1) == 0) {
                    ghostDir = 3;
                    ghost.setDir(3);
                } else if ((ch & 2) == 0) {
                    ghostDir = 0;
                    ghost.setDir(0);
                } else {
                    ghostDir = 2;
                    ghost.setDir(2);
                }
            }


            // Handles wall collisions
            if ((ghostDir == 3 && (ch & 1) != 0) ||
                    (ghostDir == 1 && (ch & 4) != 0) ||
                    (ghostDir == 0 && (ch & 2) != 0) ||
                    (ghostDir == 2 && (ch & 8) != 0)) {
                ghostDir = 4;
                ghost.setDir(4);
            }
        }



            if (ghost.getDir() == 0) {
                ghost.setYPos(ghost.getYPos() + -blockSize/20);
            } else if (ghost.getDir() == 1) {
                ghost.setXPos(ghost.getXPos() + blockSize/20);
            } else if (ghost.getDir() == 2) {
                ghost.setYPos(ghost.getYPos() + blockSize/20);
            } else if (ghost.getDir() == 3) {
                ghost.setXPos(ghost.getXPos() + -blockSize/20);
            }
    }
}//Movement
