import by.gsu.epamlab.beans.RunnerLogic;

import java.sql.SQLException;

/**
 * Created by Home on 07.06.2017.
 */
public class RunnerHalf {
    public static void main(String[]args) {
        final String fileName = "src/results2.csv";
        final String MARK_TYPE = "HALF_MARK";

        RunnerLogic.execute(MARK_TYPE, fileName);
    }
}
