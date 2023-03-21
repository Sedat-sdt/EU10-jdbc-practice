package jdbctests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class jdbc_examples {

    String dbUrl="jdbc:oracle:thin:@18.209.225.178:1521:XE";// ıp adress from EC2 machine, a port number for HR database : 1521
    String dbUsername = "hr";
    String dbPassword = "hr";   //These 3 line is connection string

    @Test
    public void test1() throws SQLException {
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //helps our java project connect to database

        Statement statement= connection.createStatement();
        //helps to write and execute SQL query

        ResultSet resultSet=statement.executeQuery("select * from departments");
        //A Data structure where we can store the data that came from database

        //move to first row
        resultSet.next();

       // System.out.println(resultSet.getString(2));

        //display departments table in 10 - Administration - 200 - 1700 format

        while(resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2) + " - " + resultSet.getInt(3) + " - " + resultSet.getInt(4));
        }

        resultSet=statement.executeQuery("select * from regions");

        while(resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));
        }




        //close connection
        resultSet.close();
        statement.close();
        connection.close();

    }
    @DisplayName("ResultSet Methods")
    @Test
    public void test2() throws SQLException {

        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //helps our java project connect to database

        Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //helps to write and execute SQL query

        ResultSet resultSet=statement.executeQuery("select * from departments");
        //A Datastructure where we can store the data that came from database

        resultSet.last(); //we can move directly with using (ResultSet.TYPE_SCROLL_INSENSITIVE)



        //how to find how many row we have for the query
        //get the row count
        resultSet.last();
        System.out.println(resultSet.getRow());

        //to move before first row after we use last method
        resultSet.beforeFirst();

        //print all second column info
        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void test3() throws SQLException {

        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //helps our java project connect to database

        Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //helps to write and execute SQL query

        ResultSet resultSet=statement.executeQuery("select * from employees");
        //A Datastructure where we can store the data that came from database

        //get the database related data inside the dbMetadata object

        DatabaseMetaData dbMetadata= connection.getMetaData();
        System.out.println("dbMetadata.getUserName() = " + dbMetadata.getUserName());
        System.out.println("dbMetadata.getDatabaseProductName() = " + dbMetadata.getDatabaseProductName());
        System.out.println("dbMetadata.getDatabaseProductVersion() = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("dbMetadata.getDriverName() = " + dbMetadata.getDriverName());
        System.out.println("dbMetadata.getDriverVersion() = " + dbMetadata.getDriverVersion());


        //get resultmetadata
        ResultSetMetaData rsMetadata=resultSet.getMetaData();

        //how many columns we have
        int colCount=rsMetadata.getColumnCount();//total number of columns
        System.out.println("colCount = " + colCount);

        //getting column names
        System.out.println("rsMetadata.getColumnName() = " + rsMetadata.getColumnName(1));
        System.out.println("rsMetadata.getColumnName() = " + rsMetadata.getColumnName(2));
        System.out.println("rsMetadata.getColumnName() = " + rsMetadata.getColumnName(3));

        //print all the column names dynamically
        for (int i = 1; i <=rsMetadata.getColumnCount() ; i++) {
            System.out.println(rsMetadata.getColumnName(i));//gets column names

        }




        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }
}
