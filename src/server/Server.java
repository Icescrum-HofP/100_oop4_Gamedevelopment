package server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server()  {

        try{
            ServerSocket server = new ServerSocket(5555);


            Socket client = server.accept();

            OutputStream out = client.getOutputStream();

            System.out.println(out);

            System.out.println("serverstart1");

        }catch(IOException e ){


         e.printStackTrace();

        }
        System.out.println("serverstart");
    }



}
