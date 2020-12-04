package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket server;
    private Socket client;
    private InputStream in;
    private OutputStream out;
    private int id;

    public Server()  {

        try{
            server = new ServerSocket(5556);

             client = server.accept();

             out = client.getOutputStream();


            in = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String s = null;

            while ((s = reader.readLine())!= null){
                System.out.println(s);
            }

        }catch(IOException e ){


         e.printStackTrace();

        }
        accapt();
    }

    public void start(){

        while(true){


            try {
                if(in != null) {
                    System.out.println("hier");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String s = null;

                    while ((s = reader.readLine()) != null) {
                        System.out.println(s);
                    }


                }
            }catch (IOException e){e.printStackTrace();}


        }


    }

    private void accapt(){
        new Thread(() -> {
            try {
                client =  server.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



}
