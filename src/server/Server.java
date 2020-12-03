package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    public Server()  {

        try{
            ServerSocket server = new ServerSocket(5555);
        }catch(IOException e ){


         e.printStackTrace();

        }
    }



}
