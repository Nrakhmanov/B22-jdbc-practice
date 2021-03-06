package jdbctests;

import org.junit.jupiter.api.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class DBUtilPractice {

    @Test
    public void test1() {

        String dbUrl = "jdbc:oracle:thin:@54.92.248.102:1521:XE";
        String dbUsername = "hr";
        String dbPassword = "hr";

        //create connection
        DBUtils.createConnection(dbUrl,dbUsername,dbPassword);

        String query = "SELECT first_name,last_name,salary,job_id " +
        "from employees " + " where rownum <6";

        List<Map<String, Object>> queryData = DBUtils.getQueryResultMap(query);

        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());

        }

        DBUtils.destroy();

    }

    @Test
    public void test2() {

        String dbUrl = "jdbc:oracle:thin:@54.92.248.102:1521:XE";
        String dbUsername = "hr";
        String dbPassword = "hr";

        DBUtils.createConnection(dbUrl, dbUsername, dbPassword);


        String query = "SELECT first_name,last_name,salary,job_id " +
                "from employees " + " where rownum <2";

        Map<String, Object> rowMap = DBUtils.getRowMap(query);

        System.out.println(rowMap.toString());

        DBUtils.destroy();

    }
}
