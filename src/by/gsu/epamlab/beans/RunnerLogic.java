package by.gsu.epamlab.beans;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.factories.MarksFactory;
import by.gsu.epamlab.factories.ReadersFactory;
import by.gsu.epamlab.loaders.DataSource;
import by.gsu.epamlab.loaders.ResultsLoader;
import by.gsu.epamlab.readers.IResultDAO;
import by.gsu.epamlab.readers.ResultImplCSV;

import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Home on 07.06.2017.
 */
public class RunnerLogic {

    public static void execute( String markType, String fileName)  {

        try {
            Class.forName(Constants.className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection cn = DataSource.getInstance().getConnection();

        Statement st = null;
        ResultSet rs = null;
        IResultDAO reader = ReadersFactory.getReaderFromFactory(fileName, markType);

        ResultsLoader.loadResults(reader, cn);

        try {
            st = cn.createStatement();
            rs = st.executeQuery(Constants.SELECT_MEAN_MARK);

            while (rs.next()) {
                System.out.printf(Constants.OUTPUT_FORMAT, rs.getString(1), rs.getDouble(2));
            }
            System.out.println();
        } catch (SQLException e) {
            System.err.println("Problems with mean mark selection." + e);
        }

        LinkedList<Result> results = null;
        try {
            rs = st.executeQuery(Constants.SELECT_DATE);
            results = new LinkedList<Result>();
            while (rs.next()) {
                Result result = new Result(rs.getString(Constants.LOGIN_IND), rs.getString(Constants.TEST_IND), rs.getString(Constants.DATE_IND), MarksFactory.getMarkFromFactory(reader.getMarkType(),rs.getString(Constants.MARK_IND)));
                results.add(result);
                System.out.println(result);
            }
            System.out.println();

        } catch (SQLException e1) {
            System.err.println("Problems with current month data selection."  + e1);
        }finally{
            DataSource.closeStatement(st);
            DataSource.closeResultSet(rs);
            DataSource.closeConnection(cn);
            reader.closeReader();
        }

        if(results.size() > 0){
            Iterator iterator = results.descendingIterator();

            Result res = (Result)iterator.next();
            Date latestDate = res.getDate();
            Date date;
            System.out.println(res);
            while( iterator.hasNext()){
                res = (Result)iterator.next();
                date = res.getDate();
                 if( latestDate.toString().equals(date.toString())){
                     System.out.println(res);
                 }
                 else{
                     break;
                 }
            }
        }
    }

}
