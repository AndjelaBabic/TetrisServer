package tetris.so;

import model.TetrisShape;
import results.Results;

public class InsertInScores extends AbstractGenericOperation{

    Results objectToDelete;

    public InsertInScores(int[][] MESH) throws Exception {
        super(MESH);
    }

    @Override
    protected void validate(Object o) throws Exception {
        Results result = (Results) o;
        if(db.getCount() >= 10){
            objectToDelete = (Results) db.getObjectWithMinScore();
            if(objectToDelete == null){
                return;
            }
            // if the newScore is smaller than the minimal score
            if(result.getScore() < objectToDelete.getScore()){
                db.startTransaction();
                throw new Exception("Your score didn't get on the list, we wish you more luck next time!");
            }
        }
    }

    @Override
    protected void execute(Object o) throws Exception {
        try {
            db.startTransaction();
            if(objectToDelete != null){
                db.delete(objectToDelete);
            }
            Results result = (Results) o;
            db.save(result);
            db.commitTransaction();
        } catch (Exception e){
            db.rollbackTransaction();
            e.printStackTrace();
        }
    }
}
