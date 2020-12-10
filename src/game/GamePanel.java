package game;

import entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    private long fps = 0;
    private long frames = 0;
    //---------Key---------
    private boolean up;
    private boolean down;
    private boolean right;
    private boolean left;
    //--------Players-----
    private ArrayList<Player> player;
    private ArrayList<Player> ennemy;

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
        player = new ArrayList<Player>();
        ennemy = new ArrayList<Player>();

        Timer fpsHelper = new Timer(1000, fpsTimer);
        fpsHelper.restart();
    }


    //FPS-----------------------------------------------------------------------
    private ActionListener fpsTimer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            fps = frames;
            frames = 0;
        }
    };


    // Player / Enemy update------------------------------------------------------------
    public void addPlayer(Player p) {
        player.add(p);
    }

    public void playerupdate(double speed) {
        player.get(0).move(up,down,right,left,speed);
    }

    public void enemyupdate(ArrayList<Player> in) {
        ennemy = in;
    }

    //Paint---------------------------------------------------------------------
    @Override
    public void paintComponent(Graphics g) {
        frames++;
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        player.get(0).paintComponent(g);
        for (int i = 0; i < ennemy.size(); i++) {
            ennemy.get(i).paintComponent(g);
        }
        g2.setColor(Color.red);
        g2.drawString("FPS: " + Long.toString(fps), 20, 10);

    }

    // GETTER ------------------------------------------
    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isLeft() {
        return left;
    }

    // Keylistener----------------------------------------------------------------------------
    private KeyListener e = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {


            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                up = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                left = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                right = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
                down = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(1);
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                up = false;
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                left = false;
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                down = false;
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                right = false;
            }

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            }

            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

            }
        }
    };


}

