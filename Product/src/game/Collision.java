 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;

/**
 *
 * @author world
 */
public class Collision {
    private Rectangle temp;
    static private Collision logs, pj, bag, fruit, grain, store;
    private static List<Collision> arr = new ArrayList();
    private volatile static List<Collision> arre = new ArrayList();
    private int px;
    private int py;
    private int index;
    private static int contador = 0;
    private int Ax;
    private int Ay;
    
    private Enemy e;
    
    Collision EnX;
    Collision EnY;
    Collision En;

    public static List<Collision> getArrE() {
        return arre;
    }
    
    
    
    Level lvl = new Level();
    
        int rx;
        int ry;
    
    static{
        // Se encarga de establecer colision en todos los objetos predefinidos
        pj = new Collision(new Rectangle(250, 250, 240, 580));
        logs = new Collision(new Rectangle(40, 125, 170, 150));
        bag = new Collision(new Rectangle(620+100, 100, 100, 150));
        fruit = new Collision(new Rectangle(815+50, 100, 350, 150));
        grain = new Collision(new Rectangle(1200,25,460,200));
        store = new Collision(new Rectangle(1570,185,90,160));
        
        
        arr.add(logs);
        arr.add(bag);
        arr.add(fruit);
        arr.add(grain);
        arr.add(store);
        
        for (int i = 0; i < arr.size(); i++) {
            Settings.getCANVAS_3().add(new DrawRect());
        }
    }
    
    public void updateE(int indexM, int x, int y){
        //actualiza la posicion de los enemigos
        Collision temp = arre.get(indexM);
        if(temp!=null){
            temp.getTemp().setLocation(x, y);
            arre.set(indexM,temp);
        }
    }
    
    public boolean checkIfClearE(int x, int y){
        //Busca colision entre el enemigo y los objetos del mapa
        boolean flag = true;
        Collision Enemy = new Collision(new Rectangle(250, 250, 96, 96));
        
        Enemy.setR(x, y);
        
        for (int i = 0; i < arr.size(); i++) {
            if(Enemy.isInterjecting(arr.get(i))){
                flag = false;
            }
        }
      
            
        
        
        if(x<40||x>1740||y<0||y>840){
            flag = false;
        }
        
        return flag;
    }
    
    public void setE(Enemy e){
        //Estabelce referencia al objeto enemigo
        this.e = e;
    }
    
    public Enemy getE(){
        //Consigue el objeto enemigo
        return e;
    }
    
    
    public int addE(int x, int y, Enemy e){
        //Agrega un enemigo a la lista de enemigos y regresa la posicion del objeto
        //en el array.
        Collision Enemy = new Collision(new Rectangle(250, 250, 96, 96),contador);
        Enemy.setR(x, y);
        Enemy.setE(e);
        contador++;
        arre.add(Enemy);
        return contador;
        
    }
    
    public boolean atkEne(int index, int lastDir){
        
        // Maneja la colision del ataque del enemigo hacia el jugador
        Enemy e;
        try{
            e = arre.get(index).getE();
        }catch(Exception j){
            return false;
        }
        Rectangle atk;
        boolean flag = false;
        switch(lastDir){
            case 0:
                atk = new Rectangle((int)(e.getHitbox().getX()+e.getHitbox().getWidth()/2),
                        (int)e.getHitbox().getY(),(int)e.getHitbox().getWidth(),
                        (int)e.getHitbox().getHeight());
                break;
            case 1:
                atk = new Rectangle((int)(e.getHitbox().getX()-e.getHitbox().getWidth()/2),
                        (int)e.getHitbox().getY(),(int)e.getHitbox().getWidth(),
                        (int)e.getHitbox().getHeight());
                break;
            default:
                atk = null;
                break;
        }
        return atk.intersects(pj.getTemp());
    }
    
