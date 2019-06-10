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
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 *
 * @author world
 */
public class PlayerSkin extends Thread{
    
    
        
        CyclicBarrier cb;
        CyclicBarrier cb2;
        
        int Skin;
        int lastDir;
        boolean shift;
        
        
        Collision col = new Collision();
        ImageIcon[] R = Settings.getR();
        ImageIcon[] L = Settings.getL();
        ImageIcon[] U = Settings.getU();
        ImageIcon[] D = Settings.getD();
    
    public void run(){
       
        
        boolean Right;
        boolean Left;
        boolean Up;
        boolean Down;
        boolean space;
        
        
        boolean move = false;
        int num=0;
        while(true){
            // maneja el control de la skin del jugador dependiendo de un contador
            // que maneja la continuidad de la skin.
            Right = PlySettings.isRight();
            Down = PlySettings.isDown();
            Up = PlySettings.isUp();
            Left = PlySettings.isLeft();
            space = PlySettings.isSpace();
            lastDir = PlySettings.getLastDir();
            
            if((Right^Left) || (Up ^ Down)){
                num++;
                move = true;
            }
            if(!(Right||Left||Up||Down)){
                num++;
                move = false;
            };
            
            if(move)
                Skin = ((num%6)+5);
            else if(space){
                PlySettings.setAtacking(true);
                try {
                    atack(6, 0, 0);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PlayerSkin.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                PlySettings.setAtacking(false);
            }
            else
                Skin = ((num%5));
            
            
            switch(lastDir){
                case 0: Settings.setPlySkin(L[Skin]);
                    break;
                case 1: Settings.setPlySkin(U[Skin]);
                    break;
                case 2: Settings.setPlySkin(R[Skin]);
                    break;
                case 3: Settings.setPlySkin(D[Skin]);
                    break;                
            }
            try {
                cb.await();
                cb2.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(PlayerSkin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BrokenBarrierException ex) {
                Logger.getLogger(PlayerSkin.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                    
        }
        
    }
    
    public void atack(int max, int sk,int inc) throws InterruptedException{ 
        
        //si es ataquee se debe escoger otros skins y es utiliza la recursividad
        // para el atque que tiene mas skins. 
        int posX = PlySettings.getPosX();
        int posY = PlySettings.getPosY();
        boolean mover=false;
        int cantidad = 30;
        int pos = inc;
        
        for (int i = 0; i < max; i++) {
            
            shift = PlySettings.isShift();
            Thread.sleep(50);
            
            if(i==max-1){
                break;
            }
            if(i == (max - 3) && shift && sk+max+11 < 43){
                int limit = (inc==2)? 8: 6;
                atack(limit, sk+max, inc+ 1);
                break;
            }
            Skin= i+11+sk;
            if(inc == 0&&i ==1)
                areaAtaque(lastDir);
            if((inc>0&&i == 1 && max == 6)){
                cantidad = 30;
                mover = true;
                
                if(pos ==4)
                    areaAtaqueU();
                else
                    areaAtaque(lastDir);
            }else if(i==2 && max ==8){
                cantidad = 90;
                mover = true;
                areaAtaque(lastDir);
            }else{
                mover = false;
            }
                
            switch(lastDir){
                case 0: Settings.setPlySkin(L[Skin]);
                    if(mover){
                        PlySettings.setPosX(posX-cantidad);
                        posX -= cantidad;
                    }
                    break;
                case 1: Settings.setPlySkin(U[Skin]);
                    if(mover){
                        PlySettings.setPosY(posY-cantidad);
                        posY -= cantidad;
                    }
                    break;
                case 2: Settings.setPlySkin(R[Skin]);
                    if(mover){
                        PlySettings.setPosX(posX+cantidad);
                        posX += cantidad;
                    }
                    break;
                case 3: Settings.setPlySkin(D[Skin]);
                    if(mover){
                        PlySettings.setPosY(posY+cantidad);
                        posY += cantidad;
                    }
                    break;                
            }
            
            try {
                cb.await();
                cb2.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(PlayerSkin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BrokenBarrierException ex) {
                Logger.getLogger(PlayerSkin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    public PlayerSkin(CyclicBarrier cb,CyclicBarrier cb2){
        this.cb = cb;
        this.cb2 = cb2;
    }

    private void areaAtaqueU() {
        if(col.atkPj(4)){
            //System.out.println("dio");
        }
    }

    private void areaAtaque(int lastDir) {
        if(col.atkPj(lastDir)){
            //System.out.println("dio");
        }
        
        
    }
}
