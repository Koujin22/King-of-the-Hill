/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
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
public class EnemySkin extends Thread{
    
    private final JLabel eneC;
    private final static ImageIcon[] L = Settings.getER();
    private final static ImageIcon[] R = Settings.getEL();
    private final Enemy enemy;
    private final CyclicBarrier cb2, cb3;
    int ite;
    int Skin;
    Point py, ene;
    Rectangle pj;
    int Px;
    int Py;
    double Distance;
    boolean repeticion;
    static DrawRect HP = Settings.getUI();
    DrawRect OHP;
    JLayeredPane canvas = Settings.getCANVAS_3();
    boolean vivo = true;
    Collision col = new Collision();
    game.Level lvl = new game.Level();
    
    public EnemySkin(JLabel c, Enemy e, CyclicBarrier cb2, CyclicBarrier cb3){
        //se encarga de inicialisar la barra de vida y la skin del enemigo.
        eneC = c;
        enemy = e;
        this.cb2 = cb2;
        this.cb3 = cb3;
        OHP = new DrawRect(e);
        OHP.setBounds(0,0,250,250);
        canvas.add(OHP, new Integer(0));
        start();
    }
    
    public DrawRect getDamageRef(){
        
        return OHP;
    }
    
    public void run(){
        
        boolean Right;
        boolean Left;
        int lastDir;
        pj = new Rectangle();
        boolean move = false;
        int num=0;
        do{
            //loop que se encarga de actualizar la posicion de la barra de vida
            // y la skin que debe tener el enemigo. 
            OHP.setLocation((int)eneC.getBounds().getCenterX()-50,eneC.getY());
            Px = PlySettings.getPosX();
            Py = PlySettings.getPosY();
            
            pj.setBounds(Px-32, Py-60, 128, 120);
            py = new Point((int) pj.getCenterX(), (int) pj.getCenterY());
            ene = new Point((int)eneC.getBounds().getCenterX()-70,(int)eneC.getBounds().getCenterY() -100  );
            try {
                cb2.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(EnemySkin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BrokenBarrierException ex) {
                Logger.getLogger(EnemySkin.class.getName()).log(Level.SEVERE, null, ex);
            }
            Right = enemy.isRight();
            Left = enemy.isLeft();
            lastDir = enemy.getLastDir();
            
            //define hacia que lado esta viendo el enemigo
            if((Right^Left)){
                num++;
                move = true;
            }
            if(!(Right||Left)){
                num++;  
                move = false;
            }
            if(!vivo) break;
            boolean flag = ChecarAtk(lastDir);
            //diferencia entre si esta caminando o atacando.
            if(!move && flag){
                
                try {
                    Skin = 0;
                    lastDir = (PlySettings.getPosX()-(int) eneC.getBounds().getX()>0)?0:1;
                    
                    Ataque( lastDir, Right);
                    repeticion = true;
                    continue;
                } catch (InterruptedException ex) {
                    Logger.getLogger(EnemySkin.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Skin = 0;
            }else if(move){
                if(!vivo) break;
                ite = ((((num-(num%2))/2)%12));
                Skin = (Right)? (ite<10)? ((4+(((ite/5))*10))-ite )+8: (21-ite)+8 :ite+8;
                repeticion = false;
                
            }
//            
            else{
                Skin = ((num - (num%4))/4)%8;
                repeticion = false;
            }
            
            
            switch(lastDir){
                case 0: eneC.setIcon(L[Skin]);//Settings.setPlySkin(ER[Skin]);
                    break;
                case 1: eneC.setIcon(R[Skin]);
                    break;
            }
            
            
            try {
                cb3.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(EnemySkin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BrokenBarrierException ex) {
                Logger.getLogger(EnemySkin.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                    
        }while(vivo);
        
    }
    
    public void Ataque( int lastDir, boolean Right) throws InterruptedException{
        
        //cuando ataca la eleccion de la skin cambia y por lo tanto este metodo es llamado
        int temp = Skin;
        int x = (int) eneC.getBounds().getX();
        int y = (int) eneC.getBounds().getY();
        if(repeticion)
            switch(lastDir){
                case 0:
                    x += 7;
                    break;
            }
           
        //diferencia entre la izq o derecha
        switch(lastDir){
            case 0:
                eneC.setBounds(x-1, y-28, 296, 216);
                eneC.setIcon(L[20]);
                break;
            case 1:
                eneC.setBounds(x-109, y-24, 296, 216);
                eneC.setIcon(R[20]);
                break;
        }
        
        int num = 0;
        int contador = 0;
        
        //entra en un loop para poder ejecutar completa la animacion de ataque
        for (int i = 0; i < 28; i++) {
            ite = ((((num-(num%2))/2)%14));
            Skin = (lastDir==0)? (ite<12)? ((2+((ite/3)*6))-ite)+20: (25-ite)+20 :ite+20;
            
            num++;
            switch(lastDir){
                case 0: eneC.setIcon(L[Skin]);//Settings.setPlySkin(ER[Skin]);
                        eneC.setBounds(x-1, y-28, 296, 216);
                    break;
                case 1: eneC.setIcon(R[Skin]);
                        eneC.setBounds(x-109, y-24, 296, 216);
                    break;
            }
            
            //si acierta hace dano al jugador
            if((i >20&&i<=24)&& (areaAtaque(lastDir))){
                contador++;
                if(contador%4==0)
                    HP.Damage(((40*game.Level.getLevel())/(15+game.Level.getLevel()))+10);
            }
            Thread.sleep(25);
        }
        switch(lastDir){
            case 0: eneC.setLocation(x-7,y); 
                    eneC.setIcon(L[temp]);//Settings.setPlySkin(ER[Skin]);
                    
                break;
            case 1: eneC.setLocation(x, y);
                    eneC.setIcon(R[temp]);
                break;
        }
        
        try {
            cb3.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(EnemySkin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BrokenBarrierException ex) {
            Logger.getLogger(EnemySkin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    

    void muerto() {
        vivo = false;
        OHP = null;
    }

    private boolean areaAtaque(int lastDir) {
        //checa si puede atacar
        return (col.atkEne(enemy.getIndex(), lastDir));
         
    }

    private boolean ChecarAtk(int lastDir) {
        //checa si el ataque dio al jugador
        return col.atkEne(enemy.getIndex(), lastDir);
        
    }

    
}
