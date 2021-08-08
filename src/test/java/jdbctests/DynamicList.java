package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.*;

public class DynamicList {

    String dbUrl = "jdbc:oracle:thin:@54.92.248.102:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * from departments");

        ResultSetMetaData rsmd = resultSet.getMetaData();

        List<Map<String, Object>> queryData = new ArrayList<>();

        //number of columns
        int colCount = rsmd.getColumnCount();

        //loop through each row
        while (resultSet.next()) {

            Map<String, Object> row = new LinkedHashMap<>(); //every loop creating new row

            //some code to fill the map dynamically. loop through columns
            for (int i = 1; i <= colCount; i++) {
                row.put(rsmd.getColumnName(i), resultSet.getObject(i));

            }

            //add ready Map row to the List
            queryData.add(row);
        }

        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }



        resultSet.close();
        statement.close();
        connection.close();
    }
}
