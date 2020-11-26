package game;

import drawing.Drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {

    long fps =0;
    long frames = 0;

    public GamePanel (int h, int w){
     this.setPreferredSize(new Dimension(w,h));
     JFrame frame = new JFrame("First Game");
     frame.setLocation(100,100);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.add(this);
     frame.pack();
     frame.setVisible(true);
     frame.setLocationRelativeTo(null);
     doInitialisations();

     Timer fpsHelper = new Timer(1000, fpsTimer);
     fpsHelper.restart();

    }

    private void doInitialisations(){






    }



    private ActionListener fpsTimer = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            fps = frames;
            frames = 0;

        }
    };

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
		/*g2.translate(this.getWidth()/2, this.getHeight()/2);
		winkle = winkle +0.1;
		g2.rotate(winkle);*/


        frames++;
        g2.drawRect( 100, 100, 40, 40);

        g2.setColor(Color.red);
        g2.drawString("FPS: " + Long.toString(fps), 20, 10);
      //  g2.drawString("Points: " + Long.toString(time), getWidth()-60, 10);




        }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {

        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {

        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {

        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {

        }
    }

    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {

        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {

        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {

        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {

        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

        }

    }

    public void keyTyped(KeyEvent e) {

    }
    }

