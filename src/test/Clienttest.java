package test;

import server.Clientnew;

import java.util.Scanner;

public class Clienttest {


    public static void main(String[] args) {

        System.out.println(" client name");
        Scanner in = new Scanner(System.in);
        String input = in.next();


        Clientnew spiel= new Clientnew(500,800,true,input);
    }

}
