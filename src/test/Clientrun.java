package test;

import screen.Screen;
import server.Client;
import sql.Sqlsrcipt;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Clientrun {


    public static void main(String[] args) {
        String name = "";
        boolean online = true;
        String ip = "";
        Sqlsrcipt questions = new Sqlsrcipt();
        boolean first = true;
        Client spiel;

        while (true) {
            Screen frame = new Screen(first);
//        System.out.println(frame.isStart());
            frame.setName(name);
            frame.setIp(ip);
            frame.setOnline(online);

            while (!frame.isStart()) {
                name = frame.getName();
                online = frame.isOnline();
                ip = frame.getIp();
//            System.out.println(online + " | "+name);
            }
            System.out.println("Name: " + name);
            System.out.println("Online: " + online);
            System.out.println("IP: " + ip);
            frame.setVisible(false);
            frame.dispose();

            if (questions.nextqestion()) {
                spiel = new Client(1000, 1540, online, name, ip);
                spiel.play();
                System.out.println("Client has started");
            }
            first = false;
        }


//        System.out.println(" client name");
//        Scanner in = new Scanner(System.in);
//        String input = in.next();
//        Client spiel = new Client(1000, 1540, true, input);
    }

}
