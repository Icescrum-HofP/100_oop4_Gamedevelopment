package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket server;
    private Socket client;
    public Server()  {

        try{
            server = new ServerSocket(5556);

             client = server.accept();

            OutputStream out = client.getOutputStream();


            InputStream in = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String s = null;

            while ((s = reader.readLine())!= null){
                System.out.println(s);
            }
            reader.close();

        }catch(IOException e ){


         e.printStackTrace();

        }
        System.out.println("serverstart");
    }

    public void start(){

        while(true){


            try {
                if(client.getInputStream() != null) {
                    InputStream in = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String s = null;

                    while ((s = reader.readLine()) != null) {
                        System.out.println(s);
                    }

                    reader.close();
                }
            }catch (IOException e){e.printStackTrace();}


        }


    }


}
