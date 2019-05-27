package tetris.so;

import model.Rect;
import model.TetrisShape;

public class MoveTurn extends AbstractGenericOperation{

    public MoveTurn(int[][] MESH) throws Exception {
        super(MESH);
    }

    @Override
    protected void validate(Object o) throws Exception {

    }

    @Override
    protected void execute(Object o) throws Exception {
        TetrisShape object = (TetrisShape) o;
        int f = object.getForm();
        Rect a = object.a;
        Rect b = object.b;
        Rect c = object.c;
        Rect d = object.d;

        switch (object.getName()) {
            case "j":
                if (f == 1 && checkBoundries(a, 1, 2) && checkBoundries(b, 0, 1) && checkBoundries(c, 0, -1)
                        && checkBoundries(d, -1, 0)) {
                    moveRect(a, 1, 2);
                    moveRect(b, 0, 1);
                    moveRect(c, 0, -1);
                    moveRect(d, -1, 0);
                    object.changeForm();
                } else if (f == 2 && checkBoundries(a, -1, 0) && checkBoundries(b, 0, -1) && checkBoundries(c, 2, -1)
                        && checkBoundries(d, 1, -2)) {
                    moveRect(a, -1, 0);
                    moveRect(b, 0, -1);
                    moveRect(c, 2, -1);
                    moveRect(d, 1, -2);
                    object.changeForm();
                } else if (f == 3 && checkBoundries(a, -1, -1) && checkBoundries(c, 0, 2) && checkBoundries(d, 1, 1)) {
                    moveRect(a, -1, -1);
                    moveRect(c, 0, 2);
                    moveRect(d, 1, 1);
                    object.changeForm();
                } else if (checkBoundries(a, 1, -1) && checkBoundries(d, -1, 1) && checkBoundries(c, -2, 0)) {
                    moveRect(a, 1, -1);
                    moveRect(c, -2, 0);
                    moveRect(d, -1, 1);
                    object.changeForm();
                }
                break;
            case "l":
                if (f == 1 && checkBoundries(a, -1, 0) && checkBoundries(b, 0, 1) && checkBoundries(c, 2, 1)
                        && checkBoundries(d, 1, 2)) {
                    moveRect(a, -1, 0);
                    moveRect(b, 0, 1);
                    moveRect(c, 2, 1);
                    moveRect(d, 1, 2);
                    object.changeForm();
                } else if (f == 2 && checkBoundries(a, 1, -2) && checkBoundries(b, 0, -1) && checkBoundries(c, 0, 1)
                        && checkBoundries(d, -1, 0)) {
                    moveRect(a, 1, -2);
                    moveRect(b, 0, -1);
                    moveRect(c, 0, 1);
                    moveRect(d, -1, 0);
                    object.changeForm();
                } else if (f == 3 && checkBoundries(a, 1, 1) && checkBoundries(c, -2, 0) && checkBoundries(d, -1, -1)) {
                    moveRect(a, 1, 1);
                    moveRect(c, -2, 0);
                    moveRect(d, -1, -1);
                    object.changeForm();
                } else if (checkBoundries(a, -1, 1) && checkBoundries(c, 0, -2) && checkBoundries(d, 1, -1)) {
                    moveRect(a, -1, 1);
                    moveRect(c, 0, -2);
                    moveRect(d, 1, -1);
                    object.changeForm();
                }
                break;
            case "o":
                break;
            case "s":
                if ((f == 1 || f == 3) && checkBoundries(a, -1, 0) && checkBoundries(b, -2, -1) && checkBoundries(c, 1, 0)
                        && checkBoundries(d, 0, -1)) {
                    moveRect(a, -1, 0);
                    moveRect(b, -2, -1);
                    moveRect(c, 1, 0);
                    moveRect(d, 0, -1);
                    object.changeForm();
                } else if ((f == 2 || f == 4) && checkBoundries(a, 1, 0) && checkBoundries(b, 2, 1) && checkBoundries(c, -1, 0)
                        && checkBoundries(d, 0, 1)) {
                    moveRect(a, 1, 0);
                    moveRect(b, 2, 1);
                    moveRect(c, -1, 0);
                    moveRect(d, 0, 1);
                    object.changeForm();
                }
                break;
            case "t":
                if (f == 1 && checkBoundries(a, 1, -1) && checkBoundries(b, 1, 1) && checkBoundries(d, -1, 1)) {
                    moveRect(a, 1, -1);
                    moveRect(b, 1, 1);
                    moveRect(d, -1, 1);
                    object.changeForm();
                } else if (f == 2 && checkBoundries(a, 1, 1) && checkBoundries(b, -1, 1) && checkBoundries(d, -1, -1)) {
                    moveRect(a, 1, 1);
                    moveRect(b, -1, 1);
                    moveRect(d, -1, -1);
                    object.changeForm();
                } else if (f == 3 && checkBoundries(a, -1, 1) && checkBoundries(b, -1, -1) && checkBoundries(d, 1, -1)) {
                    moveRect(a, -1, 1);
                    moveRect(b, -1, -1);
                    moveRect(d, 1, -1);
                    object.changeForm();
                } else if (f == 4 && checkBoundries(a, -1, -1) && checkBoundries(b, 1, -1) && checkBoundries(d, 1, 1)) {
                    moveRect(a, -1, -1);
                    moveRect(b, 1, -1);
                    moveRect(d, 1, 1);
                    object.changeForm();
                }
                break;
            case "z":
                if ((f == 1 || f == 3) && checkBoundries(a, 2, -1) && checkBoundries(b, 1, 0) && checkBoundries(c, 0, -1)
                        && checkBoundries(d, -1, 0)) {
                    moveRect(a, 2, -1);
                    moveRect(b, 1, 0);
                    moveRect(c, 0, -1);
                    moveRect(d, -1, 0);
                    object.changeForm();
                } else if ((f == 2 || f == 4) && checkBoundries(a, -2, 1) && checkBoundries(b, -1, 0) && checkBoundries(c, 0, 1)
                        && checkBoundries(d, 1, 0)) {
                    moveRect(a, -2, 1);
                    moveRect(b, -1, 0);
                    moveRect(c, 0, 1);
                    moveRect(d, 1, 0);
                    object.changeForm();
                }
                break;
            case "i":
                if ((f == 1 || f == 3) && checkBoundries(a, 2, -1) && checkBoundries(b, 1, 0) && checkBoundries(c, 0, 1)
                        && checkBoundries(d, -1, 2)) {
                    moveRect(a, 2, -1);
                    moveRect(b, 1, 0);
                    moveRect(c, 0, 1);
                    moveRect(d, -1, 2);
                    object.changeForm();
                } else if ((f == 2 || f == 4) && checkBoundries(a, -2, 1) && checkBoundries(b, -1, 0) && checkBoundries(c, 0, -1)
                        && checkBoundries(d, 1, -2)) {
                    moveRect(a, -2, 1);
                    moveRect(b, -1, 0);
                    moveRect(c, 0, -1);
                    moveRect(d, 1, -2);
                    object.changeForm();
                }
                break;
        }

        shape = object;
    }

    private void moveRect(Rect rect, int x, int y) {
        rect.setX(rect.getX() + MOVE * x);
        rect.setY(rect.getY() + MOVE * y);
    }


}
