
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
public class LastDir extends Thread {
    
    public void run(){
        
            boolean Right;
            boolean Left;
            boolean Up;
            boolean Down;
            int lastDir=2;
        while(true){
            //se encarga de actualizar el valor de last dir dependiendo del 
            //movimiento del jugador
            
            Right = PlySettings.isRight();
            Down = PlySettings.isDown();
            Up = PlySettings.isUp();
            Left = PlySettings.isLeft();
            
            if(Up){
                lastDir = 1;
            }
            if(Down){
                lastDir = 3;
            }
            if(Left){
                lastDir = 0;
            }
            if(Right){
                lastDir = 2;
            }
            
            PlySettings.setLastDir(lastDir);
        }
    }
    
}
