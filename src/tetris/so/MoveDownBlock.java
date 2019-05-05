package tetris.so;

import model.TetrisShape;

public class MoveDownBlock  extends AbstractGenericOperation{


    public MoveDownBlock(int[][] MESH) {
        super(MESH);
    }

    @Override
    protected void validate(TetrisShape object) throws Exception {
        if (object.a.getY() + MOVE == YMAX || object.b.getY() + MOVE == YMAX || object.c.getY() + MOVE == YMAX || object.d.getY() + MOVE == YMAX
                || touchingOtherBlock(object)){
            MESH[(int) (object.a.getX() / SIZE)][(int) (object.a.getY() / SIZE)] = 1;
            MESH[(int) (object.b.getX() / SIZE)][(int) (object.b.getY() / SIZE)] = 1;
            MESH[(int) (object.c.getX() / SIZE)][(int) (object.c.getY() / SIZE)] = 1;
            MESH[(int) (object.d.getX() / SIZE)][(int) (object.d.getY() / SIZE)] = 1;
            throw new Exception("Block can't be moved down");
        }
    }

    // this method moves block down and removes rows if needed
    @Override
    protected void execute(TetrisShape object) throws Exception {
            // moving the block down
            if (object.a.getY() + MOVE < YMAX && object.b.getY() + MOVE < YMAX && object.c.getY() + MOVE < YMAX && object.d.getY() + MOVE < YMAX
                    && !touchingOtherBlock(object)) {
                shape = object;
                shape.a.setY(object.a.getY() + MOVE);
                shape.b.setY(object.b.getY() + MOVE);
                shape.c.setY(object.c.getY() + MOVE);
                shape.d.setY(object.d.getY() + MOVE);
            }
    }


    private boolean touchingOtherBlock(TetrisShape shape) {
        return MESH[(int) (shape.a.getX() / SIZE)][(int) (shape.a.getY() / SIZE + 1)] == 1 ||
                MESH[(int) (shape.b.getX() / SIZE)][(int) (shape.b.getY() / SIZE + 1)] == 1 ||
                MESH[(int) (shape.c.getX() / SIZE)][(int) (shape.c.getY() / SIZE + 1)] == 1 ||
                MESH[(int) (shape.d.getX() / SIZE)][(int) (shape.d.getY() / SIZE + 1)] == 1;
    }

}
