package tetris.so;

import model.TetrisShape;

public class CheckBoundries extends AbstractGenericOperation{

    boolean checkPassed;

    public CheckBoundries(int[][] MESH) throws Exception {
        super(MESH);
    }

    @Override
    protected void validate(Object object) throws Exception {

    }

    @Override
    protected void execute(Object o) throws Exception {

        TetrisShape object = (TetrisShape) o;
         checkPassed = checkBoundries(object.a, 0, 0) && checkBoundries(object.b, 0, 0) &&
                       checkBoundries(object.c, 0, 0) && checkBoundries(object.d, 0, 0);
        if(!checkPassed){
            throw new Exception("Check failed");
        }
    }


}
