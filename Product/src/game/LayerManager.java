/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLayeredPane;

import java.awt.Component;
import java.util.Collections;

/**
 *
 * @author world
 */
public  class LayerManager extends Thread {
    
    private static final CopyOnWriteArrayList<CharacterIndex> cha = new CopyOnWriteArrayList<>();
    private static final JLayeredPane canvas = Settings.getCANVAS_2();
    private static CyclicBarrier cb = new CyclicBarrier(1);
    private static CyclicBarrier cb2 = new CyclicBarrier(1);
    ArrayList<CharacterIndex> temp;
    private int index;
    static int  contador = 0;
    
    
    
    
    public void run(){
        boolean flag = true;
        contador++;
        while(true){
            
            // Organiza todos los personajes dependiendo su eje Y y los 
            // ubica en una profundidad apropiada.
            temp = new ArrayList(cha);
            Collections.sort(temp, (a, b) -> a.getC().getY()+a.getC().getHeight() 
                    < b.getC().getY()+b.getC().getHeight() ? -1 : a.getC().getY()+a.getC().getHeight() 
                            == b.getC().getY()+b.getC().getHeight() ? 0 : 1);
//                
            
            for (int i = 0; i < temp.size(); i++) {
                
                canvas.setLayer(temp.get(i).getC(), i);
                
            }
          
            try {
                cb.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(PlyManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BrokenBarrierException ex) {
                Logger.getLogger(PlyManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    public void add(Component c, String name){
        
        this.setName("LM_"+contador);   
        cha.add(new CharacterIndex(c, name));
            
       
        
    }
    
    
    
//    <editor-fold defaultstate="collapsed" desc="Privat Static">    
//    static volatile ArrayList<CharacterIndex> cha =  new ArrayList<>();
//    private static final JLayeredPane canvas = Settings.getCANVAS_2();
//    private static final Object obj = new Object();
//    
//    public void run(){
//        
//        while(true){
//            try{
//                Collections.sort(cha, (a, b) -> a.getyPos() < b.getyPos() ? -1 : a.getyPos() == b.getyPos() ? 0 : 1);
//            }catch(ConcurrentModificationException e){
//                System.out.println("Error run");
//                continue;
//            }
//            
//            for (int i = 0; i < cha.size(); i++) {
//                
//                canvas.setLayer(canvas.getComponent(cha.get(i).getIndex()), i);
//                
//            }
//        }
//        
//    }
//    
//    public synchronized void add(int index, int y){
//        boolean flag = true;
//        do{
//            try{
//                cha.add(new CharacterIndex(index, y));
//            flag = false;
//            }catch(ConcurrentModificationException e){
//                System.out.println("Error add ");
//            }
//        }while(flag);
//        
//    }
//    
//    public synchronized void update(int index, int y){
//        boolean flag = true;
//        do{
//            try{
//                Collections.sort(cha, (a, b) -> a.getyPos() < b.getyPos() ? -1 : a.getyPos() == b.getyPos() ? 0 : 1);
//                flag = false;
//            }catch(ConcurrentModificationException e){
//                System.out.println("Error update");
//            }
//        }while(flag);
//        cha.get(index).setyPos(y);
//        
//    }
//    </editor-fold>

    void sendCB(CyclicBarrier cb, CyclicBarrier cb2) {
        
        this.cb = cb;
        this.cb2 = cb2;
    
    }

    
     

}
