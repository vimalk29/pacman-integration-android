package com.example.jimmyle.pacmanandroid;

/**
 * Created by colerogers on 7/25/16.
 */
public class Ghost {
    private int xPos, yPos, dir;

    public Ghost(int blockSize){
        //Ghost is at 8 position of length in the array x-dir
        //e.g. curMap[8,y]
        xPos = 8 * blockSize;

        //Ghost is at 4 position of height in the array y-dir
        //e.g. curMap[x,4]
        yPos = 4 * blockSize;

        dir = 4;
    }

    public int getXPos(){ return xPos; }
    public int getYPos(){ return yPos; }
    public int getDir(){ return dir; }

    public void setXPos(int xPos){ this.xPos = xPos; }
    public void setYPos(int yPos){ this.yPos = yPos; }
    public void setDir(int dir){ this.dir = dir; }
}
