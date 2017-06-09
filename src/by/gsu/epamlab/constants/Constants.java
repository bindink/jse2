package by.gsu.epamlab.constants;

/**
 * Created by Home on 07.06.2017.
 */
public class Constants {
    public final static String className = "org.gjt.mm.mysql.Driver";

    public final static String RESULTS = "results";
    public final static  String LOGINS = "logins";
    public final static String TESTS = "tests";
    public final static String DELETE = "DELETE  FROM ";

    public final static String DELIMETER = ";";
    public final static String DELETE_RESULTS = DELETE + RESULTS ;
    public final static String DELETE_LOGINS = DELETE + LOGINS;
    public final static String DELETE_TESTS = DELETE + TESTS;
    public final static String INSERT_LOGIN = "INSERT INTO logins (name) values (?)";
    public final static String INSERT_TESTS = "INSERT INTO tests (name) values (?)";
    public final static String INSERT_RESULTS = "INSERT INTO results (loginId, testId, dat, mark) VALUES (?,?,?,? )  ON DUPLICATE KEY UPDATE mark = VALUES(mark)";

    public final static String SEARCH_LOGIN = "SELECT idLogin FROM logins WHERE name = ?";
    public final static String SEARCH_TEST = "SELECT idTest FROM tests WHERE name = ?";

    public final static String SELECT_MEAN_MARK = "SELECT  logins.name, AVG(results.mark) FROM results JOIN logins ON logins.idLogin = results.loginId GROUP BY loginId  ORDER BY mark DESC";
    public final static String SELECT_DATE = "SELECT logins.name, tests.name, results.dat, results.mark FROM results JOIN logins  ON logins.idLogin = results.loginId JOIN  tests ON tests.idTest = results.testID " +
            "WHERE MONTH(dat) = MONTH(NOW()) AND YEAR(dat) = YEAR(NOW()) ORDER BY dat";
    public final static String OUTPUT_FORMAT = "%s %1.2f \n";
    public final static int LOGIN_IND = 1;
    public final static int TEST_IND = 2;
    public final static int DATE_IND = 3;
    public final static int MARK_IND = 4;
}
