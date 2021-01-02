package game;

import entities.Bullet;
import entities.Enenmy;
import entities.Player;
import input.settings.Maplist;
import world.Pos;
import world.input.Mapin;

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
    //------Entities------
    private Player player;
    private ArrayList<Enenmy> ennemy;
    private ArrayList<Bullet> bullets;
    //------Map-----------
    private Mapin map;
    private Maplist maplist;

    public GamePanel(int h, int w) {
        this.setPreferredSize(new Dimension(w, h));
        JFrame frame = new JFrame("First Game");
        frame.setLocation(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.addMouseListener(mouseListener);
        frame.addKeyListener(e);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        maplist = new Maplist();
        map = new Mapin(maplist.getMapList()[0]);
        ennemy = new ArrayList<Enenmy>();
        bullets = new ArrayList<Bullet>();
        bulletcheck();
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


    // Player / Enemy / Bullet update------------------------------------------------------------
    public void addPlayer(Player p) {
        player = p;
    }

    public void playerupdate(double speed) {
        player.move(up, down, right, left, speed);
    }

    public void enemyupdate(ArrayList<Enenmy> in) {
        ennemy = in;
    }

    //Paint---------------------------------------------------------------------
    @Override
    public void paintComponent(Graphics g) {
        frames++;
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        map.drawMap(g2);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paintComponent(g2);
        }

        for (int i = 0; i < ennemy.size(); i++) {
            ennemy.get(i).paintComponent(g2);
        }
        player.paintComponent(g2);

        g2.setColor(Color.red);
        g2.drawString("FPS: " + Long.toString(fps), 20, 10);
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

    private MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            double x = e.getX();
            double y = e.getY();
            Pos p = new Pos(x, y);
            Bullet bullet = new Bullet(player, p);
            bullets.add(bullet);
            try {
                Thread.sleep(50);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
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
    };

    // Threads--------------------------------------------------------------------------------

    public void bulletcheck() {
        new Thread(() -> {
            while (true) {
                for (int i = 0; i < bullets.size(); i++) {
                    if (bullets.get(i).hasfinished()) {
                        bullets.remove(i);
                    }
                }
                Thread.yield();
            }
        }).start();
    }

}

