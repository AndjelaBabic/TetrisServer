package tetris.so;

import domain.TetrisShape;

public class MoveRight extends AbstractGenericOperation {


    public MoveRight(int[][] MESH) {
        super(MESH);
    }

    @Override
    protected void validate(TetrisShape object) throws Exception {

    }

    @Override
    protected void execute(TetrisShape object) throws Exception {

        if (object.a.getX() + SIZE + MOVE <= XMAX && object.b.getX() + SIZE + MOVE <= XMAX &&
                object.c.getX() + SIZE + MOVE <= XMAX && object.d.getX() + SIZE + MOVE <= XMAX) {
            int movea = MESH[(int) (object.a.getX() / SIZE + 1)][(int) object.a.getY() / SIZE];
            int moveb = MESH[(int) (object.b.getX() / SIZE + 1)][(int) object.b.getY() / SIZE];
            int movec = MESH[(int) (object.c.getX() / SIZE + 1)][(int) object.c.getY() / SIZE];
            int moved = MESH[(int) (object.d.getX() / SIZE + 1)][(int) object.d.getY() / SIZE];
            if (movea == 0 && moveb == 0 && movec == 0 && moved == 0) {
                object.a.setX(object.a.getX() + MOVE);
                object.b.setX(object.b.getX() + MOVE);
                object.c.setX(object.c.getX() + MOVE);
                object.d.setX(object.d.getX() + MOVE);
            }
        }
        shape = object;
    }
}
