/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;



public class Enemy extends Thread{
    public volatile static int contador =0;
    volatile int cont = 0;
    ImageIcon def = Settings.getER()[0];
    Random rnd = new Random();
    Collision col;
    JLabel enemy;
    int index;
    int lastDir = 1;
    EnemyLimiter Limi;
    EnemyManager EnMa;
    CyclicBarrier cb, cb2,cb3;
    Rectangle hitbox = new Rectangle();
    boolean limite = false;
    boolean coli = false;
    Point loc;
    Point position, target, vel;
    int IndexL;
    int maxVel = 5;
    LayerManager LM = Settings.getLM();
    EnemySkin ES;
    
    int Pjx, Pjy;
    double dirx, diry;
    int Ex, Ey;
    int FuX = 0, FuY = 0;
    int[] index2 = {1,1};
    int height = 102;
    int width = 102;
    JLayeredPane canvas;
    
    boolean der = false;
    boolean izq = true;
    boolean vivo = true;
    
    //setters y getters
    
    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    public Rectangle getHitbox() {
        return hitbox;
    }
    
    public void setColi(boolean coli) {
        this.coli = coli;
    }
    
    public int getEnX() {
        return Ex;
    }

    public int getEnY() {
        return Ey;
    }

    public double getDirx() {
        return dirx;
    }

    public double getDiry() {
        return diry;
    }
    
    public Point getPos(){
        return new Point(Ex, Ey);
    }
    
    public void setLimite(boolean limite) {
        this.limite = limite;
    }   
    
    public void damage(int cantidad){
        //referencia para el metodo dano de drawrect.
        try{
            ES.getDamageRef().Damage(cantidad);
        }catch(NullPointerException w){
            System.out.println("1");
        }
    }

    Enemy(JLayeredPane canvas){
        //constructor, se inicialican las varibales, las instancias y la posicion
        //index = cont;
        Settings.addDBE(this);
        this.canvas = canvas;
        col = new Collision();
        
        int x,y;
        
            do{
                x = (rnd.nextInt(170)+4)*10;
                y = (rnd.nextInt(85))*10;
            }while(!col.checkIfClearE(x, y));
        enemy = new JLabel();
        enemy.setIcon(def);
        enemy.setBounds(x, y, 296, 216);
        index = col.addE(x, y, this)-1;
        canvas.add(enemy);
        IndexL = canvas.getIndexOf(enemy);
        LM.add(enemy, "Enemy_"+contador);
        contador++;
        
        
        //se inicialia los CyclicBarrier para sincronizar los differentes threads
        cb2 = new CyclicBarrier(2, new Runnable(){
            @Override
            public void run(){
                //System.out.println("Pasamos cb2 creo...");
            }
        });
        cb3 = new CyclicBarrier(2, new Runnable(){
            @Override
            public void run(){
                //System.out.println("Pasamos cb3 creo...");
            }
        });
        
        ES = new EnemySkin(enemy, this, cb2, cb3);
        
        this.setName(index+"");
        enemy.setName(""+ index);
        
        cb = new CyclicBarrier(3);
        
        loc = new Point(x,y);
        EnMa = new EnemyManager(this, cb);
        Limi = new EnemyLimiter(cb,this);
        this.start();
    }   
    
    @SuppressWarnings("empty-statement")
    public void run(){
        //loop infinito que se encarga de la posicion, siempre y cuando este vivo
        Vectors2D vec = new Vectors2D();
        while(vivo){
            
            try {
                cb.await();
                
            } catch (InterruptedException | BrokenBarrierException ex) {
                Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Thread.sleep(25);
            } catch (InterruptedException ex) {
                Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //establece el objetivo (jugador) y el vector ahead que se encarga
            //de evitar colisiones adelante del enemigo.
            
            Pjx = PlySettings.getPosX();    
            Pjy = PlySettings.getPosY();
            target = new Point(Pjx, Pjy);
            Ex = (int) enemy.getBounds().getX();
            Ey = (int) enemy.getBounds().getY();
            position = new Point(Ex, Ey);
            
            
            dirx = (int)(target.getX()-position.getX());
            diry = (int)(target.getY()-position.getY()); 
            double magnitude = Math.sqrt((Math.pow(dirx, 2))+(Math.pow(diry,2)));
            dirx = (dirx/magnitude)*maxVel;
            diry = (diry/magnitude)*maxVel;
            
//            dirx = (Pjx>Ex)? 2: (Pjx<Ex)? -2: 0; 
//            diry = (Pjy>Ey)? 2: (Pjy<Ey)? -2: 0;
            
            double[] Nvel = vec.normalize(dirx, diry);
            Nvel = vec.mult(Nvel[0], Nvel[1], 10);
            double[] ahead = vec.sum(Ex, Ey, Nvel[0],Nvel[1]);
            if(!vivo) break;
            Object res = col.checkIfClearEa(index,(int)ahead[0], (int)ahead[1]);
            
            //revisa si el bojeto adelante de el es un enemigo.
            
            double[] avoidF;
            if (res instanceof Rectangle){
                
                avoidF = vec.subs(ahead[0], ahead[1], ((Rectangle) res).getCenterX(),((Rectangle) res).getCenterY());
                avoidF = vec.normalize(avoidF[0], avoidF[1]);
                avoidF = vec.mult(avoidF[0], avoidF[1],10);
                
            }else{
                avoidF = new double[]{0,0};
            
            }
            
            
            
            if(dirx>0){
                der = true;
                izq = false;
                lastDir =0;
            }
            else if (dirx<0){
                der = false;
                izq = true;
                lastDir = 1;
            }else{
                der = false;
                izq = false;
            }
            
            
            dirx += avoidF[0];
            diry += avoidF[1];
            
            

            
            hitbox.setBounds(Ex+(int)dirx, Ey+(int)diry, 96, 96);
            
//            try {
//                index2 = col.checkIfClearE(index, Ex, dirx, Ey, diry);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            

            //actualiza la posicion del enemigo
            FuX = Ex+((int)dirx*index2[0]);
            FuY = Ey+((int)diry*index2[1]);
            if(!limite){
                enemy.setLocation(FuX, FuY);
            }else{
                der = false;
                izq = false;
            }
             try {
                 
                cb2.await();
                cb3.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BrokenBarrierException ex) {
                Logger.getLogger(Enemy.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            loc.setLocation(FuX, FuY);
            col.updateE(index, FuX, FuY);
            
//            col.updateE(index, FuX, FuY);
        }
    }
    
    //setters y getters
    
    public Point getLoc(){
        return loc;
    }

    boolean isLeft() {
        return izq;
    }

    boolean isRight() {
        return der;
    }

    int getLastDir() {
        return lastDir;
    }
    
    public Object getCol(int x, int y){
        
        return col.checkIfClearEa(index, x, y);
    }
    
    public int getIndex(){
        return index;
    }
    
    //metodo para eliminar al enemigo de las demas clases.
    
    public void muerto(){
        
        canvas.remove(enemy);
        col.delete(index);
        Settings.getCANVAS_3().remove(ES.getDamageRef());
        vivo = false;
        ES.muerto();
        Limi.muerto();
        EnMa.muerto();
        
    }
    
    
}
