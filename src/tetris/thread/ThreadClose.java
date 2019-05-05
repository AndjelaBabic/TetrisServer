package tetris.thread;

import java.io.IOException;
import java.net.ServerSocket;

public class ThreadClose extends Thread{

        boolean end;
        ThreadServer tserver;
        ServerSocket serverSocket;


    public ThreadClose(ThreadServer tserver, ServerSocket serverSocket) {
        this.tserver = tserver;
        this.serverSocket = serverSocket;
    }

    @Override
        public void run() {
            while (!end) {
                if(tserver.isInterrupted()){
                    try {
                        serverSocket.close();
                        end = true;
                    }  catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


}
