
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author world
 */
public class EnemyManager extends Thread{
    
    Enemy e;
    static volatile int indexCont;
    static int index;
    boolean flag = false;
    CyclicBarrier cb;
    static volatile List<Enemy> enemys = new ArrayList();
    double y1, y2, x1, x2, y, x, hyp, ang;
    Point ahead, aheadX, aheadY;
    boolean vivo = true;
    
    EnemyManager(Enemy e, CyclicBarrier cb){
        
        this.cb = cb;
        this.e = e;
        enemys.add(e);
        index = indexCont;
        indexCont++;
        this.start();
        
    }
    
    public void run(){
            
        while(vivo){
            try {
                pauseThread();   
            } catch (InterruptedException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            //sincroniza los differentes threas que intervienen en el enemigo.
            try {
                cb.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(EnemyManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BrokenBarrierException ex) {
                Logger.getLogger(EnemyManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        }
    }    
    
    public void muerto(){
        vivo = false;
    }
    
    private void pauseThread () throws InterruptedException{
        //se encarga de pausar el thread cuando se entra en el menu durante el juego
        synchronized (Menu.getLock())
        {
            if (Menu.isPause())
                Menu.getLock().wait(); 
        }
    }
}
