package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListOfMapExample {

    String dbUrl="jdbc:oracle:thin:@18.209.225.178:1521:XE";// ıp adress from EC2 machine, a port number for HR database : 1521
    String dbUsername = "hr";
    String dbPassword = "hr";   //These 3 line is connection string

    @Test
    public void test1(){

        //creating List for keeeping all the rows maps

        List<Map<String,Object>> queryData=new ArrayList<>();


    Map<String,Object> row1=new HashMap<>();

    row1.put("first_name","Steven");
    row1.put("last_name","King");
    row1.put("salary",24000);
    row1.put("job_id","AD_PRES");

        System.out.println(row1.toString());

    Map<String,Object> row2=new HashMap<>();

        row2.put("first_name","Neena");
        row2.put("last_name","Kochhar");
        row2.put("salary",17000);
        row2.put("job_id","AD_VP");

        System.out.println(row2.toString());

      //adding rows one by one to my list


        queryData.add(row1);
        queryData.add(row2);

        //get the steven last name directly from the list

        System.out.println(queryData.get(0).get("last_name"));

        //neeana's salary
        System.out.println(queryData.get(1).get("salary"));
//close connection


    }
    @Test
    public void test2() throws SQLException {

        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //helps our java project connect to database

        Statement statement= connection.createStatement();
        //helps to write and execute SQL query

        ResultSet resultSet=statement.executeQuery("select first_name,last_name,salary,job_id\n" + "from employees\n" + "where rownum<6");
        //A Data structure where we can store the data that came from database

        //in order to get column names we need resultsetmetadata
        ResultSetMetaData resultSetMetaData= resultSet.getMetaData();

//move to next row
        resultSet.next();


        //creating List for keeeping all the rows maps

        List<Map<String,Object>> queryData=new ArrayList<>();


        Map<String,Object> row1=new HashMap<>();

        row1.put(resultSetMetaData.getColumnName(1),resultSet.getString(1));
        row1.put(resultSetMetaData.getColumnName(2),resultSet.getString(2));
        row1.put(resultSetMetaData.getColumnName(3),resultSet.getInt(3));
        row1.put(resultSetMetaData.getColumnName(4),resultSet.getString(4));

        System.out.println(row1.toString());

        resultSet.next();

        Map<String,Object> row2=new HashMap<>();

        /*
        row2.put("first_name","Neena");
        row2.put("last_name","Kochhar");
        row2.put("salary",17000);
        row2.put("job_id","AD_VP");

        */
        row2.put(resultSetMetaData.getColumnName(1),resultSet.getString(1));
        row2.put(resultSetMetaData.getColumnName(2),resultSet.getString(2));
        row2.put(resultSetMetaData.getColumnName(3),resultSet.getInt(3));
        row2.put(resultSetMetaData.getColumnName(4),resultSet.getString(4));


        System.out.println(row2.toString());

        //adding rows one by one to my list
        queryData.add(row1);
        queryData.add(row2);

        System.out.println("queryData = " + queryData);
        //queryData = [{JOB_ID=AD_PRES, SALARY=24000, LAST_NAME=King, FIRST_NAME=Steven}, {JOB_ID=AD_VP, SALARY=17000, LAST_NAME=Kochhar, FIRST_NAME=Neena}]   as a list of map

        //close connection
        resultSet.close();
        statement.close();
        connection.close();

    }




}
