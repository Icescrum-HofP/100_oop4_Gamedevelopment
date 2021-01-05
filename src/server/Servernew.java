package server;


import server.packet.Packets;
import server.packet.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servernew {

    //number of player~name;id;pos;bulletsize;bullets~name;id;pos;bulletsize;bullets

    private ArrayList<User> users;
    private ServerSocket server;
    private boolean process;

    public Servernew() {
        try {
            server = new ServerSocket(5555);
            users = new ArrayList<User>();
            connect();
            recivedefauld();
            senddefauld();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void recivedefauld() {
        new Thread(() -> {
            try {
                while (true) {
                    for (int i = 0; i < users.size(); i++) {
                        InputStream ini = users.get(i).getSocket().getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(ini));
                        String s = null;
                        while ((s = reader.readLine()) != null) {
//----> to do

                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void senddefauld(){
        new Thread(() -> {
            try {
                OutputStream out;
                while (true) {

                    for(int i=0;i < users.size();i++) {
                        out = users.get(i).getSocket().getOutputStream();
                        PrintWriter print = new PrintWriter(out);
                        String msg = "";
                        for(int j=0;j<users.size();j++){



                        }


                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    private void connect() {
        new Thread(() -> {
            try {
                while (true) {
                    Socket clin;
                    if ((clin = server.accept()) != null) {
                        User us = new User();
                        us.setSocket(clin);
                        String s = null;
                        String jump = null;
                        InputStream ini = us.getSocket().getInputStream();
                        do {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(ini));
                            while ((s = reader.readLine()) != null) {
                                jump += s;
                            }
                            if (jump != null) {
                                Packets inputpack = new Packets(jump);
                                String[] j = inputpack.getProcessed();

                                us.setName(j[0]);
                                us.setId(Integer.parseInt(j[1]));
                                users.add(us);
                            }
                        } while (!us.isGo());
                    }
                    Thread.yield();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
