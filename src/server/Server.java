package server;

import javax.naming.ldap.SortKey;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private ServerSocket server;
    private ArrayList<Socket> clients;
    private ArrayList<ArrayList<String>> listofpos;


    public Server() {

        try {
            clients = new ArrayList<Socket>();
            listofpos = new ArrayList<ArrayList<String>>();
            server = new ServerSocket(5556);
            Socket client;
            client = server.accept();
            clients.add(client);
            listofpos.add(new ArrayList<String>());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        accapt();
        output();
        parse();
    }

    private void output() {
        new Thread(() -> {
            try {
                System.out.println("thread output has started");
                while (true) {
                    for (int i = 0; i < clients.size(); i++) {
                        InputStream ini = clients.get(i).getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(ini));
                        String s = null;
                        while ((s = reader.readLine()) != null) {
                            listofpos.get(i).add(s);
                            System.out.println(s + " von der nr " + i);
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }


    private void parse() {
        new Thread(() -> {
            try {
                //System.out.println(clients.size() + " client size");
                while (true) {
                    if (clients.size() > 1) {
                        for (int i = 0; i < clients.size(); i++) {
                            System.out.println(" client nr : " + i);
                            OutputStream out = clients.get(i).getOutputStream();
                            PrintWriter print = new PrintWriter(out);
                            String output = (clients.size() - 1) + "|";
                            for (int j = 0; j < listofpos.size(); j++) {
                                //not the own pos

                                if (i != j) {
                                    System.out.println(listofpos.get(j).size() + " ich bin im if");
                                    output += listofpos.get(j).get(0) + "|";
                                    listofpos.get(j).remove(0);
                                } else {
                                    System.out.println("auserhalb :" + j);
                                }
                            }
                            print.write(output + "\n");
                            print.flush();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void accapt() {
        new Thread(() -> {
            try {
                System.out.println("hier");
                while (true) {
                    Socket clin;
                    if ((clin = server.accept()) != null) {
                        clients.add(clin);
                        listofpos.add(new ArrayList<String>());
                        listofpos.get(listofpos.size() - 1).add("0.0|0.0");
                        System.out.println("one client has joind");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


}
