package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
}
