package test;

import screen.Screen;
import server.Client;
import sql.Sqlsrcipt;

public class Clientosql {


    public static void main(String[] args) {
        String name = "";
        boolean online = false;
        Sqlsrcipt questions = new Sqlsrcipt();

        Screen frame = new Screen();
//        System.out.println(frame.isStart());

        while (!frame.isStart()) {
            name = frame.getName();
            online = frame.isOnline();
//            System.out.println(online + " | "+name);
        }

        System.out.println("Name: " + name);
        System.out.println("Online: " + online);

        frame.setVisible(false);
        frame.dispose();
        Client spiel = new Client(1000, 1540, online, name);
    }

}
