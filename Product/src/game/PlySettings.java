/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author world
 */
public class PlySettings {
    private static volatile int lastDir = 2;
    private static volatile int posX = 250;
    private static volatile int posY = 250;
    private static volatile boolean move = false;
    private static volatile int skin = 1;
    private static volatile boolean Right = false;
    private static volatile boolean Left = false;
    private static volatile boolean Up = false;
    private static volatile boolean Down = false;
    private static volatile boolean space = false;
    private static volatile boolean shift = false;
    private static volatile boolean atacking = false;
    
    //Guarda las variables del jugador

    public static boolean isAtacking() {
        return atacking;
    }

    public static void setAtacking(boolean atacking) {
        PlySettings.atacking = atacking;
    }
   
    public static boolean isShift() {
        return shift;
    }

    public static void setShift(boolean shift) {
        PlySettings.shift = shift;
    }

    public static boolean isSpace() {
        return space;
    }

    public static void setSpace(boolean space) {
        PlySettings.space = space;
    }
    
    public static int getLastDir() {
        return lastDir;
    }

    public static int getPosX() {
        return posX;
    }

    public static int getPosY() {
        return posY;
    }

    public static boolean isMove() {
        return move;
    }

    public static int getSkin() {
        return skin;
    }

    public static void setLastDir(int lastDir) {
        PlySettings.lastDir = lastDir;
    }

    public static void setPosX(int posX) {
        PlySettings.posX = posX;
    }

    public static void setPosY(int posY) {
        PlySettings.posY = posY;
    }

    public static void setMove(boolean move) {
        PlySettings.move = move;
    }

    public static void setSkin(int skin) {
        PlySettings.skin = skin;
    }

    public static boolean isRight() {
        return Right;
    }

    public static void setRight(boolean Right) {
        PlySettings.Right = Right;
    }

    public static boolean isLeft() {
        return Left;
    }

    public static void setLeft(boolean Left) {
        PlySettings.Left = Left;
    }

    public static boolean isUp() {
        return Up;
    }

    public static void setUp(boolean Up) {
        PlySettings.Up = Up;
    }

    public static boolean isDown() {
        return Down;
    }

    public static void setDown(boolean Down) {
        PlySettings.Down = Down;
    }
     
    
    
    
    
    
    private PlySettings(){}
}
