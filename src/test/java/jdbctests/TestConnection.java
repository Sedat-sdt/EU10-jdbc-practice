package jdbctests;

import java.sql.*;

public class TestConnection {

    public static void main(String[] args) throws SQLException {
        String dbUrl="jdbc:oracle:thin:@18.209.225.178:1521:XE";// ıp adress from EC2 machine, a port number for HR database : 1521
        String dbUsername = "hr";
        String dbPassword = "hr";   //These 3 line is connection string

        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //helps our java project connect to database

        Statement statement= connection.createStatement();
        //helps to write and execute SQL query

        ResultSet resultSet=statement.executeQuery("select * from regions");
        //A Datastructure where we can store the data that came from database

        //next() is used to move pointer to first row
        resultSet.next();

        //getting information with column name
        System.out.println(resultSet.getString("region_name"));
        System.out.println(resultSet.getString(2));
        //getting info with column index(starts 1)


        //1 - Europa
        //2 - Americas
        System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));
        //move to seond row
        resultSet.next();
        System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));

        //third row
        resultSet.next();
        System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));


        //for these we can use loop
        //next() method have two functions
        //1 is iterate the next line, and the other one is to check if there is a line after current one

        while(resultSet.next()){
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));
        }

        //close connection
        resultSet.close();
        statement.close();
        connection.close();



    }
}
