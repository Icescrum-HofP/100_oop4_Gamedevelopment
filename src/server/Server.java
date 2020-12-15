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
            listofpos.add(new ArrayList<String>(25));
            accapt();
            output();
            parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        //System.out.println("started");
        while (true) {
            //System.out.println("---------------------------------------------------------------");
            if (listofpos.get(0).size() > 1) {
                for (int i = 0; i < listofpos.size(); i++) {
                    if (listofpos.get(i).size() > 2) {
                       // System.out.println(listofpos.get(i).toString());
                    }
                }
            }
        }
    }

    private void output() {
        new Thread(() -> {
            try {
                // System.out.println("thread output has started");
                while (true) {
                    for (int i = 0; i < clients.size(); i++) {
                        InputStream ini = clients.get(i).getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(ini));
                        String s = null;
                        while ((s = reader.readLine()) != null) {
                            if (listofpos.get(i).size() < 10) {
                                listofpos.get(i).add(s);
                                //    System.out.println("check ---> " + i);
                            } else {
                                int check = 5;
                                for (int j = (listofpos.get(i).size() - 5); j < listofpos.get(i).size(); j++) {
                                    if (listofpos.get(i).get(j).equals(s)) {
                                        check--;
                                        System.out.println("geh mir nd aufn sack -->" + j);
                                    }
                                }
                                if (check == 5) {
                                    listofpos.get(i).add(s);
                                    System.out.println("wurde geaddedt ");
                                    //     System.out.println(check + "--->" + i);
                                    //  System.out.println(s + " von der nr " + i);
                                }
                            }
                            break;
                        }
                    }
                    Thread.yield();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    //-------------------------------------------------------------------------------------------
    private void parse() {
        new Thread(() -> {
            try {
                while (true) {
                    if (clients.size() > 1) {
                        Thread.sleep(10);
                        // System.out.println(System.nanoTime());
                        for (int i = 0; i < clients.size(); i++) {
                            OutputStream out = clients.get(i).getOutputStream();
                            PrintWriter print = new PrintWriter(out);
                            String output = (clients.size() - 1) + ",";
                            //System.out.println(System.currentTimeMillis());
                            for (int j = 0; j < listofpos.size(); j++) {
                                if (i != j && listofpos.get(j).size() > 1) {
                                    //System.out.println(listofpos.get(j).size() + " ich bin im if");
                                    output += listofpos.get(j).get(0) + ",";
                                    listofpos.get(j).remove(0);
                                       System.out.println(output);
                                } else {
                                    // System.out.println("drausen");
                                }
                            }
                            print.write(output + "\n");
                            print.flush();
                            //  System.out.println(System.currentTimeMillis());
                        }
                    }
                    Thread.yield();
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    //---------------------------------------------------------------------------------------------
    private void accapt() {
        new Thread(() -> {
            try {
                //    System.out.println("hier");
                while (true) {
                    Socket clin;
                    if ((clin = server.accept()) != null) {
                        clients.add(clin);
                        listofpos.add(new ArrayList<String>(25));
                        listofpos.get(listofpos.size() - 1).add("0.0|0.0");
                        //  System.out.println("one client has joind");
                    }
                    Thread.yield();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
