package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicList {
    String dbUrl="jdbc:oracle:thin:@18.209.225.178:1521:XE";// ıp adress from EC2 machine, a port number for HR database : 1521
    String dbUsername = "hr";
    String dbPassword = "hr";   //These 3 line is connection string
    @Test
    public void test2() throws SQLException {

        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //helps our java project connect to database

        Statement statement= connection.createStatement();
        //helps to write and execute SQL query

        ResultSet resultSet=statement.executeQuery("select first_name,last_name,salary,job_id from employees where rownum<6");
        //A Data structure where we can store the data that came from database
        //get the specific cell information, datas

        //in order to get column names we need resultsetmetadata
        ResultSetMetaData resultSetMetaData= resultSet.getMetaData();

        //list of map to keep all information
        List<Map<String,Object>> queryData=new ArrayList<>();

        //number of column
        int colCount = resultSetMetaData.getColumnCount();

        //loop through each row
        while(resultSet.next()){

            Map<String,Object> row= new HashMap<>();

            for (int i = 1; i <= colCount ; i++) {

                row.put(resultSetMetaData.getColumnName(i),resultSet.getString(i));

            }

            //add map to the list
            queryData.add(row);
            
        }
        //print each row inside the list
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
            //System.out.println(row);  also can be like this
        }
        
        
        //System.out.println("queryData = " + queryData);
        //queryData = [{JOB_ID=AD_PRES, SALARY=24000, LAST_NAME=King, FIRST_NAME=Steven}, {JOB_ID=AD_VP, SALARY=17000, LAST_NAME=Kochhar, FIRST_NAME=Neena},
        // {JOB_ID=AD_VP, SALARY=17000, LAST_NAME=De Haan, FIRST_NAME=Lex}, {JOB_ID=IT_PROG, SALARY=9000, LAST_NAME=Hunold, FIRST_NAME=Alexander},
        // {JOB_ID=IT_PROG, SALARY=6000, LAST_NAME=Ernst, FIRST_NAME=Bruce}]

        //close connection
        resultSet.close();
        statement.close();
        connection.close();

    }
}