    public boolean atkPj(int lastDir){
        //Se encarga del ataque del jugador hacia los enemigos dependiendo de la 
        // ultima direccion
        boolean flag = false;
        Rectangle atk;
        switch(lastDir){
            case 0:
                atk = new Rectangle((int)(pj.getTemp().getX()-(pj.getTemp().getWidth()/2)),
                        (int)pj.getTemp().getY(),(int)pj.getTemp().getWidth(),
                        (int)pj.getTemp().getHeight());
                break;
            case 1:
                atk = new Rectangle((int)(pj.getTemp().getX()),
                        (int)(pj.getTemp().getY()-(pj.getTemp().getHeight()/2)),
                        (int)pj.getTemp().getWidth(),(int)pj.getTemp().getHeight());
                break;
            case 2:
                atk = new Rectangle((int)(pj.getTemp().getX()+(pj.getTemp().getWidth()/2)),
                        (int)pj.getTemp().getY(),(int)pj.getTemp().getWidth(),
                        (int)pj.getTemp().getHeight());
                break;
            case 3:
                atk = new Rectangle((int)(pj.getTemp().getX()),
                        (int)(pj.getTemp().getY()+(pj.getTemp().getWidth()/2)),
                        (int)pj.getTemp().getWidth(),(int)pj.getTemp().getHeight());
                break;
            case 4:
                atk = new Rectangle((int)(pj.getTemp().getX()-(pj.getTemp().getWidth()/2)),
                        (int)(pj.getTemp().getY()-(pj.getTemp().getHeight()/2)),
                        (int)(pj.getTemp().getWidth()+(pj.getTemp().getWidth()/2)),
                        (int)(pj.getTemp().getHeight()+(pj.getTemp().getWidth()/2)));
                break;
            default:
                atk = null;
                break;
        }
        
        for (int i = 0; i < arre.size(); i++) {
            try{
                if(atk.intersects(arre.get(i).getTemp()))
                    arre.get(i).getE().damage(((200*Level.getLevel())/(30+Level.getLevel()))+20);
                    flag = true;
            }catch(Exception e){
                continue;
            }
        
        }
        return flag;
    }
    
    public void setAhead(int bx, int by){
        //Actualiza el vector ahead para el movimiento inteligente de los enemigos
        Ax = bx;
        Ay = by;
    }
    
    public int getIndex(){
        return index;
    }
    
    public void delete(int index){
        //Elimina a los enemigos muertes de la lista de enemigos
        arre.set(index,null);
        
    }
    
    public Object checkIfClearEa(int indexM, int a, int b){
        //Verifica la colision entre enemigos.
        Rectangle EneInt = new Rectangle();
        boolean flag = true;
        Collision enemy2 = arre.get(indexM);
        enemy2.setAhead(a, b);
        
        for (int i = 0; i < arre.size(); i++) {
            if(indexM==i)
                continue;
            if(arre.get(i)==null)continue;
            if(enemy2.isInterjectingE(arre.get(i))){
                flag = false;
                
                if(enemy2.getTemp().getLocation().distance(arre.get(i).getTemp().getLocation())<enemy2.getTemp().getLocation().distance(EneInt.getLocation()))
                    EneInt = arre.get(i).getTemp();
            }
        }
        
            
        px = (int) enemy2.getTemp().getX();
        py = (int) enemy2.getTemp().getX();
        
        
        if(flag){
            return true;
        }else{
            return EneInt;
        }
    }
    
    
    public boolean checkIfClear(int x, int y){
        
        //Verifica la colision del jugador con los bordes del mapa y los objetos 
        //del mapa
        boolean flag = true;
        
        pj.setR(PlySettings.getPosX()+x, PlySettings.getPosY()+y);
        
        for (int i = 0; i < arr.size(); i++) {
            if(pj.isInterjecting(arr.get(i))){
                flag = false;
            }
        }
      
            
        px = pj.getTemp().x;
        py = pj.getTemp().y;
        
        if(px<40||px>1740||py<0||py>840){
            flag = false;
        }
        
        return flag;
    }
    
    public int getAx(){
        return Ax;
    }
    
