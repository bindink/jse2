import by.gsu.epamlab.beans.RunnerLogic;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.loaders.DataSource;
import by.gsu.epamlab.loaders.ResultsLoader;
import by.gsu.epamlab.readers.IResultDAO;
import by.gsu.epamlab.readers.ResultImplCSV;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Home on 07.06.2017.
 */
public class RunnerInt {
    public static void main(String[]args) {
        final String fileName = "src/results.csv";
        final String MARK_TYPE = "MARK";

        RunnerLogic.execute(MARK_TYPE, fileName);
    }
}
