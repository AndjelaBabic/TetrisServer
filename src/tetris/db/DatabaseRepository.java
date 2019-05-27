package tetris.db;

import results.IDomainObject;
import results.Results;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseRepository {

    Connection connection;

    public DatabaseRepository() throws Exception {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void startTransaction() throws Exception {
        DatabaseConnection.getInstance().getConnection().setAutoCommit(false);
    }

    public void commitTransaction() throws Exception {
        DatabaseConnection.getInstance().getConnection().commit();
    }

    public void rollbackTransaction() throws Exception {
        DatabaseConnection.getInstance().getConnection().rollback();
    }

    public boolean save(IDomainObject object) {
        try {
            String query = "INSERT INTO " + object.returnTableName() + " ("+ object.returnColumnNames() +") VALUES("+ object.returnColumnValues()+")";
            System.out.println(query);
            Statement st = connection.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(IDomainObject object) {
        try {
            String query = "DELETE FROM "+object.returnTableName()+" WHERE "+ object.returnConditionForDeleting();
            System.out.println(query);
            Statement st = connection.createStatement();
            st.executeUpdate(query);
            st.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }

    }

    // done
    public List<Results> getAllHighScores() {
        try {
            String query = "SELECT id, name, score, date FROM results ORDER BY score";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(query);
            List<Results> results = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int score = rs.getInt("score");
                Date sqlDate = rs.getDate("date");
                java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
                Results r = new Results();
                r.setId(id);
                r.setDate(utilDate);
                r.setScore(score);
                r.setName(name);
                results.add(r);
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public IDomainObject getObjectWithMinScore() {
        try {
            String query = "SELECT id, name, score, date FROM results WHERE score IN (SELECT min(score) FROM results)";
            Statement st = connection.createStatement();
            System.out.println(query);
            ResultSet rs = st.executeQuery(query);
            Results r = new Results();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int score = rs.getInt("score");
                Date date = rs.getDate("date");
                r.setId(id);
                r.setDate(date);
                r.setScore(score);
                r.setName(name);
            }
            return r;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getCount() {
        try {
            String query = "SELECT count(*) as total FROM results";
            Statement st = connection.createStatement();
            System.out.println(query);
            ResultSet rs = st.executeQuery(query);
            int count = 0;
            while (rs.next()) {
                count = rs.getInt("total");
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }




}
