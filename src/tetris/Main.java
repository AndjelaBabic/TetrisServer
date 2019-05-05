package tetris;

import javafx.application.Application;
import javafx.stage.Stage;
import tetris.thread.ThreadServer;

import java.net.ServerSocket;

public class Main extends Application {

    ThreadServer server;

    @Override
    public void start(Stage primaryStage) throws Exception{
        ServerSocket serverSocket = new ServerSocket(9009);
        server = new ThreadServer(serverSocket);
        server.start();
        System.out.println("Server started");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
