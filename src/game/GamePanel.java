package game;

import drawing.Drawing;
import world.Player;
import world.Postition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Arc2D;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    private long fps = 0;
    private long frames = 0;
    private String keypressd = "";
    private ArrayList<Player> player;

    public GamePanel(int h, int w) {
        this.setPreferredSize(new Dimension(w, h));
        JFrame frame = new JFrame("First Game");
        frame.setLocation(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.addKeyListener(e);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        doInitialisations();
        player = new ArrayList<Player>();


        Timer fpsHelper = new Timer(1000, fpsTimer);
        fpsHelper.restart();

    }

    private void doInitialisations() {


    }

    private ActionListener fpsTimer = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            fps = frames;
            frames = 0;

        }
    };

    public void addPlayer(Player p){
        player.add(p);
    }

    public void playerupdate(Player p){
        player.get(0).setppos(p);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        frames++;
        g2.drawRect((int)player.get(0).getX(),(int)player.get(0).getY() , 40, 40);

        g2.setColor(Color.red);
        g2.drawString("FPS: " + Long.toString(fps), 20, 10);

    }

    private KeyListener e = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {


            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                keypressd = "up";
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                keypressd = "left";
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                keypressd = "right";
            }

            if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
                keypressd = "down";
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                keypressd = "";
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                keypressd = "";
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                keypressd = "";
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                keypressd = "";
            }

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                keypressd = "";
            }

            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                keypressd = "";
            }
        }
    };

    public void setKeypressdnull() {
        this.keypressd = "";
    }

    public String getKeypressd() {
        return keypressd;
    }
}

