package tetris.thread;

import model.Rect;
import model.TetrisShape;
import results.Results;
import tetris.so.*;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.Constants;
import util.Operation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class ThreadClient extends Thread {

    Socket socket;
    ThreadServer threadServer;
    AbstractGenericOperation operation;
    public int MESH[][] = new int[Constants.XMAX / Constants.SIZE][Constants.YMAX / Constants.SIZE];
    public static int score = 0;
    private static int linesNo = 0;
    int SIZE = Constants.SIZE;
    int MOVE = Constants.MOVE;
    int XMAX = Constants.XMAX;
    int YMAX = Constants.YMAX;

    public ThreadClient(Socket socket, ThreadServer threadServer) {
        this.socket = socket;
        this.threadServer = threadServer;
        for (int i = 0; i < MESH.length; i++) {
            int[] a = MESH[i];
            Arrays.fill(a, 0);
        }
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                ObjectInputStream inObjectFromClient = new ObjectInputStream(socket.getInputStream());
                RequestObject request = (RequestObject) inObjectFromClient.readObject();
                ResponseObject response = new ResponseObject();
                switch (request.getOperation()) {
                    case Operation.GET_NEXT_RECT:
                        operation = new GetNextRect(MESH);
                        operation.templateExecute(null);
                        response.setCode(1);
                        response.setData(operation.getShape());
                        break;
                    case Operation.MOVE_RIGHT:
                        operation = new MoveRight(MESH);
                        operation.templateExecute(request.getData());
                        response.setCode(1);
                        response.setData(operation.getShape());
                        break;
                    case Operation.MOVE_LEFT:
                        operation = new MoveLeft(MESH);
                        operation.templateExecute(request.getData());
                        response.setCode(1);
                        response.setData(operation.getShape());
                        break;
                    case Operation.MOVE_DOWN_BLOCK:
                        try {
                            operation = new MoveDownBlock(MESH);
                            operation.templateExecute((TetrisShape) request.getData());
                            response.setCode(1);
                            response.setData(operation.getShape());
                            break;
                        } catch (Exception e) {
                            response.setCode(2);
                            response.setData(e.getMessage());
                            break;
                        }
                    case Operation.MOVE_TURN:
                        try {
                            operation = new MoveTurn(MESH);
                            operation.templateExecute(request.getData());
                            response.setCode(1);
                            response.setData(operation.getShape());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case Operation.REMOVE_ROWS:
                        try {
                            operation = new RemoveRows(MESH);
                            ((RemoveRows) operation).setAllRects((List<Rect>) request.getData());
                            operation.templateExecute(null);
                            response.setData(((RemoveRows) operation).rowsToRemoveIndex);
                            response.setCode(1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case Operation.CHECK_BOUNDRIES:
                        try {
                            operation = new CheckBoundries(MESH);
                            operation.templateExecute(request.getData());
                            response.setCode(1);
                        } catch (Exception e) {
                            response.setCode(2);
                        }
                        break;
                    case Operation.INSERT_IN_SCORES:
                        try {
                            operation = new InsertInScores(MESH);
                            operation.templateExecute(request.getData());
                            response.setCode(1);
                        } catch (Exception e) {
                            response.setCode(2);
                        }
                        break;
                    case Operation.GET_HIGH_SCORE_LIST:
                        try {
                            operation = new GetHighScoreList(MESH);
                            operation.templateExecute(request.getData());
                            response.setCode(1);
                            response.setData(((GetHighScoreList)operation).getResults());
                        } catch (Exception e) {
                            response.setCode(2);
                        }
                        break;
                }
                setMESH(operation.getMESH());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
                out.flush();
            } catch (IOException e) {
                threadServer.removeClient(this);
                System.out.println("Client logged out");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public int[][] getMESH() {
        return MESH;
    }

    public void setMESH(int[][] MESH) {
        this.MESH = MESH;
    }
}
