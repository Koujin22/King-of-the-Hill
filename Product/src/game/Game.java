package game;

import static game.Menu.menu;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.concurrent.CyclicBarrier;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.OverlayLayout;



public class Game extends JPanel{

    
    
    JLabel pj;
    JProgressBar pb;
    JLayeredPane parent;
    
    public Game(JFrame wndw, JLayeredPane parent, JLayeredPane canvasL, JProgressBar pb, Object lock, boolean pause) throws InterruptedException, ClassNotFoundException{
        
        JFrame window = wndw;
        this.parent = parent;
        this.pb = pb;
        Rectangle load = new Rectangle(100,1000, 1720, 50);
        
        
        int IndexL;
        System.out.println("Iniciando...");
        Class.forName("game.Settings");
        pb.setValue(5);
        canvasL.paintImmediately(load);
        Thread.sleep(500);
        System.out.println("Iniciado.");
        System.out.println("Inicializando...");
        this.parent = parent;
        JLayeredPane canvas = Settings.getCANVAS();
        JLayeredPane canvas2 = Settings.getCANVAS_2();
        JLayeredPane canvas3 = Settings.getCANVAS_3();
        
        JLabel[][] background = Settings.getBACKGROUND();
        JLabel[][] background2 = Settings.getBACKGROUND_2();
        pj = Settings.getCHAR();
        JLabel LoS = Settings.getLoS();
        ImageIcon def = Settings.getR()[1];
        BufferedImage mapSrc = Settings.getMAP_SRC();
        int numSqX = Settings.getNUM_SQUARE_X();
        int numSqY = Settings.getNUM_SQUARE_Y();
        int size = Settings.getSIZE();
        int[][] map = Settings.getMAP();
        int[][] map2 = Settings.getMAP2();
        LayerManager LM = Settings.getLM();
        LayerManager LM2 = Settings.getLM2();
        DrawRect UI = Settings.getUI();
        menu = new JLayeredPane();
        parent.add(menu, new Integer(3));
        menu.setBounds(0,0,Settings.getSIZE_X(), Settings.getSIZE_Y());
        
        
        
        
        MediaPlayer MP = Settings.getMEDIA_PLAYER();
        MP.setOnEndOfMedia(new Runnable() {
            public void run(){
                MP.seek(Duration.ZERO);
            }
        });
        MP.play();
        
        
        
        pb.setValue(10);
        canvasL.paintImmediately(load);
        Thread.sleep(500);
        System.out.print("Creando ventana... ");
        parent.setLayout(new OverlayLayout(parent));
        parent.add(canvas2, new Integer(2));
        parent.add(canvas,new Integer(1));
        parent.add(canvas3, new Integer(3));
        window.add(parent);
        
        
        canvas.setDoubleBuffered(true);
        canvas2.setDoubleBuffered(true);
        canvas3.setDoubleBuffered(true);
        pb.setValue(15);
        canvasL.paintImmediately(load);
        Thread.sleep(500);
        System.out.println("Creada.");
        
        System.out.print("Generando Mapa... ");
        for (int x = 0, y = 0; y < numSqY; x++) {
            
            int n = map[y][x];
            background[x][y] = new JLabel(new ImageIcon(
                    mapSrc.getSubimage(size*(n%16),size*(n/16),size,size)));
            background[x][y].setBounds(x*size,y*size,size,size);
            canvas.add(background[x][y],new Integer(0));
            if(x == numSqX-1){
                y++;
                x=-1;
            }   
        }
        pb.setValue(30);
        canvasL.paintImmediately(load);
        Thread.sleep(500);
        for (int x = 0, y = 0; y < numSqY; x++) {
            
            int n = map2[y][x];
            if(x == numSqX-1){
                y++;
                x=-1;
            }            
            
            if (n == -1)
                continue;
            background2[x][y] = new JLabel(new ImageIcon(
                    mapSrc.getSubimage(size*(n%16),size*(n/16),size,size)));
            background2[x][y].setBounds(x*size,y*size,size,size);
            canvas.add(background2[x][y],new Integer(1));
        }
        pb.setValue(45);
        canvasL.paintImmediately(load);
        Thread.sleep(500);
        System.out.println("Generado.");
        
        System.out.print("Creando Personaje... ");
        
        pj.setIcon(def);
        pj.setBounds(PlySettings.getPosX(), PlySettings.getPosY(), 240, 580);
        pj.setName("Jugador");
        pb.setValue(50);
        canvasL.paintImmediately(load);
        Thread.sleep(500);
        canvas2.add(pj);
        LM.add(pj, "Player");
        
        System.out.println("Creado.");
        
        System.out.print("Visualizando ventana... ");
        pb.setValue(60);
        canvasL.paintImmediately(load);
        Thread.sleep(500);
        
        System.out.println("Visualizada.");
        
        CyclicBarrier cb = new CyclicBarrier(4, new Runnable(){
            @Override
            public void run(){
            }
        });
        CyclicBarrier cb2 = new CyclicBarrier(2, new Runnable(){
            @Override
            public void run(){
            }
        });
        CyclicBarrier cb3 = new CyclicBarrier(4, new Runnable(){
            @Override
            public void run(){
            }
        });
        
        System.out.print("Creando UI... ");
        UI.setBounds(200,900,250,250);
        canvas3.add(UI, new Integer(0));
        window.setVisible(true);
        pb.setValue(75);
        Thread.sleep(500);
        canvasL.paintImmediately(load);
        System.out.println("Creado");
        pb.setValue(100);
        Thread.sleep(500);
        parent.remove(canvasL);
        parent.revalidate();
        Thread w = new Thread(new Window(cb2,cb3, lock, pause));
        Thread PMan = new Thread(new PlyManager(cb,cb2,cb3));
        Thread PS = new Thread(new PlayerSkin(cb,cb3));
        Thread PM = new Thread(new PlyMovment(cb,cb3));
        Thread lastDir = new Thread(new LastDir());
        Thread lvl = new Thread(new Level());
        lvl.start();
        LM2.sendCB(cb, cb2);
        LM2.start();
        lastDir.start();
        PM.start();
        PS.start();
        PMan.start();
        w.start();
        
        
    }
    
    public JLabel getPj(){
        return pj;
    }
    
}
