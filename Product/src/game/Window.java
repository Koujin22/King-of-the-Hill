/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Point;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLayeredPane;

/**
 *
 * @author world
 */
public class Window implements Runnable {
    
    private static final JLayeredPane canvas = Settings.getCANVAS_2();
    private static final JLayeredPane canvas3= Settings.getCANVAS_3();
    
    CyclicBarrier cb;
    CyclicBarrier cb2;
    PlySettings pj;
    private Object lock;
    private boolean pause;
    
    public void run(){
//        long lastTime= System.currentTimeMillis();
//        long fps;
//        long time;
//        int counter=0;

//      Repinta todo el canvas buscando mantener los fps constantes
        long startTime =  System.nanoTime();
        long curTime;
        double  enc = 0;
        int mult = 1;
        int time;
        new Enemy(Settings.getCANVAS_2());
        while(true){
            try {
                cb.await();
            } catch (InterruptedException | BrokenBarrierException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            long start = System.currentTimeMillis();
           
            //enc = ((game.Level.getLevel())/(5+game.Level.getLevel()))+.2;
            curTime = System.nanoTime();
            time =  (int)((curTime-startTime)/1000000000);
            if(time%5==0  && mult*5==time){
                mult++;
                new Enemy(Settings.getCANVAS_2());
            }
            
            
//            counter++;
            canvas.repaint();
            canvas3.repaint();
            try {
                pauseThread();   
            } catch (InterruptedException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            long end = System.currentTimeMillis();
            long delay = ((20-(end-start))>=0)? 20-(end-start): 0;
            
           // System.out.println(delay);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                cb2.await();
            } catch (InterruptedException | BrokenBarrierException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void Muerto(){
        System.out.println("Estas muerto");
        System.exit(0);
        //cuando el jugador muere
    }
    
    Window(){
        
    }
    
    private void pauseThread () throws InterruptedException{
        // metodo para el menu de pausa.
        synchronized (lock)
        {
            if (Menu.isPause())
                lock.wait(); 
        }
    }
   
    
    Window(CyclicBarrier cb,CyclicBarrier cb2, Object lock, boolean pause){
        this.cb = cb;
        this.cb2 = cb2;
        this.lock = lock;
        this.pause = pause;
    }
    
    
}
