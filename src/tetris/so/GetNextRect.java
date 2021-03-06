package tetris.so;

import model.Rect;
import model.TetrisShape;

public class GetNextRect extends AbstractGenericOperation {


    public GetNextRect(int[][] MESH) throws Exception {
        super(MESH);
    }

    @Override
    protected void validate(Object o)  {

    }

    @Override
    protected void execute(Object o)  {
        // random color
        TetrisShape object = (TetrisShape) o;
        int randomNumber = (int) (Math.random() * 100);

        Rect a = new Rect(SIZE - 1, SIZE - 1);
        Rect b = new Rect(SIZE - 1, SIZE - 1);
        Rect c = new Rect(SIZE - 1, SIZE - 1);
        Rect d = new Rect(SIZE - 1, SIZE - 1);

        String color;
        if (randomNumber < 15) {
            color = "j";
            a.setX(XMAX / 2);
            b.setX(XMAX / 2);
            b.setY(SIZE);
            c.setX(XMAX / 2 - SIZE);
            c.setY(SIZE * 2);
            d.setX(XMAX / 2);
            d.setY(SIZE * 2);
        } else if (randomNumber < 30) {
            color = "l";
            a.setX(XMAX / 2);
            a.setY(SIZE * 2);
            b.setX(XMAX / 2);
            b.setY(SIZE);
            c.setX(XMAX / 2 - SIZE);
            d.setX(XMAX / 2);
        } else if (randomNumber < 45) {
            // square
            color = "o";
            a.setX(XMAX / 2 - SIZE);
            b.setX(XMAX / 2);
            c.setX(XMAX / 2 - SIZE);
            c.setY(SIZE);
            d.setX(XMAX / 2);
            d.setY(SIZE);
        } else if (randomNumber < 60) {
            color = "s";
            a.setX(XMAX / 2);
            b.setX(XMAX / 2 + SIZE);
            c.setX(XMAX / 2 - SIZE);
            c.setY(SIZE);
            d.setX(XMAX / 2);
            d.setY(SIZE);
        } else if (randomNumber < 75) {
            // raketa
            color = "t";
            a.setX(XMAX / 2 - SIZE);
            a.setY(SIZE);
            b.setX(XMAX / 2);
            c.setX(XMAX / 2);
            c.setY(SIZE); // i ovo
            d.setX(XMAX / 2 + SIZE);
            d.setY(SIZE);
        } else if (randomNumber < 90) {
            color = "z";
            a.setX(XMAX / 2 - SIZE);
            b.setX(XMAX / 2);
            c.setX(XMAX / 2);
            c.setY(SIZE);
            d.setX(XMAX / 2 + SIZE);
            d.setY(SIZE);
        } else {
            color = "i";
            a.setX(XMAX / 2 - SIZE - SIZE);
            b.setX(XMAX / 2 - SIZE);
            c.setX(XMAX / 2);
            d.setX(XMAX / 2 + SIZE);
        }
        shape = new TetrisShape(a, b, c, d, color);
    }

}
