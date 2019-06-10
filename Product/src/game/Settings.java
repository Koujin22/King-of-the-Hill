package game;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

public final class Settings{
    
    //clase que guarda la mayoria de las variables globales del juego. 
    
    // <editor-fold defaultstate="collapsed" desc="Privat Final Static">
    private static final int[][]MAP = {
            {17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17},
            {17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17},
            {17,17,48,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,33,49,17,17},
            {17,17,18,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,177,177,177,177,6,7,7,7,8,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,177,177,177,177,22,23,23,23,70,8,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,177,177,177,177,22,23,54,55,23,24,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,177,177,177,177,22,23,70,71,23,70,8,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,177,177,177,177,38,55,23,23,23,23,24,177,177,177,177,177,86,87,87,87,87,87,87,87,87,87,87,88,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,177,177,177,177,177,38,39,55,23,23,24,177,177,177,177,86,151,103,103,103,103,103,103,103,103,103,103,150,88,177,177,177,177,177,177,177,177,89,90,90,90,90,90,91,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,177,177,177,177,177,177,177,38,39,39,40,177,177,177,86,151,103,103,103,103,134,135,103,103,134,135,103,103,150,88,177,177,177,177,177,177,177,105,26,26,26,26,26,107,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,102,103,103,103,103,103,150,151,103,103,150,151,103,103,103,104,177,177,177,177,177,177,177,105,26,57,58,26,26,107,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,102,103,103,103,103,103,103,103,103,103,103,103,103,103,103,104,177,177,177,177,177,177,177,105,26,73,74,26,26,107,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,102,103,103,103,103,103,103,103,134,135,103,134,135,103,103,150,88,177,177,177,177,177,177,105,26,26,57,58,26,107,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,102,103,103,103,103,134,135,103,150,151,103,150,151,103,103,103,104,177,177,177,177,177,177,105,26,26,73,74,26,107,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,118,135,134,135,103,150,151,103,103,103,103,103,134,135,103,134,120,177,177,177,177,177,177,121,122,122,122,122,122,123,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,102,150,151,103,103,103,103,103,103,103,103,150,151,103,104,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,118,135,103,103,103,134,135,103,103,103,103,103,103,134,120,177,177,177,177,177,177,177,86,87,87,88,177,177,177,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,118,135,103,103,150,151,103,103,103,103,103,134,120,177,177,177,177,177,177,177,86,151,103,103,150,87,88,177,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,177,86,87,87,87,88,177,177,177,177,177,177,177,177,177,177,177,118,119,119,119,119,119,119,119,119,119,120,177,177,177,177,177,177,177,177,102,103,103,103,103,103,150,88,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,177,102,103,103,103,150,88,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,86,87,87,151,103,103,103,103,134,135,104,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,177,102,103,103,103,103,150,88,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,102,103,103,103,134,135,103,103,150,151,104,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,177,118,119,135,103,103,103,104,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,102,103,103,103,150,151,103,103,103,103,104,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,177,86,87,87,151,103,103,103,104,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,102,103,103,103,103,103,103,103,134,119,120,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,86,151,103,103,103,103,103,134,120,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,118,119,119,135,103,103,103,103,150,88,177,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,102,103,103,103,103,103,103,104,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,118,119,119,135,103,103,104,177,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,18,177,118,119,119,119,119,119,119,120,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,177,118,119,119,120,177,177,177,177,177,177,177,177,177,16,17,17},
            {17,17,64,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,4,4,65,17,17},
            {17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17},
            {17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17},
            {17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17,17}
            };
    private static final int[][] MAP2 = {{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,12,13,14,15,14,15,14,15,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,167,168,-1,-1,-1,124,125,126,127,124,125,126,127,126,137,138,137,138,28,29,30,31,30,31,30,31,12,13,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,183,184,164,164,164,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,167,168,-1,-1,-1,156,157,158,159,156,157,158,159,158,153,154,153,154,44,45,46,47,46,47,46,47,28,29,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,199,200,164,164,165,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,155,-1,-1,-1,-1,-1,167,168,-1,-1,-1,124,125,126,127,124,125,126,127,126,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,12,13,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,183,184,164,165,165,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,167,168,-1,-1,-1,156,157,158,159,156,157,158,159,158,-1,-1,-1,60,61,62,-1,-1,60,61,62,-1,28,29,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,199,200,165,165,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,172,173,174,175,172,173,174,175,175,-1,-1,95,76,77,78,95,95,76,77,78,-1,44,45,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,111,92,93,94,111,111,92,93,94,-1,137,138,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,108,109,110,-1,-1,108,109,110,-1,153,154,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,155,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,155,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,155,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,155,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,155,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}};
    private final static int SIZE = 32;
    private final static int FPS = 70;
    private final static int SIZE_X = 1920;
    private final static int SIZE_Y = 1056;
    private final static int NUM_SQUARE_X = SIZE_X / SIZE;
    private final static int NUM_SQUARE_Y = SIZE_Y / SIZE;
    private final static Random RND = new Random();
    private final static JFrame WINDOW = new JFrame("Game");
    private final static JLayeredPane CANVAS  = new JLayeredPane();
    private final static JLayeredPane CANVAS_2  = new JLayeredPane();
    private final static JLayeredPane CANVAS_3 = new JLayeredPane();
    private final static JLabel BACKGROUND[][] = new JLabel[NUM_SQUARE_X][NUM_SQUARE_Y];
    private final static JLabel BACKGROUND_2[][] = new JLabel[NUM_SQUARE_X][NUM_SQUARE_Y];
    private final static JLabel CHAR = new JLabel();
    private final static JLabel LoS = new JLabel();
    private final static String SOUND = "Ouroboros.mp3";
    private final static String PATH_S = "src/Resources/Sound/";
    private final static String PATH = "src/Resources/Imges/";
    private final static ImageIcon[] R, L, U, D, ER, EL;
    private final static LayerManager LM = new LayerManager();
    private final static LayerManager LM2 = new LayerManager();
    private final static DrawRect UI = new DrawRect();
    private final static ArrayList<Enemy> DBE = new ArrayList();


    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Privat Static">
    
