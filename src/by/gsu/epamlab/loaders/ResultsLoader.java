package by.gsu.epamlab.loaders;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.readers.IResultDAO;

import java.sql.*;

/**
 * Created by Home on 07.06.2017.
 */
public class ResultsLoader {

    public static void loadResults(IResultDAO reader , Connection cn){
        PreparedStatement psSelectLogin = null;
        PreparedStatement psSelectTest = null;
        PreparedStatement psInsertLogin = null;
        PreparedStatement psInsertTest = null;
        PreparedStatement psInsertResults = null;
        Statement st = null;
        try {

            st = cn.createStatement();
            st.executeUpdate(Constants.DELETE_RESULTS);

            psSelectLogin = cn.prepareStatement(Constants.SEARCH_LOGIN);
            psSelectTest = cn.prepareStatement(Constants.SEARCH_TEST);
            psInsertLogin = cn.prepareStatement(Constants.INSERT_LOGIN);
            psInsertTest = cn.prepareStatement(Constants.INSERT_TESTS);
            psInsertResults = cn.prepareStatement(Constants.INSERT_RESULTS);

            while (reader.hasResult()) {
                Result result = reader.nextResult();
                String login = result.getLogin();
                String test = result.getTest();

                int idLogin = getId(login, psSelectLogin, psInsertLogin);
                int idTest = getId(test, psSelectTest, psInsertTest);

                psInsertResults.setInt(Constants.LOGIN_IND, idLogin);
                psInsertResults.setInt(Constants.TEST_IND, idTest);
                psInsertResults.setDate(Constants.DATE_IND, result.getDate());
                psInsertResults.setInt(Constants.MARK_IND, result.getMark().getMarkValue());

                psInsertResults.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Problems with loading into database: " + e);
        }finally{
            DataSource.closeStatement(st, psSelectLogin, psSelectTest,psInsertLogin, psInsertTest, psInsertResults );
        }


    }

    private static int getId(String name, PreparedStatement psSelect, PreparedStatement psInsert ) {
        int id = 0;
        ResultSet rs = null;
        try {
            psSelect.setString(1,name);
            rs = psSelect.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
            else{
                psInsert.setString(1,name);
                psInsert.executeUpdate();
                psSelect.setString(1,name);
                rs = psSelect.executeQuery();
                rs.next();
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Problems with getting an id from database: "  + e);
        }
        finally{
            DataSource.closeResultSet(rs);
        }
        return id;

    }

}