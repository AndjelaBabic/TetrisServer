package tetris.so;

import model.TetrisShape;

public class MoveLeft extends AbstractGenericOperation{


    public MoveLeft(int[][] MESH) {
        super(MESH);
    }

    @Override
    protected void validate(TetrisShape object) throws Exception {

    }

    @Override
    protected void execute(TetrisShape object) throws Exception {
        if (object.a.getX() - MOVE >= 0 && object.b.getX() - MOVE >= 0 &&
                object.c.getX() - MOVE >= 0 && object.d.getX() - MOVE >= 0) {

            int movea = MESH[(int) (object.a.getX() / SIZE - 1)][(int) object.a.getY() / SIZE];
            int moveb = MESH[(int) (object.b.getX() / SIZE - 1)][(int) object.b.getY() / SIZE];
            int movec = MESH[(int) (object.c.getX() / SIZE - 1)][(int) object.c.getY() / SIZE];
            int moved = MESH[(int) (object.d.getX() / SIZE - 1)][(int) object.d.getY() / SIZE];

            if (movea == 0 && moveb == 0 && movec == 0 && moved == 0) {
                object.a.setX(object.a.getX() - MOVE);
                object.b.setX(object.b.getX() - MOVE);
                object.c.setX(object.c.getX() - MOVE);
                object.d.setX(object.d.getX() - MOVE);
            }
        }
        shape = object;
    }
}
