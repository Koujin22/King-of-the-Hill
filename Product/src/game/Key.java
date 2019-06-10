/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author world
 */
public class Key implements KeyListener{

    @Override
    public void keyTyped(KeyEvent e) {
        //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            keyPressed(e.getKeyCode(), true);
        } catch (InterruptedException ex) {
            Logger.getLogger(Key.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        
    // Se encarga de recivir los inputs del teclado
    
    @Override
    public void keyReleased(KeyEvent e) {
        try {
            Thread.sleep(25);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Key.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            keyPressed(e.getKeyCode(), false);
        } catch (InterruptedException ex) {
            Logger.getLogger(Key.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void keyPressed(int code, boolean press) throws InterruptedException{
        //dependiendo del codigo se traduce a la tecla asiganda.
        switch (code){
            case 27:Menu.GP();
                break;
            case 40:PlySettings.setDown(press);
                break;
            case 39:PlySettings.setRight(press);
                break;
            case 38:PlySettings.setUp(press);
                break;
            case 37:PlySettings.setLeft(press);
                break;
            case 16:PlySettings.setShift(press);
                break;
            case 32:PlySettings.setSpace(press);
                break;
            
            default:
                break;
                
        }
    }
    
    
    
}
