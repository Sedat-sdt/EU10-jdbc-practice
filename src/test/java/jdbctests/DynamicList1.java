package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.*;

public class DynamicList1 {
    String dbUrl="jdbc:oracle:thin:@18.209.225.178:1521:XE";// ıp adress from EC2 machine, a port number for HR database : 1521
    String dbUsername = "hr";
    String dbPassword = "hr";   //These 3 line is connection string
    @Test
    public void test1() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        //helps our java project connect to database

        Statement statement = connection.createStatement();
        //helps to write and execute SQL query

        ResultSet resultSet = statement.executeQuery("select first_name,last_name,salary,job_id from employees where rownum<6");
        //A Data structure where we can store the data that came from database
        //get the specific cell information, datas

        //in order to get column names we need resultsetmetadata
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        List<Map<String,Object>> lisOfData=new ArrayList<>();

        while(resultSet.next()){

            Map<String,Object> row=new LinkedHashMap<>();

            for (int i = 1; i <=resultSetMetaData.getColumnCount() ; i++) {

                row.put(resultSetMetaData.getColumnName(i),resultSet.getString(i));

            }

            lisOfData.add(row);

        }
        for (Map<String, Object> row : lisOfData) {
            System.out.println(row.toString());
        }






        //close connection
        resultSet.close();
        statement.close();
        connection.close();

}

}
