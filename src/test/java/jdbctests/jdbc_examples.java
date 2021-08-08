package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class jdbc_examples {

    String dbUrl = "jdbc:oracle:thin:@54.92.248.102:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");


        //resultSet.next();

       // System.out.println(resultSet.getString(2));

        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2) +
                    " - " + resultSet.getString(3) + " - " + resultSet.getInt(4));
        }

        //================================================

        resultSet = statement.executeQuery("SELECT * FROM regions");

        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));

        }


        resultSet.close();
        statement.close();
        connection.close();
    }


    @Test
    public void test2() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");

        // move pointer to the last row
        resultSet.last();

        int rowCount = resultSet.getRow();
        System.out.println(rowCount);

        //to move pointer to the before first row
        resultSet.beforeFirst();

        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }


        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void test3() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

        //get the database related data inside the dbMetadata object
        DatabaseMetaData dbMetadata = connection.getMetaData();

        System.out.println("dbMetadata.getUserName() = " + dbMetadata.getUserName());
        System.out.println("dbMetadata.getDatabaseProductName() = " + dbMetadata.getDatabaseProductName());
        System.out.println("dbMetadata.getDatabaseProductVersion() = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("dbMetadata.getDriverName() = " + dbMetadata.getDriverName());
        System.out.println("dbMetadata.getDriverVersion() = " + dbMetadata.getDriverVersion());

        // get the resultSetMetaData //rsmd

        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //how many columns we have
        int colCount = rsMetadata.getColumnCount();
        System.out.println(colCount);

        //getting columns names

        System.out.println(rsMetadata.getColumnName(1));
        System.out.println(rsMetadata.getColumnName(2));

        for (int i = 1; i <= colCount ; i++) {
            System.out.println(rsMetadata.getColumnName(i));

        }

        resultSet.close();
        statement.close();
        connection.close();

    }

}
