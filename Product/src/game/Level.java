/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.List;

/**
 *
 * @author world
 */
public class Level extends Thread {
    static int level = 0;
    
    public void lvlup(){
        //se encarga de guardar y actualizar el nivel
        level ++;
        Settings.getUI().lvlup(level);
    }

    public static int getLevel() {
        return level;
    }
    
    public void run(){
        
        List<Collision> arre = Collision.getArrE();
        while(true){
            //verifica el nivel en cada actualizacion.
            if((arre.size()+1)%6==5){
                lvlup();
                System.out.println("LVL UP");
                new Enemy(Settings.getCANVAS_2());
                
            }

        }

    }
    
}


