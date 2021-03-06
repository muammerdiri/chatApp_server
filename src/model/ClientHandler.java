package model;


import javax.net.ssl.SSLSocket;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;

public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    private SSLSocket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    public String clientUsername;
    public String otherClientUsername;
    private DataInputStream inputStream=null;
    private DataOutputStream outputStream = null;



    public ClientHandler(SSLSocket socket) {
        try {
            this.socket = socket;
//            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

//            this.clientUsername = bufferedReader.readLine();
//            this.otherClientUsername = bufferedReader.readLine();
            this.outputStream= new DataOutputStream(socket.getOutputStream());
            this.inputStream = new DataInputStream(socket.getInputStream());
            this.clientUsername = inputStream.readUTF();
            this.otherClientUsername = inputStream.readUTF();


            clientHandlers.add(this);

            System.out.println("SERVER: " + clientUsername + " giriş yaptı!");
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter,outputStream,inputStream);
        }
    }

    //! Runnable implementation, thread start function.
    @Override
    public void run() {

        while (socket.isConnected()) {
            try {
                broadcastMessage(inputStream.readUTF());
            } catch (Exception e) {
                closeEverything(socket, bufferedReader, bufferedWriter,outputStream,inputStream);
                break;
            }
        }
    }


    public void broadcastMessage(String messageToSend) {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                //! istemci kendi dışında ki istemcilere mesaj yollama koşulu.
                if (clientHandler.clientUsername.equals(otherClientUsername)) {
                    clientHandler.outputStream.writeUTF(messageToSend);

                }
            } catch (IOException e) {
                // Gracefully close everything.
                closeEverything(socket, bufferedReader, bufferedWriter,outputStream,inputStream);
            }
        }
    }



    //! Function to run when the client leaves the chat screen.
    public void removeClientHandler() {
        clientHandlers.remove(this);
        System.out.println("SERVER: " + clientUsername + " sohbet ekranından ayrıldı!");
    }

    //! Function to close all builds.
    public void closeEverything(SSLSocket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter,DataOutputStream out,DataInputStream in) {
        removeClientHandler();
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }if (in != null) {
                in.close();
            }if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
