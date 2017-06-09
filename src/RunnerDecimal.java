import by.gsu.epamlab.beans.RunnerLogic;

import java.sql.SQLException;

/**
 * Created by Home on 07.06.2017.
 */
public class RunnerDecimal {
     public static void main(String[]args) {
         final String fileName = "src/results.xml";
         final String MARK_TYPE = "DECIMAL_MARK";

         RunnerLogic.execute(MARK_TYPE, fileName);
     }
}
