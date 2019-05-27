package tetris.so;

import results.Results;

import java.util.List;

public class GetHighScoreList extends AbstractGenericOperation{

    List<Results> results;

    public GetHighScoreList(int[][] MESH) throws Exception {
        super(MESH);
    }

    @Override
    protected void validate(Object object) throws Exception {

    }

    @Override
    protected void execute(Object object) throws Exception {
        try {
            db.startTransaction();
            results = db.getAllHighScores();
            db.commitTransaction();
        } catch (Exception e){
            db.rollbackTransaction();
            e.printStackTrace();
        }
    }

    public List<Results> getResults() {
        return results;
    }
}
