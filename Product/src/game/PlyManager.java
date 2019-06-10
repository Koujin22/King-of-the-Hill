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
public class PlyManager extends Thread {
    
    CyclicBarrier cb;
    CyclicBarrier cb2;
    CyclicBarrier cb3;
    
    public void run(){
            
        //Se encarga de sincronizar los threads relacionados con el jugador.
        
        while(true){
            try {
                cb.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(PlyManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BrokenBarrierException ex) {
                Logger.getLogger(PlyManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            Settings.getCHAR().setBounds(PlySettings.getPosX(),PlySettings.getPosY(), 128, 160);
            Settings.getCHAR().setIcon(Settings.getPlySkin());
            try {
                cb2.await();
                cb3.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(PlyManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BrokenBarrierException ex) {
                Logger.getLogger(PlyManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public void start(){
        
        
        super.start();
    }
    
    PlyManager(CyclicBarrier cb,CyclicBarrier cb2,CyclicBarrier cb3){
        this.cb = cb;
        this.cb2 = cb2;
        this.cb3 = cb3;
    }
    
    
    
}
