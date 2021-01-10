package server;


import server.packet.Packets;
import server.packet.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servernew {

    //number of player~name;id;Direction;pos;bulletsize;bullets~name;id;Direction;pos;bulletsize;bullets

    private ArrayList<User> users;
    private ServerSocket server;

    public Servernew() {
        try {
            server = new ServerSocket(5555);
            users = new ArrayList<User>();
            System.out.println("Server von HofP\n"+"--------------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        connect();
        recivedefauld();
        senddefauld();
        System.out.println("Server has started");
    }

    private void recivedefauld() {
        new Thread(() -> {
            System.out.println("reciver has started");
            try {
                while (true) {
                    ArrayList<Integer> ids = new ArrayList<Integer>();
                    for (int i = 0; i < users.size(); i++) {
                        InputStream ini = users.get(i).getSocket().getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(ini));
                        String s = null;
                        while ((s = reader.readLine()) != null) {
                            if (s.length() > 15) {
                                String[] array = s.split(";");
                                users.get(i).addBullet(array[5]);
                                users.get(i).setDirection(array[2]);
                                users.get(i).addnewPos(array[3]);
                                break;
                            }
                            if (s.length() < 10) {
                                String[] array = s.split(";");
                                if (array[0].equals("leave")) {
                                    ids.add(users.get(i).getId());
//                                    System.out.println(users.get(i).getId()+ "-------> is going to leave"+ids.size());
                                    break;
                                }
                            }
                        }
                    }
//                    System.out.println(ids.size());
                    for (int i = 0; i < ids.size(); i++) {
                        int index = 99;
//                        System.out.println(index);
                        for (int j = 0; j < users.size(); j++) {

                            if (users.get(j).getId() == ids.get(i)) {
                                index = j;
//                                System.out.println("index ="+ index);
                            }
                        }
                        if (index != 99) {
                            System.out.println(users.get(index).getName() + " , " + users.get(index).getId() + " left");
                            users.remove(index);
                        }
                    }
                    Thread.yield();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void senddefauld() {
        new Thread(() -> {
            try {
                System.out.println("emitter has started");
                OutputStream out;
                while (true) {
                    ArrayList<User> copyusers = users;
                    if (copyusers.size() > 1) {
                        for (int i = 0; i < copyusers.size(); i++) {
                            out = copyusers.get(i).getSocket().getOutputStream();
                            PrintWriter print = new PrintWriter(out);
                            String msg = copyusers.size() - 1 + "~";
                            int counter = 0;
                            for (int j = 0; j < copyusers.size(); j++) {
                                if (copyusers.get(j).getId() != copyusers.get(i).getId()) {
                                    counter++;
                                    if (counter < copyusers.size() - 1) {
                                        msg += copyusers.get(j).next() + "~";
                                    } else {
                                        msg += copyusers.get(j).next();
                                    }
                                }
                            }
                            msg += "\n";
                            print.write(msg);
//                            System.out.println(msg + " ------> " + copyusers.get(i).getId());
                            print.flush();
                        }
                    } else {
                        if (copyusers.size() > 0) {
                            out = copyusers.get(0).getSocket().getOutputStream();
                            PrintWriter print = new PrintWriter(out);
                            String msg = "0\n";
                            print.write(msg);
                            print.flush();
                        }
                    }
                    Thread.yield();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void connect() {
        new Thread(() -> {
            try {
                System.out.println("accapter has started");
                while (true) {
                    Socket clin;
                    if ((clin = server.accept()) != null) {

                        User us = new User();
                        us.setSocket(clin);
                        String s;
                        InputStream in = us.getSocket().getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        do {

                            while ((s = reader.readLine()) != null) {
                                if (s != null) {
                                    Packets inputpack = new Packets(s);
                                    String[] j = inputpack.getProcessed();
                                    us.setName(j[0]);
                                    us.setId(Integer.parseInt(j[1]));
                                    System.out.println(j[0] + " --->" + j[1] + " has joind");
                                    us.go();
                                    users.add(us);
                                    break;
                                }
                            }
                        } while (us.isGo() == false);
                    }
                    Thread.yield();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
