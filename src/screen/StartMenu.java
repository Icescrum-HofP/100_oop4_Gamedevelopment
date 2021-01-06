package screen;

import javax.swing.*;
import java.awt.*;

public class StartMenu extends JPanel {

    JFrame frame;
    JButton client;
    JButton server;

    public StartMenu (){
        this.setPreferredSize(new Dimension(400, 800));
        frame = new JFrame("Menu");
        client = new JButton("Client");
        server = new JButton("Server");
        client.setSize(100,100);

        frame.setLocation(100,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.add(this);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(client);


    }


    public static void main(String[] args) {
        StartMenu start =new StartMenu();
    }

}
