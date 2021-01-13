package game;

import entities.Bullet;
import entities.Enenmy;
import entities.Player;
import input.settings.Maplist;
import world.HitBox;
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
    private boolean escape;
    //------Entities------
    private Player player;
    private ArrayList<Enenmy> ennemy;
    private ArrayList<Bullet> bullets;
    private ArrayList<HitBox> objects;
    //------Map-----------
    private Mapin map;
    private Maplist maplist;
    private boolean dead;
    JFrame frame;

    public GamePanel(int h, int w) {
        maplist = new Maplist();
        map = new Mapin(maplist.getMapList()[0]);
        ennemy = new ArrayList<Enenmy>();
        bullets = new ArrayList<Bullet>();
        objects = map.getObjects();
        this.setPreferredSize(new Dimension(w, h));
        frame = new JFrame("First Game");
        frame.setLocation(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setResizable(false);
        frame.addMouseListener(mouseListener);
        frame.addKeyListener(e);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        Timer fpsHelper = new Timer(1000, fpsTimer);
        fpsHelper.restart();
        dead = false;
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

    public void checkcollide() {

        //  System.out.println(objects.size());

        bulletcheck();

//        for (Bullet s : bullets) {
//            if (s.check(player)) {
//                player.gethit();
//            }
//        }

        for (Enenmy s : ennemy) {
            int counter = 0;
            for (Bullet b : s.getBullets()) {
                if (counter != 0) {
                    if (!b.checks(s)) {
                        if (b.checks(player)) {
                            player.gethit();
                        }
                    }
                }
                counter++;
            }
        }


        for (HitBox y : objects) {
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).check(y)) {
                    bullets.remove(i);
                }
            }
        }

        for (HitBox s : objects) {
            if (s.check(player)) {
                player.setppos(player.getPositionbevor());
            }
        }

        player.setPositionbevor(player.getPos());
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    //Paint---------------------------------------------------------------------
    @Override
    public void paintComponent(Graphics g) {
        frames++;
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        map.drawMap(g2);

//        for (HitBox s : objects) {
//            g2.drawRect((int) s.getX(), (int) s.getY(), (int) s.getW(), (int) s.getH());
//        }

        for (Bullet s : bullets) {
            s.paintComponent(g2);
        }

        for (Enenmy s : ennemy) {
            int counter = 0;
            for (Bullet b : s.getBullets()) {
                if (counter != 0) {
                    b.paintComponent(g2);
                }
                counter++;
            }
            s.paintComponent(g2);
        }

        player.paintComponent(g2);

        g2.setColor(Color.red);
        g2.drawString("FPS: " + Long.toString(fps), 20, 10);

        if (dead) {
            g2.setFont(g2.getFont().deriveFont(50f));
            g2.setColor(Color.RED);
            g2.drawString("YOU ARE DEAD\n GAME OVER", 300, 500);
        }

        if (escape) {
            g2.setFont(g2.getFont().deriveFont(50f));
            g2.setColor(Color.RED);
            g2.drawString("YOU HAVE EXITED\n GAME OVER", 300, 500);
        }

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
                escape = true;
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
                Thread.sleep(10);
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

    // functions--------------------------------------------------------------------------------

    public void bulletcheck() {
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).hasfinished()) {
                bullets.remove(i);
            }
        }
    }

    public boolean isEscape() {
        return escape;
    }

    public boolean isDeath() {
        return player.isDeath();
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void close() {
        frame.dispose();
    }

    public JFrame getFrame() {
        return frame;
    }

    //    public void bulletout() {
//        new Thread(() -> {
//            while (true) {
//                String msg = "Bullets: -->";
//                for (int i = 0; i < bullets.size(); i++) {
//                    msg += bullets.get(i).toString();
//                }
//                System.out.println(msg);
//                Thread.yield();
//            }
//        }).start();
//    }

}

