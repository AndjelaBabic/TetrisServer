package tetris.so;

import model.Rect;
import model.TetrisShape;

import java.util.ArrayList;
import java.util.List;

public class RemoveRows extends AbstractGenericOperation {


    public List<Rect> allRects;
    public List<Integer> rowsToRemoveIndex;

    public RemoveRows(int[][] MESH) {
        super(MESH);
    }

    @Override
    protected void validate(TetrisShape object) throws Exception {

    }

    @Override
    protected void execute(TetrisShape object) throws Exception {
        rowsToRemoveIndex = new ArrayList<>();

        for (int y = 0; y < YMAX / SIZE; y++) {
            int numberInRow = 0;
            for (int x = 0; x < XMAX / SIZE; x++) {
                if (MESH[x][y] == 1) {
                    numberInRow++;
                }
            }
            if (numberInRow == XMAX / SIZE) {
                rowsToRemoveIndex.add(y);
            }
        }
        // TODO Try to improve this part

        List<Rect> newRects = new ArrayList<>();
        for (Rect rect : allRects) {
            MESH[(int) rect.getX() / SIZE][(int) rect.getY() / SIZE] = 0;
            if(!rowsToRemoveIndex.contains(rect.getY() / SIZE)){
                newRects.add(rect);
            }
            // 23
            //if (rowsToRemoveIndex.contains(rect.getY() / SIZE)) {
            //    MESH[(int) rect.getX() / SIZE][(int) rect.getY() / SIZE] = 0;
            //}
        }

        for (Rect rect: newRects) {
            int rowsToMoveDown = calculateHowManyRowsToMoveDown(rowsToRemoveIndex, rect);
            if (rowsToMoveDown != 0) {
                MESH[(int) rect.getX() / SIZE][(int) rect.getY() / SIZE] = 0;
                rect.setY(rect.getY() + SIZE * rowsToMoveDown);
            }
        }

        for (Rect rect :newRects){
            MESH[(int) rect.getX() / SIZE][(int) rect.getY() / SIZE] = 1;
        }
    }

    private int calculateHowManyRowsToMoveDown(List<Integer> rowsToRemoveIndex, Rect rect) {
        int j = 0;
        for (int i = 0; i < rowsToRemoveIndex.size(); i++) {
            if (rect.getY() / SIZE < rowsToRemoveIndex.get(i)) {
                j++;
            }
        }
        return j;
    }

    public void setAllRects(List<Rect> allRects) {
        this.allRects = allRects;
    }
}
