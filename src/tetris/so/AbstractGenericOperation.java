package tetris.so;

import model.Rect;
import model.TetrisShape;
import tetris.db.DatabaseRepository;
import util.Constants;

public abstract class AbstractGenericOperation {

    protected DatabaseRepository db;

    TetrisShape shape;
    int SIZE = Constants.SIZE;
    int MOVE = Constants.MOVE;
    int XMAX = Constants.XMAX;
    int YMAX = Constants.YMAX;
    public int MESH[][];

    public AbstractGenericOperation(int MESH[][]) throws Exception {
        this.MESH = MESH;
        this.db = new DatabaseRepository();
    }

    public void templateExecute(Object object) throws Exception {
        try {
            validate(object);
            try {
                execute(object);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }

    }

    protected abstract void validate(Object object) throws Exception;

    protected abstract void execute(Object object) throws Exception;

    public TetrisShape getShape() {
        return shape;
    }

    public void setShape(TetrisShape shape) {
        this.shape = shape;
    }

    public boolean checkBoundries(Rect rect, int x, int y) {
        boolean xok = false;
        boolean yok = false;

        if (rect.getX() + MOVE * x + SIZE <= XMAX && rect.getX() + MOVE * x >= 0) {
            xok = true;
        }
        if (rect.getY() + MOVE * y + SIZE <= YMAX || rect.getY() + MOVE * y >= 0) {
            yok = true;
        }
        try {
            return xok && yok && MESH[(int) (rect.getX() / SIZE + x)][(int) (rect.getY() / SIZE + y)] == 0;
        } catch (ArrayIndexOutOfBoundsException ex) {
            return false;
        }

    }

    public int[][] getMESH() {
        return MESH;
    }

    public void setMESH(int[][] MESH) {
        this.MESH = MESH;
    }
}
