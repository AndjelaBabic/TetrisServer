package tetris.thread;


import tetris.so.AbstractGenericOperation;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ThreadServer extends Thread {

    ServerSocket serverSocket;
    List<ThreadClient> clients = new ArrayList<>();

    public ThreadServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                System.out.println("Waiting for a client...");
                Socket clientSocket = serverSocket.accept();
                ThreadClient client = new ThreadClient(clientSocket, this);
                client.start();
                clients.add(client);
                System.out.println("Client connected!");
            } catch (Exception e) {
                closeClientSockets();
                System.out.println("Server is stopped");
            }
        }
    }

    private void closeClientSockets() {
        for (ThreadClient client : clients) {
            try {
                client.interrupt();
                client.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeClient(ThreadClient threadClient) {
        try {
            threadClient.interrupt();
            clients.remove(threadClient);
            threadClient.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
