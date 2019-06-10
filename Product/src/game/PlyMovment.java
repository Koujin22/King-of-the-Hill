/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author world
 */
public class PlyMovment extends Thread {
    
        CyclicBarrier cb;
        CyclicBarrier cb2;
    
    @Override
    public void run(){
        
        // se encarga de manejar, dependiendo del input de key, el movimiento
        // y posicion del jugador.
        Collision col = new Collision();
        
        boolean Right;
        boolean Left;
            boolean Up;
        boolean Down;
        int posX = PlySettings.getPosX();
        int posY = PlySettings.getPosY();
        
        while(true){
            Right = PlySettings.isRight();
            Down = PlySettings.isDown();
            Up = PlySettings.isUp();
            Left = PlySettings.isLeft();
            
            posX = PlySettings.getPosX();
            posY = PlySettings.getPosY();
            
//            System.out.println(posX);
//            System.out.println(posY);
//            System.out.println("");
            
            
            int fx = (Right&&Left)?0:(Right)?10:(Left)?-10:0;
            int fy = (Up&&Down)?0:(Up)?-10:(Down)?10:0;
            if((fx==0||fy==0)&&(col.checkIfClear((int)fx,(int)fy))){
                
                posX = posX + fx;
                posY = posY + fy;
                
            }
            else{
                
                fx = (fx/10)*(Math.abs(fx)-3);
                fy = (fy/10)*(Math.abs(fx)-3);
                if(col.checkIfClear(fx, fy)){
                    posX = posX + fx;
                    posY = posY + fy;
                }
                
            }
            
            if(!PlySettings.isAtacking()){
                PlySettings.setPosX(posX);
                PlySettings.setPosY(posY);
            }
            
            
            try {
                cb.await();
                cb2.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(PlyMovment.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BrokenBarrierException ex) {
                Logger.getLogger(PlyMovment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public PlyMovment(CyclicBarrier cb,CyclicBarrier cb2){
        this.cb = cb;
        this.cb2 = cb2;
    }
    
}
