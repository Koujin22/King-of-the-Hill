/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Rectangle;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author world
 */
public class EnemyLimiter extends Thread{
    
    CyclicBarrier cb;
    Enemy e;
    Rectangle en;
    Rectangle pj;
    int Px, Py;
    boolean vivo = true;
    
    EnemyLimiter(CyclicBarrier cb, Enemy e){
        en = new Rectangle();
        pj = new Rectangle();
        this.cb = cb;
        this.e = e;
        this.start();
    }
    
    public void run(){
        
        while(vivo){
            try {
                cb.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(EnemyLimiter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BrokenBarrierException ex) {
                Logger.getLogger(EnemyLimiter.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            Px = PlySettings.getPosX();
            Py = PlySettings.getPosY();
            
            //actualiza el hitbox del jugador
            
            en.setBounds(e.getHitbox());
            pj.setBounds(Px-32, Py-30, 128, 100);
            
            if(en.intersects(pj)){
                e.setLimite(true);
            }else{
                e.setLimite(false);
            }
            
        }
    }
    
    public void muerto(){
        vivo = false;
    }
    
    
    
}