    private static BufferedImage MAP_SRC;
    private static BufferedImage[] SKINS_SRC;
    private static MediaPlayer MEDIA_PLAYER;
    private static Media MEDIA_S;
    private static ImageIcon PlySkin;
    
            
    // </editor-fold>
    
    static{
        System.out.print("Cargando Imagenes y Sonido... ");
        
        try {
            final CountDownLatch latch;
            latch = new CountDownLatch(1);
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new JFXPanel(); // initializes JavaFX environment
                    latch.countDown();
                }
            });
            latch.await();
            
            
            MEDIA_S  = new Media(new File(PATH_S+SOUND).toURI().toString());
            MEDIA_PLAYER = new MediaPlayer(MEDIA_S);
            MAP_SRC = ImageIO.read(new File(PATH+"map1.png"));
            SKINS_SRC = new BufferedImage[]{ImageIO.read(new File(PATH+"R.png")),
                                            ImageIO.read(new File(PATH+"L.png")),
                                            ImageIO.read(new File(PATH+"U.png")),
                                            ImageIO.read(new File(PATH+"D.png")),
                                            ImageIO.read(new File(PATH+"SIR.png")),
                                            ImageIO.read(new File(PATH+"SR_Atk.png")),
                                            ImageIO.read(new File(PATH+"SIL.png")),
                                            ImageIO.read(new File(PATH+"SL_Atk.png"))};
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception e) {
            System.out.println("Error aqui");
            e.printStackTrace();
        }
        
        ER = new ImageIcon[]{new ImageIcon(SKINS_SRC[4].getSubimage(0, 0, 192, 192)),
                            new ImageIcon(SKINS_SRC[4].getSubimage(192, 0, 192, 192)),
                            new ImageIcon(SKINS_SRC[4].getSubimage(384, 0, 192, 192)),
                            new ImageIcon(SKINS_SRC[4].getSubimage(576, 0, 192, 192)),
                            new ImageIcon(SKINS_SRC[4].getSubimage(768, 0, 192, 192)),
                            new ImageIcon(SKINS_SRC[4].getSubimage(384, 192, 192, 192)),
                            new ImageIcon(SKINS_SRC[4].getSubimage(576, 192, 192, 192)),
                            new ImageIcon(SKINS_SRC[4].getSubimage(768, 192, 192, 192)),
                            
                            
                            new ImageIcon(SKINS_SRC[4].getSubimage(0, 384, 192, 192)),
                            new ImageIcon(SKINS_SRC[4].getSubimage(192, 384, 192, 192)),
                            new ImageIcon(SKINS_SRC[4].getSubimage(384, 384, 192, 192)),
                            new ImageIcon(SKINS_SRC[4].getSubimage(576, 384, 192, 192)),
                            new ImageIcon(SKINS_SRC[4].getSubimage(768, 384, 192, 192)),
                            new ImageIcon(SKINS_SRC[4].getSubimage(0, 576, 192, 192)),
                            new ImageIcon(SKINS_SRC[4].getSubimage(192, 576, 192, 192)),
                            new ImageIcon(SKINS_SRC[4].getSubimage(384, 576, 192, 192)),
                            new ImageIcon(SKINS_SRC[4].getSubimage(576, 576, 192, 192)),
                            new ImageIcon(SKINS_SRC[4].getSubimage(768, 576, 192, 192)),
                            new ImageIcon(SKINS_SRC[4].getSubimage(576, 768, 192, 192)),
                            new ImageIcon(SKINS_SRC[4].getSubimage(768, 768, 192, 192)),
                            
                            new ImageIcon(SKINS_SRC[5].getSubimage(0, 0, 296, 216)),
                            new ImageIcon(SKINS_SRC[5].getSubimage(296, 0, 296, 216)),
                            new ImageIcon(SKINS_SRC[5].getSubimage(592, 0, 296, 216)),
                            new ImageIcon(SKINS_SRC[5].getSubimage(0, 216, 296, 216)),
                            new ImageIcon(SKINS_SRC[5].getSubimage(296, 216, 296, 216)),
                            new ImageIcon(SKINS_SRC[5].getSubimage(592, 216, 296, 216)),
                            new ImageIcon(SKINS_SRC[5].getSubimage(0, 432, 296, 216)),
                            new ImageIcon(SKINS_SRC[5].getSubimage(296, 432, 296, 216)),
                            new ImageIcon(SKINS_SRC[5].getSubimage(592, 432, 296, 216)),
                            new ImageIcon(SKINS_SRC[5].getSubimage(0, 648, 296, 216)),
                            new ImageIcon(SKINS_SRC[5].getSubimage(296, 648, 296, 216)),
                            new ImageIcon(SKINS_SRC[5].getSubimage(592, 648, 296, 216)),
                            new ImageIcon(SKINS_SRC[5].getSubimage(296, 864, 296, 216)),
                            new ImageIcon(SKINS_SRC[5].getSubimage(592, 864, 296, 216))};
        
        EL = new ImageIcon[]{new ImageIcon(SKINS_SRC[6].getSubimage(0, 0, 192, 192)),
                            new ImageIcon(SKINS_SRC[6].getSubimage(192, 0, 192, 192)),
                            new ImageIcon(SKINS_SRC[6].getSubimage(384, 0, 192, 192)),
                            new ImageIcon(SKINS_SRC[6].getSubimage(576, 0, 192, 192)),
                            new ImageIcon(SKINS_SRC[6].getSubimage(768, 0, 192, 192)),
                            new ImageIcon(SKINS_SRC[6].getSubimage(0, 192, 192, 192)),
                            new ImageIcon(SKINS_SRC[6].getSubimage(192, 192, 192, 192)),
                            new ImageIcon(SKINS_SRC[6].getSubimage(384, 192, 192, 192)),
                            
                            
                            new ImageIcon(SKINS_SRC[6].getSubimage(0, 384, 192, 192)),
                            new ImageIcon(SKINS_SRC[6].getSubimage(192, 384, 192, 192)),
                            new ImageIcon(SKINS_SRC[6].getSubimage(384, 384, 192, 192)),
                            new ImageIcon(SKINS_SRC[6].getSubimage(576, 384, 192, 192)),
                            new ImageIcon(SKINS_SRC[6].getSubimage(768, 384, 192, 192)),
                            new ImageIcon(SKINS_SRC[6].getSubimage(0, 576, 192, 192)),
                            new ImageIcon(SKINS_SRC[6].getSubimage(192, 576, 192, 192)),
                            new ImageIcon(SKINS_SRC[6].getSubimage(384, 576, 192, 192)),
                            new ImageIcon(SKINS_SRC[6].getSubimage(576, 576, 192, 192)),
                            new ImageIcon(SKINS_SRC[6].getSubimage(768, 576, 192, 192)),
                            new ImageIcon(SKINS_SRC[6].getSubimage(0, 768, 192, 192)),
                            new ImageIcon(SKINS_SRC[6].getSubimage(192, 768, 192, 192)),
                            
                            new ImageIcon(SKINS_SRC[7].getSubimage(0, 0, 296, 216)),
                            new ImageIcon(SKINS_SRC[7].getSubimage(296, 0, 296, 216)),
                            new ImageIcon(SKINS_SRC[7].getSubimage(592, 0, 296, 216)),
                            new ImageIcon(SKINS_SRC[7].getSubimage(0, 216, 296, 216)),
                            new ImageIcon(SKINS_SRC[7].getSubimage(296, 216, 296, 216)),
                            new ImageIcon(SKINS_SRC[7].getSubimage(592, 216, 296, 216)),
                            new ImageIcon(SKINS_SRC[7].getSubimage(0, 432, 296, 216)),
                            new ImageIcon(SKINS_SRC[7].getSubimage(296, 432, 296, 216)),
                            new ImageIcon(SKINS_SRC[7].getSubimage(592, 432, 296, 216)),
                            new ImageIcon(SKINS_SRC[7].getSubimage(0, 648, 296, 216)),
                            new ImageIcon(SKINS_SRC[7].getSubimage(296, 648, 296, 216)),
                            new ImageIcon(SKINS_SRC[7].getSubimage(592, 648, 296, 216)),
                            new ImageIcon(SKINS_SRC[7].getSubimage(0, 864, 296, 216)),
                            new ImageIcon(SKINS_SRC[7].getSubimage(296, 864, 296, 216))};
        
        R = new ImageIcon[]{new ImageIcon(SKINS_SRC[0].getSubimage(192, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(224, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(256, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(288, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(192, 32, 32, 32)),

                            new ImageIcon(SKINS_SRC[0].getSubimage(224, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(256, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(288, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(192, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(224, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(256, 64, 32, 32)),

                            new ImageIcon(SKINS_SRC[0].getSubimage(0, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(32, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(64, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(96, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(128, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(160, 0, 32, 32)),

                            new ImageIcon(SKINS_SRC[0].getSubimage(0, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(32, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(64, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(96, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(128, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(160, 32, 32, 32)),

                            new ImageIcon(SKINS_SRC[0].getSubimage(0, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(32, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(64, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(96, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(128, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(160, 64, 32, 32)),

                            new ImageIcon(SKINS_SRC[0].getSubimage(0, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(32, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(64, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(96, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(128, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(160, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(192, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(224, 96, 32, 32)),

                            new ImageIcon(SKINS_SRC[0].getSubimage(0, 128, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(32, 128, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(64, 128, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(96, 128, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(128, 128, 32, 32)),
                            new ImageIcon(SKINS_SRC[0].getSubimage(160, 128, 32, 32))};
                                    
        L = new ImageIcon[]{new ImageIcon(SKINS_SRC[1].getSubimage(192, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(224, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(256, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(288, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(192, 32, 32, 32)),

                            new ImageIcon(SKINS_SRC[1].getSubimage(224, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(256, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(288, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(192, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(224, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(256, 64, 32, 32)),

                            new ImageIcon(SKINS_SRC[1].getSubimage(0, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(32, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(64, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(96, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(128, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(160, 0, 32, 32)),

                            new ImageIcon(SKINS_SRC[1].getSubimage(0, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(32, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(64, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(96, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(128, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(160, 32, 32, 32)),

                            new ImageIcon(SKINS_SRC[1].getSubimage(0, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(32, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(64, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(96, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(128, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(160, 64, 32, 32)),
                            
                            new ImageIcon(SKINS_SRC[1].getSubimage(0, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(32, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(64, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(96, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(128, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(160, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(192, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(224, 96, 32, 32)),

                            new ImageIcon(SKINS_SRC[1].getSubimage(0, 128, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(32, 128, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(64, 128, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(96, 128, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(128, 128, 32, 32)),
                            new ImageIcon(SKINS_SRC[1].getSubimage(160, 128, 32, 32))};  
        
        U = new ImageIcon[]{new ImageIcon(SKINS_SRC[2].getSubimage(192, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(224, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(256, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(288, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(194, 32, 32, 32)),

                            new ImageIcon(SKINS_SRC[2].getSubimage(226, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(258, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(290, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(192, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(224, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(256, 64, 32, 32)),

                            new ImageIcon(SKINS_SRC[2].getSubimage(0, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(32, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(64, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(96, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(128, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(160, 0, 32, 32)),

                            new ImageIcon(SKINS_SRC[2].getSubimage(0, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(32, 32, 34, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(66, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(98, 32, 34, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(130, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(162, 32, 32, 32)),

                            new ImageIcon(SKINS_SRC[2].getSubimage(0, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(32, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(64, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(96, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(128, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(160, 64, 32, 32)),
                            
                            new ImageIcon(SKINS_SRC[2].getSubimage(0, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(32, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(64, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(96, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(128, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(160, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(192, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(224, 96, 32, 32)),

                            new ImageIcon(SKINS_SRC[2].getSubimage(0, 128, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(32, 128, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(64, 128, 34, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(98, 128, 36, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(133, 128, 32, 32)),
                            new ImageIcon(SKINS_SRC[2].getSubimage(165, 128, 32, 32))};
        
        D = new ImageIcon[]{new ImageIcon(SKINS_SRC[3].getSubimage(192, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(224, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(256, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(288, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(192, 32, 32, 32)),

                            new ImageIcon(SKINS_SRC[3].getSubimage(224, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(256, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(288, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(192, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(224, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(256, 64, 32, 32)),

                            new ImageIcon(SKINS_SRC[3].getSubimage(0, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(32, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(64, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(96, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(128, 0, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(160, 0, 32, 32)),

                            new ImageIcon(SKINS_SRC[3].getSubimage(0, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(32, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(64, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(96, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(128, 32, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(160, 32, 32, 32)),
                            
                            new ImageIcon(SKINS_SRC[3].getSubimage(0, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(32, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(64, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(96, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(128, 64, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(160, 64, 32, 32)),

                            new ImageIcon(SKINS_SRC[3].getSubimage(0, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(32, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(64, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(96, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(128, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(160, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(192, 96, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(224, 96, 32, 32)),

                            new ImageIcon(SKINS_SRC[3].getSubimage(0, 128, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(32, 128, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(64, 128, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(96, 128, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(128, 128, 32, 32)),
                            new ImageIcon(SKINS_SRC[3].getSubimage(160, 128, 32, 32))};
        
        for (int i = 0; i < R.length; i++) {
            R[i] = new ImageIcon(R[i].getImage().getScaledInstance(R[i].getIconWidth()*4,R[i].getIconHeight()*5, Image.SCALE_DEFAULT));
            L[i] = new ImageIcon(L[i].getImage().getScaledInstance(L[i].getIconWidth()*4,L[i].getIconHeight()*5, Image.SCALE_DEFAULT)); 
            U[i] = new ImageIcon(U[i].getImage().getScaledInstance(U[i].getIconWidth()*4,U[i].getIconHeight()*5, Image.SCALE_DEFAULT)); 
            D[i] = new ImageIcon(D[i].getImage().getScaledInstance(D[i].getIconWidth()*4,D[i].getIconHeight()*5, Image.SCALE_DEFAULT));
        }
        System.out.println("Cargadas.");
}
    
    
    public static LayerManager getLM(){
        return LM;
    }
    public static LayerManager getLM2(){
        return LM2;
    }
    
    public static int[][] getMAP() {
        return MAP;
    }

    public static int[][] getMAP2() {
        return MAP2;
    }

    public static int getSIZE() {
        return SIZE;
    }

    public static int getFPS() {
        return FPS;
    }

    public static int getSIZE_X() {
        return SIZE_X;
    }

    public static int getSIZE_Y() {
        return SIZE_Y;
    }

    public static int getNUM_SQUARE_X() {
        return NUM_SQUARE_X;
    }

    public static int getNUM_SQUARE_Y() {
        return NUM_SQUARE_Y;
    }

    public static Random getRND() {
        return RND;
    }

    public static JFrame getWINDOW() {
        return WINDOW;
    }

    public static JLayeredPane getCANVAS() {
        return CANVAS;
    }
    
    public static JLayeredPane getCANVAS_2() {
        return CANVAS_2;
    }

    public static JLabel[][] getBACKGROUND() {
        return BACKGROUND;
    }

    public static JLabel[][] getBACKGROUND_2() {
        return BACKGROUND_2;
    }

    public static JLabel getCHAR() {
        return CHAR;
    }

    public static String getSOUND() {
        return SOUND;
    }

    public static Media getMEDIA_S() {
        return MEDIA_S;
    }

    public static MediaPlayer getMEDIA_PLAYER() {
        return MEDIA_PLAYER;
    }

    public static ImageIcon[] getR() {
        return R;
    }

    public static ImageIcon[] getL() {
        return L;
    }

    public static ImageIcon[] getU() {
        return U;
    }

    public static ImageIcon[] getD() {
        return D;
    }
    
    public static ImageIcon[] getER() {
        return ER;
    }

    public static ImageIcon[] getEL() {
        return EL;
    }

    
    public static BufferedImage getMAP_SRC() {
        return MAP_SRC;
    }

    public static BufferedImage[] getSKINS_SRC() {
        return SKINS_SRC;
    }

    public static ImageIcon getPlySkin() {
        return PlySkin;
    }

    public static void setPlySkin(ImageIcon PlySkin) {
        Settings.PlySkin = PlySkin;
    }

    public static JLabel getLoS() {
        return LoS;
    }

    static JLayeredPane getCANVAS_3() {
        return CANVAS_3;
    }

    static DrawRect getUI() {
        return UI;
    }
    
    public static void addDBE(Enemy e){
        DBE.add(e);
    }
    
    public static ArrayList<Enemy> getDBE(){
        return DBE;
    }

    private Settings(){
        
    }
    
}