    public int getAy(){
        return Ay;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Privat Static">
//    public boolean checkIfClearE(int index, int x, int y ,int o){
//        
//        try{
//            arrE.get(index).setR(x, y);
//            En = arrE.get(index);
//        }catch(Exception e){
//            En = new Collision(new Rectangle(x,y, 64, 96));
//        }
//        boolean flag = true;
//        for (int i = 0; i < arrE.size(); i++) {
//            if(i == index) continue;
//            if(En.isInterjectingE(arrE.get(i))){
//                flag = false;
//            }
//        }
//        
//        for (int i = 0; i < arr.size(); i++) {
//            if(En.isInterjecting(arr.get(i))){
//                flag = false;
//            }
//        }
//        
//        return flag;
//    }
    
//    public int[] checkIfClearE(int index, int x, int dx, int y, int dy) throws InterruptedException{
//        int[] result = {0,0};
//        Thread X = new Thread( new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    arrE.get(index).setR(x+dx, y);
//                    EnX = arrE.get(index);
//                }catch(Exception e){
//                    EnX = new Collision(new Rectangle(x+dx,y, 64, 96));
//                }
//                rx = 1;
//                for (int i = 0; i < arrE.size(); i++) {
//                    if(i == index) continue;
//                    if(EnX.isInterjectingE(arrE.get(i))){
//                        rx = 0;
//                    }
//                }
//
//                for (int i = 0; i < arr.size(); i++) {
//                    if(EnX.isInterjecting(arr.get(i))){
//                        rx = 0;
//                    }
//                }
//
//            }
//        });
//        
//        Thread Y = new Thread( new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    arrE.get(index).setR(x, y+dy);
//                    EnY = arrE.get(index);
//                }catch(Exception e){
//                    System.out.println("err");
//                    EnY = new Collision(new Rectangle(x,y+dy, 64, 96));
//                }
//                ry = 1;
//                for (int i = 0; i < arrE.size(); i++) {
//                    if(i == index) continue;
//                    if(EnY.isInterjectingE(arrE.get(i))){
//                        ry = 0;
//                        System.out.println(i);
//                        System.out.println(index);
//                        System.out.println("");
//                    }
//                }
//
//                for (int i = 0; i < arr.size(); i++) {
//                    if(EnY.isInterjecting(arr.get(i))){
//                        ry = 0;
//                    }
//                }
//                
//            }
//        });
//        
//        X.start();
//        Y.start();
//        X.join();
//        Y.join();
//        
//        result[0]=rx;
//        result[1]=ry;
//        
//        return result;
//
//    }
    //</editor-fold>
    
    public boolean isInterjectingE(Collision c){
        
        //Verifica que dos objetos collision esten chocando.
        //Line2D th = new Line2D.Double(this.getTemp().getX(), this.getTemp().getY(), this.getAx(), this.getAy());
         
        Ellipse2D th = new Ellipse2D.Double(this.getTemp().getX(), this.getTemp().getY(),96,96);
        Rectangle obj = c.getTemp();
        //System.out.println(this.index + ": "+this.getTemp().getX());
        
        return th.intersects(obj);
    }
    
    public boolean isInterjecting(Collision c){ 
        //verifica dos rectangulos colisionando.
        Rectangle th = this.getTemp();
        Rectangle obj = c.getTemp();
        
        
        
        
        return (( (th.getLocation().x<(obj.getLocation().x + obj.getWidth())) && 
                (th.getLocation().x>=(obj.getLocation().x)) ) && ( 
                (th.getLocation().y>(obj.getLocation().y -obj.getHeight())) && 
                th.getLocation().y<(obj.getLocation().y)) );
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="Privat Static">
//    public boolean isInterjectingE(Collision c){ 
//        Rectangle th = this.getTemp();
//        Rectangle obj = c.getTemp();
//        
//        
//        return th.intersects(obj);
//        
//    }
    //</editor-fold>
    
    public Collision(){
        
    }
    
    public Collision(Rectangle r){
        
        temp = r;
        
    }
    
    public Collision(Rectangle r, int index){
        temp = r;
        this.index = index;
        
    }
    
    public Rectangle getTemp() {
        return temp;    
    }
    
    public void setR(int x, int y){
        this.getTemp().setLocation(x, y);
    }
   
    // <editor-fold defaultstate="collapsed" desc="Privat Static">
//    public void AddE(int x, int y, int w, int h){
//        
//        arrE.add(new Collision(new Rectangle(x,y, w, h)));   
//        
//    }
//    
//    public void updateE(int num, int w, int h){
//        Collision temp = arrE.get(num);
//        temp.setR(w, h);
//        arrE.set(num, temp);
//    }
    // </editor-fold>
  
    
    
    
    
}
