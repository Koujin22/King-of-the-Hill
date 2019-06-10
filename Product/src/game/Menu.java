/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.OverlayLayout;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


/**
 *
 * @author world
 */
public class Menu {
    
    static int sizeX = Settings.getSIZE_X();
    static int sizeY = Settings.getSIZE_Y();
    static JLayeredPane menu;
    static final Object lock = new Object();
    static volatile boolean pause =  false;
    static BufferedImage button;
    
    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, IOException{
        
        //inicia la ventana
        
        JFrame window = Settings.getWINDOW();
        JLayeredPane parent = new JLayeredPane();
        window.addKeyListener(new Key());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(sizeX, sizeY);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setUndecorated(true);
        
        
        //inicia el canvas para el menu tanto principal como para el de pausa
        
        JLayeredPane canvasM = new JLayeredPane();
        parent.add(canvasM,new Integer(0));
        canvasM.setBounds(0,0, sizeX, sizeX);
        window.add(parent);
        canvasM.setDoubleBuffered(true);
        
        
        //organiza todas las imagenes del menu
        JLabel imgBg = new JLabel();
        ImageIcon icon = new ImageIcon("src/Resources/Imges/menu_bg.jpg");
        Image img = icon.getImage();
        imgBg.setIcon(new ImageIcon(getScaledImage(img, sizeX, sizeY)));
        imgBg.setBounds(0,0,sizeX,sizeY);
        canvasM.add(imgBg, new Integer(0));
       
        button = ImageIO.read(new File("src/Resources/Imges/buttons.png"));        
        JLabel buttonJ = new JLabel();       
        buttonJ.setText("JUGAR");
        buttonJ.setFont(new Font("Serif", Font.PLAIN, 29));
        buttonJ.setHorizontalTextPosition(JLabel.CENTER);
        buttonJ.setVerticalTextPosition(JLabel.CENTER);
        buttonJ.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                
                JLayeredPane canvasL = new JLayeredPane();
                parent.add(canvasL);
                
                JLabel loading = new JLabel();
                loading.setIcon(new ImageIcon(new ImageIcon("src/Resources/Imges/LoadingScreen.jpg").getImage().getScaledInstance(sizeX,sizeY,Image.SCALE_DEFAULT)));
                loading.setBounds(0,0,sizeX,sizeY);
                canvasL.add(loading, new Integer(0));
                canvasL.setBounds(0,0, sizeX, sizeY);
                
                int MAX = 100;
                JProgressBar pb = new JProgressBar();
                pb.setMinimum(0);
                pb.setMaximum(MAX);
                pb.setStringPainted(true);
                pb.setBounds(100,1000, 1720, 50);
                parent.remove(canvasM);
                canvasL.add(pb, new Integer(1));
                parent.revalidate();
                parent.paintImmediately(0,0,sizeX,sizeY);
                try {
                    Game g = new Game(window,parent, canvasL, pb, lock, pause);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
               
            }
        
        });
        buttonJ.setIcon(new ImageIcon((new ImageIcon(button.getSubimage(240, 60, 200, 100))).getImage().getScaledInstance(400,150, Image.SCALE_DEFAULT)));
        
        JLabel buttonC = new JLabel();
        buttonC.setText("Salir        ");
        buttonC.setFont(new Font("Serif", Font.PLAIN, 29));
        buttonC.setHorizontalTextPosition(JLabel.CENTER);
        buttonC.setVerticalTextPosition(JLabel.CENTER);
        buttonC.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mousePressed(MouseEvent e) {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        buttonC.setIcon(new ImageIcon(new ImageIcon(button.getSubimage(450, 0, 150, 150)).getImage().getScaledInstance(200,200, Image.SCALE_DEFAULT)));
        buttonC.setBounds(1740, 0, 200, 200);
        canvasM.add(buttonC, new Integer(1));
        
        buttonJ.setBounds((sizeX/2)-200, (sizeY/2)-212, 400, 150);
        canvasM.add(buttonJ, new Integer(1));
        
        
        
        window.setVisible(true);
        //Game g = new Game(window);
        
    }
    
    private static Image getScaledImage(Image srcImg, int w, int h){
        // cambia el tama;o de la imagen
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
    
    public static void GP() throws InterruptedException{
        //Menu durante el juego, se encarga de pausar los demas trheads y generar
        //las graficas del menu.
        pause = true;
        

        JLabel buttonJ = new JLabel();
        buttonJ.setText("Salir");
        buttonJ.setFont(new Font("Serif", Font.PLAIN, 29));
        buttonJ.setHorizontalTextPosition(JLabel.CENTER);
        buttonJ.setVerticalTextPosition(JLabel.CENTER);
        buttonJ.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {

                System.exit(0);


            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });

        JLabel buttonE = new JLabel();
        buttonE.setText("Reanudar");
        buttonE.setFont(new Font("Serif", Font.PLAIN, 29));
        buttonE.setHorizontalTextPosition(JLabel.CENTER);
        buttonE.setVerticalTextPosition(JLabel.CENTER);
        buttonE.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                synchronized(lock){
                    menu.removeAll();
                    menu.repaint();
                    lock.notifyAll();
                    pause = false;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
        
        JLabel buttonS = new JLabel();
        buttonS.setText("Nivel: "+(game.Level.getLevel()+1));
        buttonS.setFont(new Font("Serif", Font.PLAIN, 61));
        buttonS.setForeground(Color.WHITE);
        buttonS.setHorizontalTextPosition(JLabel.CENTER);
        buttonS.setVerticalTextPosition(JLabel.CENTER);
        buttonS.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
          
        buttonS.setBounds((sizeX/2)-100, (sizeY/2)+212, 400, 150);
        menu.add(buttonS, new Integer(1));
        
        buttonJ.setIcon(new ImageIcon((new ImageIcon(button.getSubimage(240, 60, 200, 100))).getImage().getScaledInstance(400,150, Image.SCALE_DEFAULT)));
        buttonJ.setBounds((sizeX/2)-200, (sizeY/2), 400, 150);
        menu.add(buttonJ, new Integer(1));

        buttonE.setIcon(new ImageIcon((new ImageIcon(button.getSubimage(240, 60, 200, 100))).getImage().getScaledInstance(400,150, Image.SCALE_DEFAULT)));
        buttonE.setBounds((sizeX/2)-200, (sizeY/2)-212, 400, 150);
        menu.add(buttonE, new Integer(1));
        
        menu.paintImmediately((sizeX/2)-200, (sizeY/2)+212, 400, 150);
        menu.paintImmediately((sizeX/2)-200, (sizeY/2)-212, 400, 150);
        menu.paintImmediately((sizeX/2)-200, (sizeY/2), 400, 150);
        
    }
    
    public static boolean isPause(){
        return pause;
    }

    static Object getLock() {
        return lock;
    }
    
}
