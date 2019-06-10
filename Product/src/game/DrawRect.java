/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author world
 */
public class DrawRect extends JLabel {

    
    private int total = 200;
    private int hpLeft = total;
    private int height = 25;
    Enemy ene = null;
    Window wnd = new Window();

    DrawRect(){
        
        //constructor para el jugador
    }
    

    
    DrawRect(Enemy e){
        //Constructor para la barra de vida de los enemigos
        height = 10;
        total = (((500*(Level.getLevel()))/(30+(Level.getLevel())))+100);
        hpLeft = total;
        ene = e;
    }

  
        
    
    public void lvlup(int i){
        
        //metodo para curar al jugador dependiendo del nivel.
        if(ene==null){
            hpLeft += (int) ((75*i)/(20+i))+10;
            if(hpLeft>100) hpLeft = 100;
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        
        //se modifica el paintcomponent para poder dibujar los rectangulos 
        //que representan la vida.
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        g2.fillRect(5,5,hpLeft,height);
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(5,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND));
        g2.drawRect(5,5,total,height);

    }

    public void Damage(int cantidad){
        
        //metodo para infligir dano.
        hpLeft -= cantidad;
        if(hpLeft<=0)
            if(ene!=null)
                ene.muerto();
            else
                System.exit(0);
        
            
    }
    
    

    @Override
    public String getName(){
        return "drawRect";
    }
   

    
}
