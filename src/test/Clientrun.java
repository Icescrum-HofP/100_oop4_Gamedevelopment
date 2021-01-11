package test;

import screen.Screen;
import server.Client;
import sql.Sqlsrcipt;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Clientrun {


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

        if (questions.nextqestion()) {
            Client spiel = new Client(1000, 1540, online, name);
            System.out.println("Client has started");
        }

//        System.out.println(" client name");
//        Scanner in = new Scanner(System.in);
//        String input = in.next();
//        Client spiel = new Client(1000, 1540, true, input);
    }

}
